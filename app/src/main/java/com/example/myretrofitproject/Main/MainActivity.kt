package com.example.myretrofitproject.Main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
        }

        viewModel.repoLiveData.observe(this) {
            if (it != null) {
                when(it) {
                    is GithubViewModel.ViewState.Loading -> {
                        repoAdapter.submitList(emptyList())
                        binding.errorText.visibility = GONE
                        showLoading(true)
                    }
                    is GithubViewModel.ViewState.Data -> {
                        repoAdapter.submitList(it.repoList)
                        binding.errorText.visibility = GONE
                        showLoading(false)
                    }
                    is GithubViewModel.ViewState.Error -> {
                        repoAdapter.submitList(emptyList())
                        showLoading(false)
                        binding.errorText.visibility = VISIBLE
                        binding.errorText.text = it.error
                    }
                }
            }
        }
    }

    private fun searchUser(){
        val username = binding.etUserInput.text.toString()
        if (username.isNotEmpty()){
            viewModel.setUserDetails(username)
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