package com.example.testtask.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testtask.Pojo.Data


@Database(entities = [Data::class],version = 1,exportSchema = false)
abstract class ProfilesDatabase: RoomDatabase() {

    abstract fun profileDao(): DAO

    companion object{

        @Volatile
        var instance: ProfilesDatabase? = null

        fun getInstance(context: Context): ProfilesDatabase{
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(ProfilesDatabase::class) {
                val instance = Room.databaseBuilder(
                    context,
                    ProfilesDatabase::class.java,
                    "ProfilesDB"
                ).build()
                this.instance = instance
                return instance
            }
        }
    }
}