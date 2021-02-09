package com.zolki.parker.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zolki.parker.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@Suppress("EXPERIMENTAL_API_USAGE")
class MapViewModel : ViewModel() {

    private val _showPermissionsFlow = MutableStateFlow(false)
    val showPermissionsFlow: StateFlow<Boolean> = _showPermissionsFlow

    private val _showPermissionsRequest = MutableSharedFlow<Boolean>()
    val showPermissionsRequest: SharedFlow<Boolean> = _showPermissionsRequest

    private val _showAdvisoryScreen = MutableSharedFlow<Int>()
    val showAdvisoryScreen: SharedFlow<Int> = _showAdvisoryScreen

    private val _showPermissionExplanationScreen = MutableSharedFlow<Boolean>()
    val showPermissionExplanationScreen: SharedFlow<Boolean> = _showPermissionExplanationScreen

    private val _enableMapMyLocationLayer = MutableSharedFlow<Boolean>()
    val enableMapMyLocationLayer: SharedFlow<Boolean> = _enableMapMyLocationLayer

    fun onMapReady() {
        viewModelScope.launch { _showPermissionsFlow.emit(true) }
    }

    fun onShowPermissionsRationale() {
        Timber.d("onShowPermissionRationale called")
        viewModelScope.launch {
            _showPermissionExplanationScreen.emit(true)
        }
    }

    fun onUserAgreeToShowPermissionRequest() {
        Timber.d("onUserAgreeToShowPermissionRequest called")
        viewModelScope.launch {
            _showPermissionsRequest.emit(true)
        }
    }

    fun onPermissionsGrantedResult() {
        Timber.d("onPermissionsGrantedResult called")
        viewModelScope.launch { _enableMapMyLocationLayer.emit(true) }
    }

    fun onPermissionsNotGrantedResult() {
        Timber.d("onPermissionsNotGrantedResult called")
        viewModelScope.launch { _showAdvisoryScreen.emit(R.string.location_permissions_not_granted) }
    }

    fun onPermissionsGranted() {
        Timber.d("onPermissionsGranted called")
        viewModelScope.launch { _enableMapMyLocationLayer.emit(true) }
    }

    fun onGooglePlayServicesNotAvailable() {
        Timber.d("onGooglePlayServicesNotAvailable called")
        viewModelScope.launch { _showAdvisoryScreen.emit(R.string.google_play_services_not_available) }
    }
}