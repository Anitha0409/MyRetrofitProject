package com.example.myretrofitproject.Interface

import com.example.myretrofitproject.Model.UserDetails
import com.example.myretrofitproject.Model.UserRepoDetailsClass
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
    fun getUserRepoDetails(@Path("username") username: String): Call<UserRepoDetailsClass> // Is this the right way?
}