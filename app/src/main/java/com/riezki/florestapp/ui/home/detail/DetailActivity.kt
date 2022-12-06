package com.riezki.florestapp.ui.home.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.riezki.florestapp.R
import com.riezki.florestapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

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

        val imgSwitcher = binding.imgSw

        imgSwitcher.setFactory {
            val imgView = ImageView(this)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView
        }

        imgSwitcher.setImageResource(sampleImages[index])

        val imgIn = AnimationUtils.loadAnimation(
            this, android.R.anim.slide_in_left)
        imgSwitcher.inAnimation = imgIn

        val imgOut = AnimationUtils.loadAnimation(
            this, android.R.anim.slide_out_right)
        imgSwitcher.outAnimation = imgOut

        // previous button functionality
        val prev = binding.prev
        prev.setOnClickListener {
            index = if (index - 1 >= 0) index - 1 else 2
            imgSwitcher.setImageResource(sampleImages[index])
        }
        // next button functionality
        val next = binding.next
        next.setOnClickListener {
            index = if (index + 1 < sampleImages.size) index +1 else 0
            imgSwitcher.setImageResource(sampleImages[index])
        }
    }
}