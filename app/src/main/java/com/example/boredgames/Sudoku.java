package com.example.boredgames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Sudoku extends AppCompatActivity {

    ImageButton HomeButton;
    CountDownTimer timer;
    private TextView[][] grid;
    private int [][] answers;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        HomeButton = (ImageButton) findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(v -> GoHome());

        grid = new TextView[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

            }
        }
        //makeGrid();
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
            public void onTick(long millisUntilFinished)
            {
                elapsedTime += 1000;
                TextView timerTextView = findViewById(R.id.Time);
                timerTextView.setText(String.valueOf(elapsedTime / 1000));
            }
            @Override
            public void onFinish() { }
        }.start();
    }
    private void makeGrid(){}
    private void userAnswers(TextView num){}
}