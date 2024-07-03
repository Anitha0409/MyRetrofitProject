package com.example.myretrofitproject.Main.repo

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import com.example.myretrofitproject.Interface.RetrofitClientObject
import com.example.myretrofitproject.Main.Room.UserDetailsEntity
import com.example.myretrofitproject.Main.application.MyApplication
import com.example.myretrofitproject.Main.util.RepoListResponseToRepoListEntity.toEntityList
import com.example.myretrofitproject.Model.RepoResponseResult
import com.example.myretrofitproject.Model.UserDetails
import com.example.myretrofitproject.Model.UserResponseResult
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException
import java.util.Locale

class GithubRepo {

    companion object {
        private const val TAG = "GithubRepo"
    }

    private val api = RetrofitClientObject.api

    suspend fun getUserData(username: String) : UserResponseResult {
        return try {
            val user = api.getUserDetails(username)
            MyApplication.DATABASE_INSTANCE.userRepoDetailsDAO().insertUserDetails(
                UserDetailsEntity(
                    name = user.name?.lowercase(Locale.getDefault()),
                    avatarUrl = user.avatarUrl
                )
            )
            UserResponseResult.Success(user)
        } catch (ex: UnknownHostException) {
            Log.e(TAG, ex.message ?: "")
            val userDetails =
                MyApplication.DATABASE_INSTANCE.userRepoDetailsDAO()
                    .getUserDetails(username.lowercase(Locale.getDefault()))
            if (userDetails.isNotEmpty()) {
                UserResponseResult.Success(
                    UserDetails(
                        name = userDetails[0].name,
                        avatarUrl = userDetails[0].avatarUrl
                    )
                )
            } else {
                UserResponseResult.NoInternetConnection
            }
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
            val repoList = api.getUserRepoDetails(username)
            MyApplication.DATABASE_INSTANCE.userRepoDetailsDAO().insertAllRepoDetails(
                repoList.toEntityList(username)
            )
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