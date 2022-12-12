package com.riezki.florestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riezki.florestapp.databinding.ItemPostCommentBinding
import com.riezki.florestapp.core.entity.CommentItem

class CommentsAdapter(private val listComments: ArrayList<CommentItem>): RecyclerView.Adapter<CommentsAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemPostCommentBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateCommentlistItems(comments: List<CommentItem>){
        listComments.clear()
        listComments.addAll(comments)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPostCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val commentsItem = listComments[position]
        holder.binding.imgUserCommentator.setImageDrawable(commentsItem.photoProfile)
        holder.binding.tvCommentUsername.text = commentsItem.username
        holder.binding.tvComment.text = commentsItem.comment
    }

    override fun getItemCount(): Int = listComments.size

}