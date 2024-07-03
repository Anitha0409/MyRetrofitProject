package com.example.myretrofitproject.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myretrofitproject.Main.repo.GithubRepo
import com.example.myretrofitproject.Model.BaseUserRepoViewItem
import com.example.myretrofitproject.Model.ErrorViewItem
import com.example.myretrofitproject.Model.RepoResponseResult
import com.example.myretrofitproject.Model.RepoViewItem
import com.example.myretrofitproject.Model.UserResponseResult
import com.example.myretrofitproject.Model.UserViewItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GithubViewModel: ViewModel() {

    private var _repoLiveData = MutableLiveData<ViewState>()
    val repoLiveData: LiveData<ViewState> = _repoLiveData

    private var repo = GithubRepo()

    private val itemList = ArrayList<BaseUserRepoViewItem>()

    fun setUserDetails(username: String){
        viewModelScope.launch {
            _repoLiveData.value = ViewState.Loading
            val userDetailsResult = withContext(Dispatchers.IO) {
                repo.getUserData(username)
            }

            when(userDetailsResult) {
                is UserResponseResult.Success -> {
                    itemList.clear()
                    itemList.add(
                        UserViewItem(
                            userDetailsResult.userDetails.name ?: "",
                            userDetailsResult.userDetails.avatarUrl ?: ""
                        )
                    )
                    setRepoDetails(username)

                }

                is UserResponseResult.NoInternetConnection -> {
                    _repoLiveData.value = ViewState.Error("There is no internet connection")
                }

                is UserResponseResult.NetworkError -> {
                    when(userDetailsResult.code) {
                        400 -> {
                            _repoLiveData.value = ViewState.Error("Error getting the user info")
                        }

                        404 -> {
                            _repoLiveData.value = ViewState.Error("Check username and try again")
                        }

                        500 -> {
                            _repoLiveData.value = ViewState.Error("Try Again Later")
                        }

                        else -> {
                            _repoLiveData.value = ViewState.Error(userDetailsResult.message)
                        }
                    }
                }

                is UserResponseResult.OtherErrors -> {
                    _repoLiveData.value = ViewState.Error("You blew up the world..!")
                }
            }
        }
    }

    private fun setRepoDetails(username: String) {
        viewModelScope.launch {
            val repoListResult = withContext(Dispatchers.IO) {
                repo.getRepoDataForUsername(username)
            }

            when(repoListResult) {
                is RepoResponseResult.Success -> {
                    if (repoListResult.repoList.isNotEmpty()){
                        repoListResult.repoList.forEach {repo ->
                            itemList.add(
                                RepoViewItem(
                                    repo.name ?: "",
                                    repo.description ?: ""
                                )
                            )
                        }
                    } else {
                        itemList.add(
                            ErrorViewItem(
                                "No repos found for this user"
                            )
                        )
                    }

                    _repoLiveData.value = ViewState.Data(itemList)
                }

                is RepoResponseResult.NoInternetConnection ->{
                    //_repoLiveData.value = ViewState.Error("There is no internet connection")
                    itemList.add(
                        ErrorViewItem(
                            "There is no internet connection"
                        )
                    )
                    _repoLiveData.value = ViewState.Data(itemList)
                }

                is RepoResponseResult.NetworkError ->{
                    when(repoListResult.code) {
                        400 -> {
                            _repoLiveData.value = ViewState.Error("Error getting the user info")
                        }

                        404 -> {
                            itemList.add(
                                ErrorViewItem("Check username and try again")
                            )
                            _repoLiveData.value = ViewState.Data(itemList)
                        }

                        500 -> {
                            _repoLiveData.value = ViewState.Error("Try Again Later")
                        }

                        else -> {
                            _repoLiveData.value = ViewState.Error(repoListResult.message)
                        }
                    }

                }
                is RepoResponseResult.OtherErrors -> {
                    _repoLiveData.value = ViewState.Error("You blew up the world from repo..!")

                }

                is RepoResponseResult.Failure -> {
                    _repoLiveData.value = ViewState.Error("Error getting the repos")

                }

            }

        }
    }

    sealed class ViewState {
        data object Loading : ViewState()
        class Error(val error: String) : ViewState()
        class Data(val repoList: List<BaseUserRepoViewItem>) : ViewState()
    }
}
