package com.riezki.florestapp.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riezki.florestapp.R
import com.riezki.florestapp.core.models.Articles
import com.riezki.florestapp.databinding.AdapterNewsBinding
import com.riezki.florestapp.core.models.News

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private var newsList = mutableListOf<News>();
    private var onClickItemsListener: OnClickItemsListener? = null
    private var context: Context? = null

    fun setContext(context: Context){
        this.context = context
    }

    @JvmName("setNewsList1")
    fun setNewsList(newsList: List<News>) {
        this.newsList = newsList.toMutableList()
        notifyDataSetChanged()
    }

    fun setOnClickItems(onClickItemsListener: OnClickItemsListener){
        this.onClickItemsListener = onClickItemsListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.binding.tvPublishNews.text = news.publishDate
        holder.binding.tvTitle.text = news.title
        holder.binding.tvDesc.text = news.desc

        context?.let {
            Glide.with(it).load(news.imgUrl)
                .placeholder(R.drawable.ic_dashboard_black_24dp)
                .error(R.drawable.ic_dashboard_black_24dp)
                .into(holder.binding.imgNews);
        }

        holder.binding.lyParent.setOnClickListener{
            if(onClickItemsListener != null){
                onClickItemsListener!!.onItemClick(news)
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    interface OnClickItemsListener{
        fun onItemClick(news: News)
    }
}

class NewsViewHolder(val binding: AdapterNewsBinding) : RecyclerView.ViewHolder(binding.root) {

}