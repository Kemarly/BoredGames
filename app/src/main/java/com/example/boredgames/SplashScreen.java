package com.example.boredgames;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private Animation zoomInAnimation;
    private Animation zoomOutAnimation;
    private Animation fadeOutAnimation;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        zoomOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        img = findViewById(R.id.splash1);

        img.setScaleX(1.0f);
        img.setScaleY(1.0f);
        img.startAnimation(zoomInAnimation);

        zoomInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                img.startAnimation(fadeOutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                img.setImageResource(R.drawable.splash2);
                img.startAnimation(zoomOutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        zoomOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashScreen.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}
