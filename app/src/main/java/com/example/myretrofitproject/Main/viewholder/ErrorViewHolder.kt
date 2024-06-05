package com.example.myretrofitproject.Main.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitproject.Model.ErrorViewItem
import com.example.myretrofitproject.databinding.ErrordetailsBinding

class ErrorViewHolder(private val binding: ErrordetailsBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(errorItem: ErrorViewItem){
            binding.tvError.text = errorItem.name
        }
    }