package com.zolki.parker.data.model

import com.google.gson.annotations.SerializedName

data class ParkingDTO(
    val type: String,
    val features: List<Feature>
)

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)

data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

data class Properties(
    @SerializedName("parking_type")
    val parkingType: ParkingType,

    val id: Int,

    val name: String,

    @SerializedName("num_of_free_places")
    val numOfFreePlaces: Int,

    @SerializedName("num_of_taken_places")
    val numOfTakenPlaces: Int,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("total_num_of_places")
    val totalNumOfPlaces: Int,

    @SerializedName("average_occupancy")
    val averageOccupancy: AverageOccupancy,

    val district: String,

    val address: Address,

    @SerializedName("last_updated")
    val lastUpdated: Long,

    @SerializedName("payment_link")
    val paymentLink: String,

    @Suppress("SpellCheckingInspection")
    @SerializedName("payment_shortname")
    val paymentShortName: String
)

data class ParkingType(
    val description: String,
    val id: Int
)

data class AverageOccupancy(
    val `0`: X0,
    val `1`: X1,
    val `2`: X2,
    val `3`: X3,
    val `4`: X4,
    val `5`: X5,
    val `6`: X6
)

data class X0(
    val `00`: Double,
    val `01`: Double,
    val `02`: Double,
    val `03`: Double,
    val `04`: Double,
    val `05`: Double,
    val `06`: Double,
    val `07`: Double,
    val `08`: Double,
    val `09`: Double,
    val `10`: Double,
    val `11`: Double,
    val `12`: Double,
    val `13`: Double,
    val `14`: Double,
    val `15`: Double,
    val `16`: Double,
    val `17`: Double,
    val `18`: Double,
    val `19`: Double,
    val `20`: Double,
    val `21`: Double,
    val `22`: Double,
    val `23`: Double
)

data class X1(
    val `00`: Double,
    val `01`: Double,
    val `02`: Double,
    val `03`: Double,
    val `04`: Double,
    val `05`: Double,
    val `06`: Double,
    val `07`: Double,
    val `08`: Double,
    val `09`: Double,
    val `10`: Double,
    val `11`: Double,
    val `12`: Double,
    val `13`: Double,
    val `14`: Double,
    val `15`: Double,
    val `16`: Double,
    val `17`: Double,
    val `18`: Double,
    val `19`: Double,
    val `20`: Double,
    val `21`: Double,
    val `22`: Double,
    val `23`: Double
)

data class X2(
    val `00`: Double,
    val `01`: Double,
    val `02`: Double,
    val `03`: Double,
    val `04`: Double,
    val `05`: Double,
    val `06`: Double,
    val `07`: Double,
    val `08`: Double,
    val `09`: Double,
    val `10`: Double,
    val `11`: Double,
    val `12`: Double,
    val `13`: Double,
    val `14`: Double,
    val `15`: Double,
    val `16`: Double,
    val `17`: Double,
    val `18`: Double,
    val `19`: Double,
    val `20`: Double,
    val `21`: Double,
    val `22`: Double,
    val `23`: Double
)

data class X3(
    val `00`: Double,
    val `01`: Double,
    val `02`: Double,
    val `03`: Double,
    val `04`: Double,
    val `05`: Double,
    val `06`: Double,
    val `07`: Double,
    val `08`: Double,
    val `09`: Double,
    val `10`: Double,
    val `11`: Double,
    val `12`: Double,
    val `13`: Double,
    val `14`: Double,
    val `15`: Double,
    val `16`: Double,
    val `17`: Double,
    val `18`: Double,
    val `19`: Double,
    val `20`: Double,
    val `21`: Double,
    val `22`: Double,
    val `23`: Double
)

data class X4(
    val `00`: Double,
    val `01`: Double,
    val `02`: Double,
    val `03`: Double,
    val `04`: Double,
    val `05`: Double,
    val `06`: Double,
    val `07`: Double,
    val `08`: Double,
    val `09`: Double,
    val `10`: Double,
    val `11`: Double,
    val `12`: Double,
    val `13`: Double,
    val `14`: Double,
    val `15`: Double,
    val `16`: Double,
    val `17`: Double,
    val `18`: Double,
    val `19`: Double,
    val `20`: Double,
    val `21`: Double,
    val `22`: Double,
    val `23`: Double
)

data class X5(
    val `00`: Double,
    val `01`: Double,
    val `02`: Double,
    val `03`: Double,
    val `04`: Double,
    val `05`: Double,
    val `06`: Double,
    val `07`: Double,
    val `08`: Double,
    val `09`: Double,
    val `10`: Double,
    val `11`: Double,
    val `12`: Double,
    val `13`: Double,
    val `14`: Double,
    val `15`: Double,
    val `16`: Double,
    val `17`: Double,
    val `18`: Double,
    val `19`: Double,
    val `20`: Double,
    val `21`: Double,
    val `22`: Double,
    val `23`: Double
)

data class X6(
    val `00`: Double,
    val `01`: Double,
    val `02`: Double,
    val `03`: Double,
    val `04`: Double,
    val `05`: Double,
    val `06`: Double,
    val `07`: Double,
    val `08`: Double,
    val `09`: Double,
    val `10`: Double,
    val `11`: Double,
    val `12`: Double,
    val `13`: Double,
    val `14`: Double,
    val `15`: Double,
    val `16`: Double,
    val `17`: Double,
    val `18`: Double,
    val `19`: Double,
    val `20`: Double,
    val `21`: Double,
    val `22`: Double,
    val `23`: Double
)

data class Address(
    @SerializedName("address_country")
    val addressCountry: String,

    @SerializedName("address_formatted")
    val addressFormatted: String,

    @SerializedName("address_locality")
    val address_locality: String,

    @SerializedName("address_region")
    val addressRegion: String,

    @SerializedName("postal_code")
    val postalCode: String,

    @SerializedName("street_address")
    val streetAddress: String
)