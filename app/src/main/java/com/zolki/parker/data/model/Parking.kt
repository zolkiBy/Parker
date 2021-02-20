package com.zolki.parker.data.model

data class Parking(
    val id: Int,
    val name: String,
    val numOfFreePlaces: Int,
    val numOfTakenPlaces: Int,
    val totalNumOfPlaces: Int,
    val latitude: Double,
    val longitude: Double
)