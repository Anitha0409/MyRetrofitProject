package com.example.myretrofitproject.Main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.Orientation
import com.example.myretrofitproject.R
import com.example.myretrofitproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: GithubViewModel
    private lateinit var repoAdapter: GithubAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        prepareRepoRecyclerView()

        viewModel = ViewModelProvider(this)[GithubViewModel::class.java]
        binding.btnSearch.setOnClickListener {
            searchUser()
            getRepodetails()
        }

        viewModel.repoLiveData.observe(this) {
            repoList -> repoAdapter.setRepoList(repoList)
            showLoading(false)

        }
    }

    private fun searchUser(){
        val username = binding.etUserInput.text.toString()
        if (username.isNotEmpty()){
            showLoading(true)
            viewModel.setUserDetails(username)
        }
    }

    private fun getRepodetails(){
        val username = binding.etUserInput.text.toString()
        if(username.isNotEmpty()) {
            showLoading(true)
            viewModel.getRepoDetails(username)
        }
    }
        private fun prepareRepoRecyclerView() {
            repoAdapter = GithubAdapter()
            binding.rvRepoDetails.apply {
                layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
                adapter = repoAdapter
            }
        }

    private fun showLoading(state : Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}