package com.riezki.florestapp.ui.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.riezki.florestapp.R
import com.riezki.florestapp.ui.auth.AuthActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screnn)

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this@SplashScreenActivity, AuthActivity::class.java).also {
                startActivity(it)
            }
        }, 2000)
    }
}