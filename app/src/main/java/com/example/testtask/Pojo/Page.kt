package com.example.testtask.Pojo

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val dataList: ArrayList<Data>
)