package com.example.myretrofitproject.Main.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity (tableName = "repo_details_table")
data class RepoDetailsEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @SerializedName("login")
    val username: String?,
    @SerializedName("name")
    val repoName: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    @SerializedName("forks")
    val forks: Int?

)
