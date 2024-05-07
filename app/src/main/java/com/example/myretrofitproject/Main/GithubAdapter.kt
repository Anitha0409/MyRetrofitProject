package com.example.myretrofitproject.Main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitproject.Model.UserRepoDetailsClass
import com.example.myretrofitproject.databinding.RepodetailsRvlayoutBinding

class GithubAdapter: RecyclerView.Adapter<GithubAdapter.ViewHolder>()
{
// What is the use of this function?
    private var repoList = UserRepoDetailsClass()
    fun setRepoList(repoList: UserRepoDetailsClass){
        this.repoList= repoList
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding:RepodetailsRvlayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

           val binding= RepodetailsRvlayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return repoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvRepoName.text = repoList[position].name
        holder.binding.tvRepoDescription.text = repoList[position].description
    }


}