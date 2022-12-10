package com.riezki.florestapp.ui.home.detail

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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

        supportActionBar?.hide()

        setImageSwitcher()
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
}