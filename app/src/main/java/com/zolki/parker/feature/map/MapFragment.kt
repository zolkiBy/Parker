package com.zolki.parker.feature.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zolki.parker.R
import com.zolki.parker.common.ext.toPx
import com.zolki.parker.data.repository.LocationRepository
import com.zolki.parker.databinding.FragmentMapBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.koin.android.scope.ScopeFragment
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class MapFragment : ScopeFragment(), OnMapReadyCallback {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 11111
        private const val MARKER_SIZE = 32
    }

    private val viewModel: MapViewModel by navGraphViewModels(R.id.nav_graph) { MapViewModelFactory(get()) }
    private val locationRepository: LocationRepository by inject { parametersOf(lifecycle, viewLifecycleOwner.lifecycleScope) }

    private val binding by viewBinding(FragmentMapBinding::bind)
    private lateinit var googleMap: GoogleMap

    private var markerBitmapDescriptor: BitmapDescriptor? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_map, container, false)

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            map.onCreate(savedInstanceState)
            map.getMapAsync(this@MapFragment)
        }

        viewModel.showPermissionsFlow
            .onEach { show -> Timber.d("Show permissions flow: %s", show) }
            .filter { show -> show }
            .catch { exception -> Timber.e(exception, "Can't show permissions flow") }
            .asLiveData().observe(viewLifecycleOwner) {
                val availabilityResult = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext())
                if (availabilityResult == ConnectionResult.SUCCESS) {
                    handleOrRequestPermissions()
                } else {
                    viewModel.onGooglePlayServicesNotAvailable()
                }
            }

        viewModel.showPermissionExplanationScreen
            .onEach { show -> Timber.d("Show permissions explanation screen: %s", show) }
            .filter { show -> show }
            .catch { exception -> Timber.e(exception, "Can't show permissions explanation screen") }
            .asLiveData().observe(viewLifecycleOwner) { showPermissionExplanationDialog() }

        viewModel.showPermissionsRequest
            .onEach { show -> Timber.d("Show permissions request: %s", show) }
            .filter { show -> show }
            .catch { exception -> Timber.e(exception, "Can't show permission request") }
            .asLiveData().observe(viewLifecycleOwner) { requestLocationPermission() }

        viewModel.showAdvisoryScreen
            .onEach { resId -> Timber.d("Show advisory screen with res id: %s", resId) }
            .filter { resId -> resId != 0 }
            .catch { exception -> Timber.e(exception, "Can't show advisory screen") }
            .asLiveData().observe(viewLifecycleOwner) { descriptionResId -> showAdvisoryDialog(descriptionResId) }

        viewModel.enableMapMyLocationLayer
            .onEach { enabled -> Timber.d("set map location layer enabled: %s", enabled) }
            .catch { exception -> Timber.e(exception, "Can't enable map location layer") }
            .asLiveData().observe(viewLifecycleOwner) { enabled ->
                if (this::googleMap.isInitialized) {
                    setMyLocationMapLayerEnabled(enabled)
                }
            }

        viewModel.locationUpdatesEnabled
            .onEach { enabled -> Timber.d("Location updates enabled: %s", enabled) }
            .catch { exception -> Timber.e(exception, "Can't enable/disable location updates") }
            .asLiveData().observe(viewLifecycleOwner) { enabled -> locationRepository.locationUpdatesEnabled = enabled }

        locationRepository.location
            .onEach { }
            .catch { exception -> Timber.e(exception, "Can't get location") }
            .asLiveData().observe(viewLifecycleOwner) { location -> viewModel.onLocationChanged(location) }

        viewModel.parking
            .onEach { parkingList -> Timber.d("Parking list loaded: %s", parkingList) }
            .catch { exception -> Timber.e(exception, "Can't enable map location layer") }
            .map { parkingList ->
                parkingList.map { parking ->
                    val position = LatLng(parking.latitude, parking.longitude)
                    MarkerOptions()
                        .position(position)
                        .title(parking.name)
                        .snippet(getString(R.string.fragment_map_marker_snippet, parking.numOfFreePlaces, parking.numOfTakenPlaces))
                        .icon(markerBitmapDescriptor)
                }
            }
            .asLiveData().observe(viewLifecycleOwner) { markers ->
                Timber.d("Show markers on map: %s", markers)
                if (this::googleMap.isInitialized) {
                    markers.forEach { marker -> googleMap.addMarker(marker) }
                }
            }
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.map.onStart()
    }

    override fun onStop() {
        binding.map.onStop()
        super.onStop()
    }

    override fun onPause() {
        binding.map.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.map.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.map.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        val size = MARKER_SIZE.toPx(requireContext())
        val markerBitmap = ResourcesCompat.getDrawable(resources, R.drawable.ic_parking_24, null)
            ?.toBitmap(width = size, height = size)
        markerBitmap?.let {
            markerBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(it)
        }
        viewModel.onMapReady()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissionsGranted(grantResults)) {
                viewModel.onPermissionsGrantedResult()
            } else {
                viewModel.onPermissionsNotGrantedResult()
            }
        }
    }

    private fun handleOrRequestPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            viewModel.onPermissionsGranted()
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                viewModel.onShowPermissionsRationale()
            } else {
                requestLocationPermission()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setMyLocationMapLayerEnabled(enabled: Boolean) {
        googleMap.isMyLocationEnabled = enabled
    }

    private fun permissionsGranted(grantResults: IntArray): Boolean =
        grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED

    private fun showAdvisoryDialog(@StringRes descriptionResId: Int) {
        val action = MapFragmentDirections.actionMapFragmentToAdvisoryDialog(description = getString(descriptionResId))
        findNavController().navigate(action)
    }

    private fun showPermissionExplanationDialog() {
        val action = MapFragmentDirections.actionMapFragmentToPermissionExplanationDialog()
        findNavController().navigate(action)
    }

    private fun requestLocationPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
    }
}