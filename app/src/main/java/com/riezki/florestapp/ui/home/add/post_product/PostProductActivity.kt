package com.riezki.florestapp.ui.home.add.post_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riezki.florestapp.R
import com.riezki.florestapp.databinding.ActivityPostProductBinding

class PostProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}