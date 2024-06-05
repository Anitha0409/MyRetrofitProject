package com.example.myretrofitproject.Main.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserDetailsEntity::class,RepoDetailsEntity::class], version = 1)
abstract class UserRepoDatabase: RoomDatabase() {
    abstract fun userRepoDetailsDAO(): UserRepoDetailsDAO
    companion object{

        @Volatile
        private var INSTANCE: UserRepoDatabase? = null
        fun getInstance(context:Context): UserRepoDatabase{

            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserRepoDatabase::class.java,
                        "user_repo_details_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}