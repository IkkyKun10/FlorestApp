package com.riezki.florestapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.riezki.florestapp.core.entity.TipsContentEntity
import com.riezki.florestapp.databinding.RowItemBinding

class PostTipsAdapter(private val onItemClick: (TipsContentEntity) -> Unit?) : ListAdapter<TipsContentEntity, PostTipsAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TipsContentEntity>() {
            override fun areItemsTheSame(oldItem: TipsContentEntity, newItem: TipsContentEntity): Boolean =
                oldItem.idTips == newItem.idTips


            override fun areContentsTheSame(oldItem: TipsContentEntity, newItem: TipsContentEntity): Boolean =
                oldItem == newItem

        }
    }

    class ViewHolder(private val binding: RowItemBinding, val onItemClick: (TipsContentEntity) -> Unit?)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(tipsItem: TipsContentEntity) {
            with(binding) {
                tvJudul.text = tipsItem.titleTips.toString()
                tvDeskripsi.text = tipsItem.descTips.toString()

                itemView.setOnClickListener {
                    onItemClick(tipsItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}