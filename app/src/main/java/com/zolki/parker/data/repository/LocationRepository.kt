package com.zolki.parker.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.location.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LocationRepository(lifecycle: Lifecycle, context: Context, private val coroutineScope: CoroutineScope) : LifecycleObserver {

    companion object {
        private const val UPDATE_INTERVAL = 10000L
        private const val FASTEST_INTERVAL = 5000L
    }

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context.applicationContext)
    private val _locationFlow = MutableSharedFlow<Location>()
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            coroutineScope.launch { produceLocationEvent(locationResult.lastLocation) }
        }
    }
    private val locationRequest = LocationRequest.create()?.apply {
        interval = UPDATE_INTERVAL
        fastestInterval = FASTEST_INTERVAL
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val location: SharedFlow<Location> = _locationFlow

    init {
        lifecycle.addObserver(this)
    }

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        startLocationUpdates()
    }

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        stopLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private suspend fun produceLocationEvent(location: Location) {
        _locationFlow.emit(location)
    }
}
