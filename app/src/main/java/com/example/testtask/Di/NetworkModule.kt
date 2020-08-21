package com.example.testtask.Di

import com.example.testtask.BuildConfig
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
            .addConverterFactory(get() as GsonConverterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .build().create(CallAdapter::class.java)
    }
}