package com.example.myretrofitproject.Main.util

import androidx.recyclerview.widget.DiffUtil
import com.example.myretrofitproject.Main.GithubAdapter
import com.example.myretrofitproject.Model.BaseUserRepoViewItem
import com.example.myretrofitproject.Model.RepoViewItem
import com.example.myretrofitproject.Model.UserViewItem

class RepoItemDiffUtil : DiffUtil.ItemCallback<BaseUserRepoViewItem>() {

    override fun areContentsTheSame(
        oldItem: BaseUserRepoViewItem,
        newItem: BaseUserRepoViewItem
    ): Boolean
    {
        return oldItem.viewTypeEnum == newItem.viewTypeEnum
                && oldItem.name == newItem.name
    }

    override fun areItemsTheSame(
        oldItem: BaseUserRepoViewItem,
        newItem: BaseUserRepoViewItem
    ): Boolean {
        return oldItem.viewTypeEnum == newItem.viewTypeEnum
                && oldItem.name == newItem.name
    }
}