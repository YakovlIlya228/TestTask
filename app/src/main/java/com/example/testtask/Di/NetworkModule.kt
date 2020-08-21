package com.example.testtask.Di

import com.example.testtask.BuildConfig.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
            .addConverterFactory(get() as GsonConverterFactory)
            .baseUrl(BASE_URL)
            .build()
    }


}