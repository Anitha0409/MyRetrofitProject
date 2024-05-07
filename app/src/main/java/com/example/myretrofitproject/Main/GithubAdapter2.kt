package com.example.myretrofitproject.Main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myretrofitproject.Model.UserDetails
import com.example.myretrofitproject.databinding.UserdetailsRvlayoutBinding

 class GithubAdapter2:RecyclerView.Adapter<GithubAdapter2.ViewHolder>() {


     private var userList= ArrayList<UserDetails>()

     fun setUserList(userList: List<UserDetails>){
        this.userList= userList as ArrayList<UserDetails>
         notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: UserdetailsRvlayoutBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          val binding =  UserdetailsRvlayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        return ViewHolder(binding)
    }

     override fun getItemCount(): Int {
         return userList.size
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvUserProfileName.text = userList[position].name
        Glide.with(holder.itemView)
            .load(userList[position].avatar_url)
            .into(holder.binding.ivUserProfile)
        }
    }
