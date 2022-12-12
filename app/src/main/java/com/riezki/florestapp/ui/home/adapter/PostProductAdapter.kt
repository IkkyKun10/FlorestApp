package com.riezki.florestapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.riezki.florestapp.core.entity.ProductItem
import com.riezki.florestapp.databinding.RowItemBinding

class PostProductAdapter(private val onItemClick: (ProductItem) -> Unit?) : ListAdapter<ProductItem, PostProductAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductItem>() {
            override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean =
                oldItem.idProduct == newItem.idProduct


            override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean =
                oldItem == newItem

        }
    }

    class ViewHolder(private val binding: RowItemBinding, val onItemClick: (ProductItem) -> Unit?)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(productItem: ProductItem) {
            with(binding) {
                tvJudul.text = productItem.titleProduct.toString()
                tvDeskripsi.text = productItem.desc.toString()

                itemView.setOnClickListener {
                    onItemClick(productItem)
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