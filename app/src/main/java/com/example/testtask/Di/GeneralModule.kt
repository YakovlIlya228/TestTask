package com.example.testtask.Di

import android.location.LocationManager
import android.location.LocationProvider
import com.example.testtask.Network.ProfileService
import com.example.testtask.ViewModel.GeneralViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val generalModule = module {
    viewModel { GeneralViewModel(get(), get()) }
    single { get<Retrofit>().create(ProfileService::class.java) }

}
