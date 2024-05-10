package com.example.myretrofitproject.Model

import com.google.gson.annotations.SerializedName


data class UserRepoDetails(
        @SerializedName("name")
        val name: String?,
        @SerializedName("description")
        val description: String?
    )


