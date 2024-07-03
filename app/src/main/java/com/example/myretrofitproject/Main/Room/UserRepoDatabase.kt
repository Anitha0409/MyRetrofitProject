package com.example.myretrofitproject.Main.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserDetailsEntity::class,RepoDetailsEntity::class], version = 1)
abstract class UserRepoDatabase: RoomDatabase() {
    abstract fun userRepoDetailsDAO(): UserRepoDetailsDAO
}