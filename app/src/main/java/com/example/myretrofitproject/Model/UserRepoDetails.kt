package com.example.myretrofitproject.Model


class UserRepoDetailsClass: ArrayList<UserRepoDetailsClass.UserRepoDetails>() // Is this the right way?

{
    data class UserRepoDetails(
        val name: String,
        val description: String
    )

}

