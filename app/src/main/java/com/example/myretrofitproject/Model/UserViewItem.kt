package com.example.myretrofitproject.Model

import com.example.myretrofitproject.Main.GithubAdapter

data class UserViewItem(
    override val name: String,
    val avatarUrl: String,
    override val viewTypeEnum: GithubAdapter.ItemViewTypeEnum = GithubAdapter.ItemViewTypeEnum.USER_TYPE
) : BaseUserRepoViewItem(viewTypeEnum, name)