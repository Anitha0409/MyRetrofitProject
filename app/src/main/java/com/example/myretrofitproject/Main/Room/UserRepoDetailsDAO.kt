package com.example.myretrofitproject.Main.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserRepoDetailsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(userDetails: UserDetailsEntity)

    @Query("SELECT * FROM user_details_table")
    fun fetchUserDetails():List<UserDetailsEntity>

    @Query("SELECT * FROM user_details_table WHERE name = :username")
    fun getUserDetails(username: String): List<UserDetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRepoDetails(repoDetails: List<RepoDetailsEntity>)

    @Query("SELECT * FROM repo_details_table")
    fun fetchRepoDetails(): List<RepoDetailsEntity>
}