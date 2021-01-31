package com.zolki.parker.feature.map

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("EXPERIMENTAL_API_USAGE")
class MapViewModel : ViewModel() {

    private val _showPermissionsRequest = MutableStateFlow(true)
    val showPermissionsRequest: StateFlow<Boolean> = _showPermissionsRequest

}