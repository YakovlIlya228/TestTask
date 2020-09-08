package com.example.testtask.Di

import com.example.testtask.BuildConfig
import com.example.testtask.Network.ProfileService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val testModule = module {

    single {GsonConverterFactory.create()}
    single {
        Retrofit.Builder()
            .addConverterFactory(get() as GsonConverterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
    single { get<Retrofit>().create(ProfileService::class.java) }
}