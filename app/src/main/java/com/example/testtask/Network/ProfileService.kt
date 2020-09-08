package com.example.testtask.Network

import com.example.testtask.Pojo.Data
import com.example.testtask.Pojo.Page
import com.example.testtask.Pojo.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileService {
    @GET("/api/users")
    suspend fun getProfiles(@Query("page")page: Int): Page

    @GET("users")
    suspend fun getUsers(): List<User>
}