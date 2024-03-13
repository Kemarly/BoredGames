package com.example.boredgames

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var zoom: Animation
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        zoom = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom)
        img = findViewById(R.id.splash1)
        img.startAnimation(zoom)

        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, SplashScreen2::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }
}
