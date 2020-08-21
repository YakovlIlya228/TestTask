package com.example.testtask.Di

import com.example.testtask.ViewModel.GeneralViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit

val generalModel = module {
    viewModel { GeneralViewModel(get() ,get()) }
    single { get<Retrofit>().create(CallAdapter::class.java) }
}