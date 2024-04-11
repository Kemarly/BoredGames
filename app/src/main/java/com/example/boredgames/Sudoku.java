package com.example.boredgames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Sudoku extends AppCompatActivity {

    ImageButton HomeButton;
    CountDownTimer timer;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        HomeButton = (ImageButton) findViewById(R.id.HomeButton);

        HomeButton.setOnClickListener(v -> GoHome());
        resetTimer();
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