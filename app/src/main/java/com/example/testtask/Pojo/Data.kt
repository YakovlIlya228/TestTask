package com.example.testtask.Pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "profiles")
data class Data(
    @SerializedName("id")
    @PrimaryKey @NotNull
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String


)