package com.example.testtask.Di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class Application: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(mutableListOf(databaseModule, networkModule, generalModel))
        }
    }
}