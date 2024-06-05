package com.example.myretrofitproject.Main.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity (tableName = "user_details_table")
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("login")
    val name: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)
