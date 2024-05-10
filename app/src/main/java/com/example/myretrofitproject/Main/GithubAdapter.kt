package com.example.myretrofitproject.Main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitproject.Main.viewholder.RepoViewHolder
import com.example.myretrofitproject.Main.viewholder.UserItemViewHolder
import com.example.myretrofitproject.Model.BaseUserRepoViewItem
import com.example.myretrofitproject.Model.RepoViewItem
import com.example.myretrofitproject.Model.UserViewItem
import com.example.myretrofitproject.databinding.RepodetailsRvlayoutBinding
import com.example.myretrofitproject.databinding.UserdetailsRvlayoutBinding

class GithubAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
// What is the use of this function?
    private var repoList = ArrayList<BaseUserRepoViewItem>()
    fun setRepoList(repoList: List<BaseUserRepoViewItem>){
        this.repoList= repoList as ArrayList<BaseUserRepoViewItem>
        notifyItemChanged(0)
    }
    // inner class ViewHolder(val binding:RepodetailsRvlayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ItemViewTypeEnum.USER_TYPE.type) {
            val binding = UserdetailsRvlayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            UserItemViewHolder(binding)
        } else {
            val binding = RepodetailsRvlayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            RepoViewHolder(binding)
        }
    }

    override fun getItemCount() = repoList.size

    override fun getItemViewType(position: Int): Int {
        return if (repoList[position] is UserViewItem) {
            ItemViewTypeEnum.USER_TYPE.type
        } else {
            ItemViewTypeEnum.REPO_TYPE.type
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (repoList[position] is UserViewItem) {
            (holder as UserItemViewHolder).bind(repoList[position] as UserViewItem)
        } else {
            (holder as RepoViewHolder).bind(repoList[position] as RepoViewItem)
        }
    }

    enum class ItemViewTypeEnum(val type: Int) {
        USER_TYPE (0),
        REPO_TYPE (1)
    }
}