package com.riezki.florestapp.ui.home.add.post_tips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riezki.florestapp.R
import com.riezki.florestapp.databinding.ActivityPostTipsBinding

class PostTipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostTipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}