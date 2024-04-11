package com.example.boredgames;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.os.CountDownTimer;

public class Sudoku extends AppCompatActivity {

    ImageButton homebutton;
    CountDownTimer timer;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        homebutton = (ImageButton) findViewById(R.id.HomeButton);

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
        timer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            long elapsedTime = 0;
            @Override
            public void onTick(long millisUntilFinished) {elapsedTime += 1000;}

            @Override
            public void onFinish() { }

        };
    }
}