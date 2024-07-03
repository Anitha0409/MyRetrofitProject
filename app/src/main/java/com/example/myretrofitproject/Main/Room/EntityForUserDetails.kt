package com.example.myretrofitproject.Main.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "user_details_table")
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?
)
