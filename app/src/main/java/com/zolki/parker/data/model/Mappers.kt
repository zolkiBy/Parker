package com.zolki.parker.data.model

import com.google.android.gms.maps.model.LatLng

fun Feature.toParking(): Parking {
    return Parking(
        properties.id,
        properties.name,
        properties.numOfFreePlaces,
        properties.numOfTakenPlaces,
        properties.totalNumOfPlaces,
        LatLng(geometry.coordinates[1], geometry.coordinates[0])
    )
}