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
    private lateinit var zoomOutAnimation: Animation
    private lateinit var fadeOutAnimation: Animation
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        zoomInAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom)
        zoomOutAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out)
        fadeOutAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade)
        img = findViewById(R.id.splash1)

        img.scaleX = 1.0f
        img.scaleY = 1.0f
        img.startAnimation(zoomInAnimation)

        zoomInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                img.startAnimation(fadeOutAnimation)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                img.setImageResource(R.drawable.splash6)
                img.startAnimation(zoomOutAnimation)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        zoomOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@SplashScreen, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                img.setImageResource(R.drawable.splash5)
                img.startAnimation(zoomOutAnimation)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        zoomOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@SplashScreen, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                img.setImageResource(R.drawable.splash4)
                img.startAnimation(zoomOutAnimation)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        zoomOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@SplashScreen, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                img.setImageResource(R.drawable.splash3)
                img.startAnimation(zoomOutAnimation)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        zoomOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@SplashScreen, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                img.setImageResource(R.drawable.splash2)
                img.startAnimation(zoomOutAnimation)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })


    }
}