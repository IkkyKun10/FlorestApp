package com.riezki.florestapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.riezki.florestapp.core.RetrofitService
import com.riezki.florestapp.core.models.News
import com.riezki.florestapp.core.repository.NewsRepository
import com.riezki.florestapp.databinding.FragmentHomeBinding
import com.riezki.florestapp.ui.base.BaseFragment
import com.riezki.florestapp.ui.MyViewModelFactory
import com.riezki.florestapp.ui.detail_news.DetailNewsActivity
import com.riezki.florestapp.ui.home.adapters.NewsAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    lateinit var viewModel: HomeViewModel
    private val adapter = NewsAdapter()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = NewsRepository(retrofitService)

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = adapter
        adapter.setContext(requireContext())

        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(mainRepository))[HomeViewModel::class.java]
        viewModel.newsList.observe(requireActivity()) {
            adapter.setNewsList(it.articles.toList())
        }

        viewModel.errorMessage.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(requireActivity(), Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getTopHeadlinesNews()
        adapter.setOnClickItems(object : NewsAdapter.OnClickItemsListener{
            override fun onItemClick(news: News) {
                viewModel.setSelectedItems(news)

                val intent = Intent(requireActivity(), DetailNewsActivity::class.java)
                intent.putExtra("data", Gson().toJson(news));
                startActivity(intent)
            }
        })
    }
}