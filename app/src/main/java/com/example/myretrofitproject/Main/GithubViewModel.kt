package com.example.myretrofitproject.Main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myretrofitproject.Interface.RetrofitClientObject
import com.example.myretrofitproject.Model.BaseUserRepoViewItem
import com.example.myretrofitproject.Model.RepoViewItem
import com.example.myretrofitproject.Model.UserDetails
import com.example.myretrofitproject.Model.UserRepoDetails
import com.example.myretrofitproject.Model.UserViewItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel: ViewModel() {

    private var _repoLiveData = MutableLiveData<List<BaseUserRepoViewItem>>()
    val repoLiveData: LiveData<List<BaseUserRepoViewItem>> = _repoLiveData

    val itemList = ArrayList<BaseUserRepoViewItem>()

    //private var userLiveData = MutableLiveData<UserDetails>()

    fun setUserDetails(username: String){
        viewModelScope.launch {
            RetrofitClientObject.api.getUserDetails(username)
                .enqueue(object : Callback<UserDetails> {
                    override fun onResponse(
                        call: Call<UserDetails>,
                        response: Response<UserDetails>
                    ) {
                        if (response.body() != null) {
                            //userLiveData.value = response.body()
                            itemList.add(
                                UserViewItem(
                                    response.body()?.name ?: "",
                                    response.body()?.avatarUrl ?: ""
                                )
                            )
                            _repoLiveData.value = itemList
                        } else {
                            return
                        }
                    }

                    override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                        Log.d("TAG", t.message.toString())
                    }

                })
        }
    }

    fun getRepoDetails(username: String) {
        viewModelScope.launch {
            RetrofitClientObject.api.getUserRepoDetails(username)
                .enqueue(object : Callback<List<UserRepoDetails>> {

                    override fun onResponse(
                        call: Call<List<UserRepoDetails>>,
                        response: Response<List<UserRepoDetails>>
                    ) {
                        if (response.body() != null) {
                            //repoLiveData.postValue(response.body())
                            response.body()?.let { items ->
                                items.forEach { item ->
                                    itemList.add(
                                        RepoViewItem(
                                            item.name ?: "",
                                            item.description?: ""
                                        )
                                    )
                                }
                                _repoLiveData.value = itemList
                            }
                        } else {
                            return
                        }
                    }

                    override fun onFailure(call: Call<List<UserRepoDetails>>, t: Throwable) {
                        Log.d("TAG", t.message.toString())
                    }

                }
                )
        }
    }

    /*fun observeRepoLiveData() : LiveData <UserRepoDetailsResponse>{
        return repoLiveData
    }

    fun observeUserLiveData() : LiveData<UserDetails>{
        return userLiveData
    }*/

}
