package com.example.myretrofitproject.Main.repo

import android.util.Log
import com.example.myretrofitproject.Interface.RetrofitClientObject
import com.example.myretrofitproject.Model.RepoResponseResult
import com.example.myretrofitproject.Model.UserResponseResult
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class GithubRepo {

    companion object {
        private const val TAG = "GithubRepo"
    }

    private val api = RetrofitClientObject.api

    suspend fun getUserData(username: String) : UserResponseResult {
        return try {
            val user = api.getUserDetails(username)
            UserResponseResult.Success(user)
        } catch (ex: UnknownHostException) {
            Log.e(TAG, ex.message ?: "")
            UserResponseResult.NoInternetConnection
        } catch (ex: HttpException) {
            Log.e(TAG, ex.message())
            UserResponseResult.NetworkError(ex.code(), ex.message())
        } catch (ex: Exception) {
            Log.e(TAG, ex.message?:"")
            UserResponseResult.OtherErrors(ex.message ?: "Something went wrong with the user details call")
        }
    }

    suspend fun getRepoDataForUsername(username: String): RepoResponseResult {
        return try {
            val repoList = api.getUserRepoDetails(username+"1")
            RepoResponseResult.Success(repoList)
        } catch (ex: UnknownHostException){
            Log.e(TAG, ex.message?: "")
            RepoResponseResult.NoInternetConnection
        } catch (ex: HttpException) {
            Log.e(TAG, ex.message())
            RepoResponseResult.NetworkError(ex.code(),ex.message())
        } catch (ex: Exception) {
            Log.e(TAG, ex.message?: "")
            RepoResponseResult.OtherErrors(ex.message ?: "Something went wrong with the repo details call")
        }
    }
}