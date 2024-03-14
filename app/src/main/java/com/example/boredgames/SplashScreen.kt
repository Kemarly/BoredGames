package com.example.boredgames

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    private lateinit var zoomInAnimation: Animation
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        zoomInAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom)
        img = findViewById(R.id.splash1)
        img.startAnimation(zoomInAnimation)

        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000) // Delay for 2 seconds
    }
}
