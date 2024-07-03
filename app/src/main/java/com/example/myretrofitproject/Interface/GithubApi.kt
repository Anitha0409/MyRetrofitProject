package com.example.myretrofitproject.Interface

import com.example.myretrofitproject.Model.UserDetails
import com.example.myretrofitproject.Model.UserRepoDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/{username}")
    @Headers("Authorization: token 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")
    suspend fun getUserDetails(@Path("username") username: String): UserDetails


    @GET("/users/{username}/repos")
    @Headers("Authorization: token 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")
    suspend fun getUserRepoDetails(@Path("username") username: String): List<UserRepoDetails>
}