package com.example.myretrofitproject.Model

sealed class RepoResponseResult {

    class Success(val repoList: List<UserRepoDetails>) : RepoResponseResult()
    sealed class Failure: RepoResponseResult()

    data object NoInternetConnection: Failure()

    class NetworkError(val code: Int, val message: String) :Failure()

    class OtherErrors(val message: String) : Failure()
}