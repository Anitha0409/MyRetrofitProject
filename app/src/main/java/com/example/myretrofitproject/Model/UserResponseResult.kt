package com.example.myretrofitproject.Model

sealed class UserResponseResult {

    class Success(val userDetails: UserDetails): UserResponseResult()

    sealed class Failure : UserResponseResult()

    data object NoInternetConnection : Failure()

    class NetworkError(val code: Int, val message: String) : Failure()

    class OtherErrors(val message: String) : Failure()
}