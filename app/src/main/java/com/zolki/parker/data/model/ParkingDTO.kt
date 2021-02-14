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
    val `00`: Int,
    val `01`: Int,
    val `02`: Int,
    val `03`: Int,
    val `04`: Int,
    val `05`: Int,
    val `06`: Int,
    val `07`: Int,
    val `08`: Int,
    val `09`: Int,
    val `10`: Int,
    val `11`: Int,
    val `12`: Int,
    val `13`: Int,
    val `14`: Int,
    val `15`: Int,
    val `16`: Int,
    val `17`: Int,
    val `18`: Int,
    val `19`: Int,
    val `20`: Int,
    val `21`: Int,
    val `22`: Int,
    val `23`: Int
)

data class X1(
    val `00`: Int,
    val `01`: Int,
    val `02`: Int,
    val `03`: Int,
    val `04`: Int,
    val `05`: Int,
    val `06`: Int,
    val `07`: Int,
    val `08`: Int,
    val `09`: Int,
    val `10`: Int,
    val `11`: Int,
    val `12`: Int,
    val `13`: Int,
    val `14`: Int,
    val `15`: Int,
    val `16`: Int,
    val `17`: Int,
    val `18`: Int,
    val `19`: Int,
    val `20`: Int,
    val `21`: Int,
    val `22`: Int,
    val `23`: Int
)

data class X2(
    val `00`: Int,
    val `01`: Int,
    val `02`: Int,
    val `03`: Int,
    val `04`: Int,
    val `05`: Int,
    val `06`: Int,
    val `07`: Int,
    val `08`: Int,
    val `09`: Int,
    val `10`: Int,
    val `11`: Int,
    val `12`: Int,
    val `13`: Int,
    val `14`: Int,
    val `15`: Int,
    val `16`: Int,
    val `17`: Int,
    val `18`: Int,
    val `19`: Int,
    val `20`: Int,
    val `21`: Int,
    val `22`: Int,
    val `23`: Int
)

data class X3(
    val `00`: Int,
    val `01`: Int,
    val `02`: Int,
    val `03`: Int,
    val `04`: Int,
    val `05`: Int,
    val `06`: Int,
    val `07`: Int,
    val `08`: Int,
    val `09`: Int,
    val `10`: Int,
    val `11`: Int,
    val `12`: Int,
    val `13`: Int,
    val `14`: Int,
    val `15`: Int,
    val `16`: Int,
    val `17`: Int,
    val `18`: Int,
    val `19`: Int,
    val `20`: Int,
    val `21`: Int,
    val `22`: Int,
    val `23`: Int
)

data class X4(
    val `00`: Int,
    val `01`: Int,
    val `02`: Int,
    val `03`: Int,
    val `04`: Int,
    val `05`: Int,
    val `06`: Int,
    val `07`: Int,
    val `08`: Int,
    val `09`: Int,
    val `10`: Int,
    val `11`: Int,
    val `12`: Int,
    val `13`: Int,
    val `14`: Int,
    val `15`: Int,
    val `16`: Int,
    val `17`: Int,
    val `18`: Int,
    val `19`: Int,
    val `20`: Int,
    val `21`: Int,
    val `22`: Int,
    val `23`: Int
)

data class X5(
    val `00`: Int,
    val `01`: Int,
    val `02`: Int,
    val `03`: Int,
    val `04`: Int,
    val `05`: Int,
    val `06`: Int,
    val `07`: Int,
    val `08`: Int,
    val `09`: Int,
    val `10`: Int,
    val `11`: Int,
    val `12`: Int,
    val `13`: Int,
    val `14`: Int,
    val `15`: Int,
    val `16`: Int,
    val `17`: Int,
    val `18`: Int,
    val `19`: Int,
    val `20`: Int,
    val `21`: Int,
    val `22`: Int,
    val `23`: Int
)

data class X6(
    val `00`: Int,
    val `01`: Int,
    val `02`: Int,
    val `03`: Int,
    val `04`: Int,
    val `05`: Int,
    val `06`: Int,
    val `07`: Int,
    val `08`: Int,
    val `09`: Int,
    val `10`: Int,
    val `11`: Int,
    val `12`: Int,
    val `13`: Int,
    val `14`: Int,
    val `15`: Int,
    val `16`: Int,
    val `17`: Int,
    val `18`: Int,
    val `19`: Int,
    val `20`: Int,
    val `21`: Int,
    val `22`: Int,
    val `23`: Int
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