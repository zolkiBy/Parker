package com.zolki.parker.data.networking

import com.zolki.parker.data.model.ParkingDTO
import com.zolki.parker.data.networking.result.Result
import retrofit2.http.GET
import retrofit2.http.Query

@Suppress("SpellCheckingInspection")
interface GolemioApi {
    @GET("parkings")
    suspend fun getParking(
        @Query(value = "latlng") latLng: String? = null,
        @Query(value = "range") range: Long? = null,
        @Query(value = "districts[]") districts: List<String>? = null,
        @Query(value = "limit") limit: Long? = null,
        @Query(value = "offset") offset: Long? = null,
        @Query(value = "updatedSince") updateSince: String? = null
    ): Result<ParkingDTO>
}