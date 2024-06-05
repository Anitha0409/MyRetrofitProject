package com.example.myretrofitproject.Model

import com.example.myretrofitproject.Main.GithubAdapter

data class ErrorViewItem(
    override val name: String,
    override val viewTypeEnum : GithubAdapter.ItemViewTypeEnum = GithubAdapter.ItemViewTypeEnum.ERROR_TYPE
):BaseUserRepoViewItem(viewTypeEnum,name)
