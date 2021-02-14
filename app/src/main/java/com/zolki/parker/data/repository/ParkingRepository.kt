package com.zolki.parker.data.repository

import com.zolki.parker.data.model.Parking
import com.zolki.parker.data.model.ParkingDTO
import com.zolki.parker.data.model.toParking
import com.zolki.parker.data.networking.GolemioApi
import com.zolki.parker.data.networking.result.Result
import com.zolki.parker.data.networking.result.asFailure
import com.zolki.parker.data.networking.result.asSuccess
import com.zolki.parker.data.networking.result.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class ParkingRepository(@Suppress("SpellCheckingInspection") val golemioApi: GolemioApi) {

    @FlowPreview
    suspend fun getParking(): Flow<List<Parking>> {
        return flow {
            val result: Result<ParkingDTO> = golemioApi.getParking(null, null, null, null, null, null)
            if (result.isSuccess()) {
                val parkingList = result.asSuccess().value.features
                    .map { feature -> feature.toParking() }
                emit(parkingList)
            } else {
                result.asFailure().error?.let { throwable ->
                    throw throwable
                } ?: throw error("Can't load parking data")
            }
        }
            .flowOn(Dispatchers.IO)
            .conflate()
    }
}