package com.example.myretrofitproject.Main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myretrofitproject.Interface.RetrofitClientObject
import com.example.myretrofitproject.Model.UserDetails
import com.example.myretrofitproject.Model.UserRepoDetailsClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class GithubViewModel: ViewModel() {

    private var repoLiveData = MutableLiveData<UserRepoDetailsClass>()
    private var userLiveData = MutableLiveData<UserDetails>()
    fun setUserDetails(username: String){
        RetrofitClientObject.api.getUserDetails(username)
            .enqueue(object : Callback<UserDetails>{
            override fun onResponse(
                call: Call<UserDetails>,
                response: Response<UserDetails>
            ) {
                if(response.body()!=null){
                    userLiveData.value = response.body()
                }else{
                    return
                }
            }
                override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                    Log.d("TAG",t.message.toString())
                }

            })

        }

    fun getRepoDetails(username: String){
        RetrofitClientObject.api.getUserRepoDetails(username).enqueue(object :Callback<UserRepoDetailsClass>{

            override fun onResponse(
                call: Call<UserRepoDetailsClass>,
                response: Response<UserRepoDetailsClass>
            ) {
                    if(response.body()!=null){
                        repoLiveData.postValue(response.body())
                    }else{
                        return
                    }

                    }

            override fun onFailure(call: Call<UserRepoDetailsClass>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }

        }
        )}

    fun observeRepoLiveData() : LiveData <UserRepoDetailsClass>{
        return repoLiveData
    }

    fun observeUserLiveData() : LiveData<UserDetails>{
        return userLiveData
    }

}
