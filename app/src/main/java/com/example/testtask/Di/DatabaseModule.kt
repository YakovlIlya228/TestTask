package com.example.testtask.Di

import androidx.room.Room
import com.example.testtask.Database.ProfilesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { get<ProfilesDatabase>().profileDao() }

    single {
        Room.databaseBuilder(
            androidApplication(),
            ProfilesDatabase::class.java,
            ProfilesDatabase.dbName).build()
    }
}