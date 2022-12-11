package com.riezki.florestapp.ui.home.detail

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.florestapp.R
import com.riezki.florestapp.adapter.CommentsAdapter
import com.riezki.florestapp.databinding.ActivityDetailTipsBinding
import com.riezki.florestapp.core.entity.CommentItem

class DetailTipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTipsBinding
    private lateinit var commentsAdapter: CommentsAdapter
    private var comment: String? = null
    private var dummyProfileImage: Drawable? = null
    private lateinit var dummyListComments: ArrayList<CommentItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        dummyProfileImage = ContextCompat.getDrawable(this@DetailTipsActivity, R.drawable.ic_baseline_account_circle_24)

        dummyListComments = arrayListOf(
            CommentItem(photoProfile = dummyProfileImage!!, username = "Username", comment = "comment"),
            CommentItem(photoProfile = dummyProfileImage!!, username = "Username", comment = "comment"),
            CommentItem(photoProfile = dummyProfileImage!!, username = "Username", comment = "comment"),
            CommentItem(photoProfile = dummyProfileImage!!, username = "Username", comment = "comment")
        )

        commentsAdapter = CommentsAdapter(arrayListOf())

        binding.btnSend.isEnabled = false
        binding.btnSend.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))

        getCommentList(dummyListComments)
        addComment()
    }

    private fun getCommentList(comments: List<CommentItem>) {
        binding.rvComment.layoutManager = LinearLayoutManager(this)

        commentsAdapter.updateCommentlistItems(comments)
        binding.rvComment.adapter = commentsAdapter
    }

    private fun addComment(){
        binding.edtComment.doOnTextChanged{ text, _, _, _ ->
            comment = text.toString()
            if(!text.isNullOrEmpty()){
                binding.btnSend.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green))
                binding.btnSend.isEnabled = true
            }else{
                binding.btnSend.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))
                binding.btnSend.isEnabled = false
            }
        }

        binding.btnSend.setOnClickListener{
            if(comment!=null){
                dummyListComments.add(0, CommentItem(
                    photoProfile = dummyProfileImage!!,
                    "Username",
                    comment = comment!!
                )
                )
                getCommentList(dummyListComments)
                binding.edtComment.text?.clear()
            }
        }
    }
}