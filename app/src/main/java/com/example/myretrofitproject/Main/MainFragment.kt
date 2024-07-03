package com.example.myretrofitproject.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitproject.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: GithubViewModel
    private lateinit var repoAdapter: GithubAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRepoRecyclerView()

        viewModel = ViewModelProvider(this)[GithubViewModel::class.java]

        binding.btnSearch.setOnClickListener {
            searchUser()
        }

        viewModel.repoLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                when(it) {
                    is GithubViewModel.ViewState.Loading -> {
                        repoAdapter.submitList(emptyList())
                        binding.errorText.visibility = View.GONE
                        showLoading(true)
                    }
                    is GithubViewModel.ViewState.Data -> {
                        repoAdapter.submitList(it.repoList)
                        binding.errorText.visibility = View.GONE
                        showLoading(false)
                    }
                    is GithubViewModel.ViewState.Error -> {
                        repoAdapter.submitList(emptyList())
                        showLoading(false)
                        binding.errorText.visibility = View.VISIBLE
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
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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
