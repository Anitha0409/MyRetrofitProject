package com.example.myretrofitproject.Main.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myretrofitproject.Model.UserViewItem
import com.example.myretrofitproject.databinding.UserdetailsRvlayoutBinding

class UserItemViewHolder(private val binding: UserdetailsRvlayoutBinding)
    : RecyclerView.ViewHolder(binding.root) {

        fun bind(userItem: UserViewItem) {
            binding.tvUserProfileName.text = userItem.name
            Glide.with(binding.root.context).load(userItem.avatarUrl).into(binding.ivUserProfile)
        }
}