package com.zolki.parker.data.networking

import com.zolki.parker.data.model.ParkingDTO
import com.zolki.parker.data.networking.result.Result
import retrofit2.http.GET
import retrofit2.http.Query

@Suppress("SpellCheckingInspection")
interface GolemioApi {
    @GET("parkings")
    suspend fun getParking(
        @Query(value = "latlng") latLng: String?,
        @Query(value = "range") range: Long?,
        @Query(value = "districts[]") districts: List<String>?,
        @Query(value = "limit") limit: Long?,
        @Query(value = "offset") offset: Long?,
        @Query(value = "updatedSince") updateSince: String?
    ): Result<ParkingDTO>
}