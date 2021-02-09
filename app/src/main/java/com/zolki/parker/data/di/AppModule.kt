package com.zolki.parker.data.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.zolki.parker.data.repository.LocationRepository
import com.zolki.parker.feature.map.MapViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModules = module {
    viewModel { MapViewModel() }

    scope<Fragment> {
        factory { (lifecycle: Lifecycle, coroutineScope: CoroutineScope) ->
            LocationRepository(lifecycle = lifecycle, context = androidContext(), coroutineScope)
        }
    }
}