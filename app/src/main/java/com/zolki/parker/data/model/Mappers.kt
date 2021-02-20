package com.zolki.parker.data.model

fun Feature.toParking(): Parking {
    return Parking(
        properties.id,
        properties.name,
        properties.numOfFreePlaces,
        properties.numOfTakenPlaces,
        properties.totalNumOfPlaces,
        geometry.coordinates[1],
        geometry.coordinates[0]
    )
}