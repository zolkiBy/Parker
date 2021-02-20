package com.zolki.parker.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zolki.parker.data.repository.ParkingRepository

class MapViewModelFactory(private val parkingRepository: ParkingRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MapViewModel::class.java) -> MapViewModel(parkingRepository)
            else -> throw UnsupportedOperationException("Cant handle such ViewModel class: $modelClass")
        } as T
}