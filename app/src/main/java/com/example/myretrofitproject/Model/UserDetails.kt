package com.example.myretrofitproject.Model

import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("login")
    val name: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
)
