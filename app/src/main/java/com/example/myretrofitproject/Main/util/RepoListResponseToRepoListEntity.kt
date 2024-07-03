package com.example.myretrofitproject.Main.util

import com.example.myretrofitproject.Main.Room.RepoDetailsEntity
import com.example.myretrofitproject.Model.UserRepoDetails

object RepoListResponseToRepoListEntity {

    fun List<UserRepoDetails>.toEntityList(username: String) : List<RepoDetailsEntity> {
        val returnList = ArrayList<RepoDetailsEntity>()
        forEach { userRepo ->
            returnList.add(
                RepoDetailsEntity(
                    username = username,
                    repoName = userRepo.name,
                    description = userRepo.description,
                    updatedAt = null,
                    stargazersCount = null,
                    forks = null
                )
            )
        }
        return returnList
    }
}