package com.example.myretrofitproject.Model

import com.example.myretrofitproject.Main.GithubAdapter

data class RepoViewItem(
    override val name: String,
    val repoDescription:String,
    override val viewTypeEnum: GithubAdapter.ItemViewTypeEnum = GithubAdapter.ItemViewTypeEnum.REPO_TYPE
): BaseUserRepoViewItem(viewTypeEnum, name)