package com.example.boredgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.widget.ImageButton;
import android.os.CountDownTimer;

public class ScriptScavenger extends AppCompatActivity {

    ImageButton HomeButton;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_scavenger);
        resetTimer();
        timer.start();
        HomeButton = (ImageButton) findViewById(R.id.imageView7);

        HomeButton.setOnClickListener(v -> GoHome());
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
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {showScore();}

        };
    }
    private void showScore(){}
}
