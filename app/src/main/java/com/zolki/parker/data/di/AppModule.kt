package com.zolki.parker.data.di

import androidx.lifecycle.Lifecycle
import com.zolki.parker.data.networking.AuthorizationInterceptor
import com.zolki.parker.data.networking.GolemioApi
import com.zolki.parker.data.networking.result.ResultAdapterFactory
import com.zolki.parker.data.repository.LocationRepository
import com.zolki.parker.data.repository.ParkingRepository
import com.zolki.parker.feature.map.MapFragment
import com.zolki.parker.feature.map.MapViewModel
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@JvmField
val appModules = listOf(
    module {
        single { ParkingRepository(get()) }

        viewModel { MapViewModel(get()) }

        scope<MapFragment> {
            factory { (lifecycle: Lifecycle, coroutineScope: CoroutineScope) ->
                LocationRepository(lifecycle, androidContext(), coroutineScope)
            }
        }
    },
    module {
        single { provideHttpClient() }
        single { provideRetrofit(get()) }
        single { provideGolemioApi(get()) }
    }
)

private fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor())
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.golemio.cz/v2/")
        .addCallAdapterFactory(ResultAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

@Suppress("SpellCheckingInspection")
private fun provideGolemioApi(retrofit: Retrofit): GolemioApi {
    return retrofit.create(GolemioApi::class.java)
}