package com.example.myretrofitproject.Main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitproject.Main.util.RepoItemDiffUtil
import com.example.myretrofitproject.Main.viewholder.ErrorViewHolder
import com.example.myretrofitproject.Main.viewholder.RepoViewHolder
import com.example.myretrofitproject.Main.viewholder.UserItemViewHolder
import com.example.myretrofitproject.Model.BaseUserRepoViewItem
import com.example.myretrofitproject.Model.ErrorViewItem
import com.example.myretrofitproject.Model.RepoViewItem
import com.example.myretrofitproject.Model.UserViewItem
import com.example.myretrofitproject.databinding.ErrordetailsBinding
import com.example.myretrofitproject.databinding.RepodetailsRvlayoutBinding
import com.example.myretrofitproject.databinding.UserdetailsRvlayoutBinding

class GithubAdapter: ListAdapter<BaseUserRepoViewItem, RecyclerView.ViewHolder>(RepoItemDiffUtil())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ItemViewTypeEnum.USER_TYPE.type) {
            val binding = UserdetailsRvlayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            UserItemViewHolder(binding)
        }

       else if(viewType == ItemViewTypeEnum.REPO_TYPE.type){
            val binding = RepodetailsRvlayoutBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
            RepoViewHolder(binding)
        }
        else{
            val binding = ErrordetailsBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
            ErrorViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).viewTypeEnum == ItemViewTypeEnum.USER_TYPE) {
            ItemViewTypeEnum.USER_TYPE.type
        } else if(getItem(position).viewTypeEnum == ItemViewTypeEnum.REPO_TYPE) {
            ItemViewTypeEnum.REPO_TYPE.type
        }else{
            ItemViewTypeEnum.ERROR_TYPE.type
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItem(position).viewTypeEnum == ItemViewTypeEnum.USER_TYPE) {
            (holder as UserItemViewHolder).bind(getItem(position) as UserViewItem)

        } else if(getItem(position).viewTypeEnum == ItemViewTypeEnum.REPO_TYPE) {
            (holder as RepoViewHolder).bind(getItem(position) as RepoViewItem)
        }
        else{
            (holder as ErrorViewHolder).bind(getItem(position) as ErrorViewItem)
        }
    }

    enum class ItemViewTypeEnum(val type: Int) {
        USER_TYPE (0),
        REPO_TYPE (1),
        ERROR_TYPE(3)
    }

}