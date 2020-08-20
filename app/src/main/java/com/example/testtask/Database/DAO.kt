package com.example.testtask.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testtask.Pojo.Data

@Dao
interface DAO {
    @Query("SELECT * FROM profiles ORDER BY id ASC")
    fun getAllProfiles(): LiveData<List<Data>>
    @Query("DELETE FROM profiles")
    suspend fun deleteAll()
    @Query("SELECT EXISTS(SELECT * FROM profiles WHERE id = :id)")
    suspend fun checkExistence(id: Int): Boolean
    @Insert
    suspend fun insert(profile: Data)
    @Query("UPDATE PROFILES SET firstName =  :firstName, last_name = :lastName, email = :email WHERE id = :id ")
    suspend fun updateById(id: Int,firstName: String,lastName: String, email: String)
    @Update
    suspend fun update(profile: Data)
    @Query("DELETE FROM PROFILES WHERE id = :id")
    suspend fun deleteById(id: Int)

}