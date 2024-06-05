package com.example.myretrofitproject.Main.Room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserRepoDetailsDAO {
    @Insert
    suspend fun insertUserDetails(getUserDetails: UserDetailsEntity)
    @Query("SELECT * FROM `user_details_table`")
    fun fetchUserDetails():LiveData<List<UserDetailsEntity>>

    @Insert
    suspend fun insertRepoDetails(getRepoDetails: RepoDetailsEntity)
    @Query("SELECT * FROM `repo_details_table`")
    fun fetchRepoDetails(): LiveData<List<RepoDetailsEntity>>
}