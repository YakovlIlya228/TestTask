package com.example.testtask.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtask.Pojo.Data


@Database(entities = [Data::class],version = 1,exportSchema = false)
abstract class ProfilesDatabase: RoomDatabase() {

    abstract fun profileDao(): DAO

    companion object{

        val dbName = "ProfilesDB"

    }
}