package com.example.myretrofitproject.Main.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitproject.Model.RepoViewItem
import com.example.myretrofitproject.databinding.RepodetailsRvlayoutBinding

class RepoViewHolder(private val binding: RepodetailsRvlayoutBinding)
    : RecyclerView.ViewHolder(binding.root) {

        fun bind(repoItem: RepoViewItem) {
            binding.tvRepoName.text = repoItem.name
            binding.tvRepoDescription.text=repoItem.repoDescription
        }
}