package com.riezki.florestapp.ui.detail_news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riezki.florestapp.R
import com.riezki.florestapp.core.models.News
import com.riezki.florestapp.databinding.ActivityDetailNewsBinding
import com.riezki.florestapp.ui.base.BaseActivity
import com.riezki.florestapp.ui.home.HomeViewModel
import java.lang.reflect.Type
import java.util.*


class DetailNewsActivity : BaseActivity<ActivityDetailNewsBinding>() {

    lateinit var viewModel: HomeViewModel

    override fun getViewBinding() = ActivityDetailNewsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title ="Detail Berita"
            it.setDisplayHomeAsUpEnabled(true)
        }

        val data = intent.getStringExtra("data")
        if (data != null) {
            val type: Type = object : TypeToken<News>() {}.type
            val news: News = Gson().fromJson(data, type)

            binding.tvTitle.text = news.title
            binding.tvDesc.text = news.desc
            binding.tvPublishNews.text = news.publishDate

            Glide.with(this).load(news.imgUrl)
                .placeholder(R.drawable.ic_dashboard_black_24dp)
                .error(R.drawable.ic_dashboard_black_24dp)
                .into(binding.imgNews);

            binding.btnUrl.setOnClickListener {
                val uriUrl = Uri.parse(news.url)
                val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                startActivity(launchBrowser)
            }
        }
    }

}