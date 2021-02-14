package com.zolki.parker.data.model

import com.google.android.gms.maps.model.LatLng

data class Parking(
    val id: Int,
    val name: String,
    val numOfFreePlaces: Int,
    val numOfTakenPlaces: Int,
    val totalNumOfPlaces: Int,
    val location: LatLng
)