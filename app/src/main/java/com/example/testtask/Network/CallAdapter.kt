package com.example.testtask.Network

import com.example.testtask.Pojo.Data
import com.example.testtask.Pojo.Page
import retrofit2.http.GET
import retrofit2.http.Query

interface CallAdapter {
    @GET("/api/users")
    suspend fun getProfiles(@Query("page")page: Int): Page
}