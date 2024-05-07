package com.example.myretrofitproject.Main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myretrofitproject.R
import com.example.myretrofitproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private var binding:ActivityMainBinding?= null
    private var viewModel: GithubViewModel?= null
    private var userAdapter: GithubAdapter2? =null
    private var repoAdapter: GithubAdapter? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Implementing the steps for the ViewModel

        // Is this the right order of steps?

        prepareUserRecyclerView()
        prepareRepoRecyclerView()

        viewModel = ViewModelProvider(this)[GithubViewModel::class.java]
        binding?.btnSearch?.setOnClickListener {
            searchUser()
            getRepodetails()
        }

        // Where should I place this piece of code?
        viewModel?.observeUserLiveData()!!.observe(
            this, Observer {
                    userList-> userAdapter?.setUserList(userList)
            })

        viewModel?.observeRepoLiveData()!!.observe(
            this, Observer {
                    repoList -> repoAdapter?.setRepoList(repoList)
            }
        )
    }

    private fun searchUser(){
        binding?.apply {
            val username = etUserInput.text.toString()
            if (username.isNotEmpty()){
                viewModel?.setUserDetails(username)
            }
        }
    }

    private fun getRepodetails(){
        binding?.apply {
            val username = etUserInput.text.toString()
            if(username.isNotEmpty()) {
                viewModel!!.getRepoDetails(username)
            }
        }
    }
        private fun prepareUserRecyclerView() {
            userAdapter = GithubAdapter2()
            binding!!.rvUserDetails.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = userAdapter
            }

        }

        private fun prepareRepoRecyclerView() {
            repoAdapter = GithubAdapter()
            binding!!.rvRepoDetails.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = repoAdapter
            }
        }
}