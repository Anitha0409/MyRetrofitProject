package com.example.myretrofitproject.Model

import com.example.myretrofitproject.Main.GithubAdapter

abstract class BaseUserRepoViewItem(
    open val viewTypeEnum: GithubAdapter.ItemViewTypeEnum,
    open val name: String
)