package com.zolki.parker.data.di

import com.zolki.parker.feature.map.MapViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModules = module {
    viewModel { MapViewModel() }
}