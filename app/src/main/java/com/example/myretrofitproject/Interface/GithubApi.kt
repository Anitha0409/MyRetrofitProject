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
    fun getUserDetails(@Path("username") username: String): Call<UserDetails>


    @GET("/users/{username}/repos")
    @Headers("Authorization: token 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")
    fun getUserRepoDetails(@Path("username") username: String): Call<List<UserRepoDetails>> // Is this the right way?
}