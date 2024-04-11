package com.example.boredgames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.os.CountDownTimer;

public class ScriptScavenger extends AppCompatActivity {

    ImageButton homebutton;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_scavenger);
        resetTimer();
        timer.start();
        homebutton = (ImageButton) findViewById(R.id.imageView7);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoHome();
            }
        });
        if(timer != null) {timer.cancel();}
        assert timer != null;
        timer.start();
    }

    public void GoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void resetTimer()
    {
        timer = new CountDownTimer(60000, 6000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() { }

        };
    }
}