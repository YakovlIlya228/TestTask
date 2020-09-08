package com.example.testtask

import android.app.Application
import com.example.testtask.Di.databaseModule
import com.example.testtask.Di.generalModule
import com.example.testtask.Di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(
                databaseModule,
                networkModule,
                generalModule
            ))
        }

    }
}