package com.zolki.parker.feature.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.zolki.parker.R
import com.zolki.parker.databinding.FragmentMapBinding
import kotlinx.coroutines.flow.*
import timber.log.Timber

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 11111
    }

    private val viewModel: MapViewModel by navGraphViewModels(R.id.nav_graph)
    private val binding by viewBinding(FragmentMapBinding::bind)
    private lateinit var googleMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            map.onCreate(savedInstanceState)
            map.getMapAsync(this@MapFragment)
        }

        viewModel.showPermissionsFlow
            .onEach { show -> Timber.d("Show permissions flow: %s", show) }
            .filter { show -> show }
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