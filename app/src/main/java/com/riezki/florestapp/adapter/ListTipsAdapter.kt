package com.riezki.florestapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riezki.florestapp.ListTipsData
import com.riezki.florestapp.R
import com.riezki.florestapp.databinding.RowItemBinding

class ListTipsAdapter(private val listTips: ArrayList<ListTipsData>): RecyclerView.Adapter<ListTipsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (judul, tips) = listTips[position]
        holder.binding.tvJudul.text = judul
        holder.binding.tvDeskripsi.text = tips
    }

    override fun getItemCount(): Int = listTips.size

    class ListViewHolder(var binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root)

}