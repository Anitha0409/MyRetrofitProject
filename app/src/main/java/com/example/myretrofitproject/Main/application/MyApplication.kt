package com.example.myretrofitproject.Main.application

import android.app.Application
import androidx.room.Room
import com.example.myretrofitproject.Main.Room.UserRepoDatabase

class MyApplication : Application() {

    companion object {
        lateinit var DATABASE_INSTANCE: UserRepoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        DATABASE_INSTANCE = Room.databaseBuilder(
                this,
                UserRepoDatabase::class.java,
                "user_repo_details_database"
            ).fallbackToDestructiveMigration().build()
        }
}