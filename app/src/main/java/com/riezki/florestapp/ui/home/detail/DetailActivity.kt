package com.riezki.florestapp.ui.home.detail

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.florestapp.R
import com.riezki.florestapp.databinding.ActivityDetailBinding
import com.riezki.florestapp.model.CommentModel


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var commentsAdapter: CommentsAdapter
    private var comment: String? = null
    private var dummyProfileImage: Drawable? = null
    private lateinit var dummyListComments: ArrayList<CommentModel>

    private var sampleImages = intArrayOf(
        R.drawable.detail_post_image,
        R.drawable.detail_post_image2,
        R.drawable.detail_post_image3
    )
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        dummyProfileImage = ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_baseline_account_circle_24)

        dummyListComments = arrayListOf(
            CommentModel(photoProfile = dummyProfileImage!!, username = "Username", comment = "comment"),
            CommentModel(photoProfile = dummyProfileImage!!, username = "Username", comment = "comment"),
            CommentModel(photoProfile = dummyProfileImage!!, username = "Username", comment = "comment"),
            CommentModel(photoProfile = dummyProfileImage!!, username = "Username", comment = "comment")
        )

        commentsAdapter = CommentsAdapter(arrayListOf())

        binding.btnSend.isEnabled = false
        binding.btnSend.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))

        getCommentList(dummyListComments)
        setImageSwitcher()
        addComment()
    }

    private fun setImageSwitcher(){
        val imgSwitcher = binding.imgSw

        imgSwitcher.setFactory {
            val imgView = ImageView(this)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView
        }

        imgSwitcher.setImageResource(sampleImages[index])

        val prev = binding.prev
        prev.setOnClickListener {
            val imgIn = AnimationUtils.loadAnimation(
                this, android.R.anim.slide_in_left)
            imgSwitcher.inAnimation = imgIn

            val imgOut = AnimationUtils.loadAnimation(
                this, android.R.anim.slide_out_right)
            imgSwitcher.outAnimation = imgOut
            index = if (index - 1 >= 0) index - 1 else 2
            imgSwitcher.setImageResource(sampleImages[index])
        }

        val next = binding.next
        next.setOnClickListener {
            val imgIn = AnimationUtils.loadAnimation(
                this, R.anim.slide_in_right)
            imgSwitcher.inAnimation = imgIn

            val imgOut = AnimationUtils.loadAnimation(
                this, R.anim.slide_out_left)
            imgSwitcher.outAnimation = imgOut
            index = if (index + 1 < sampleImages.size) index +1 else 0
            imgSwitcher.setImageResource(sampleImages[index])
        }
    }

    private fun getCommentList(comments: List<CommentModel>) {
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
                dummyListComments.add(0, CommentModel(
                    photoProfile = dummyProfileImage!!,
                    "Username",
                    comment = comment!!
                ))
                getCommentList(dummyListComments)
                binding.edtComment.text?.clear()
            }
        }
    }
}