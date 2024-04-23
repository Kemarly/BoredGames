package com.example.boredgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Random;

public class ScriptScavenger extends AppCompatActivity {

    ImageButton HomeButton;
    CountDownTimer timer;
    EditText userInput;
    TextView generateWord;
    TextView tickTime;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_scavenger);

        tickTime = findViewById(R.id.Time);
        resetTimer();
        timer.start();

        HomeButton = (ImageButton) findViewById(R.id.imageView7);
        HomeButton.setOnClickListener(v -> GoHome());

        if(timer != null) {timer.cancel();}
        assert timer != null;
        timer.start();

        generateWord=findViewById(R.id.generateWord);
        String randomWord = genRandom();
        generateWord.setText(randomWord);

        userInput = findViewById(R.id.userInput);
        //String uInput = userInput.getText().toString();
        userInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String uInput = userInput.getText().toString();
                generateWord.append("\n"+ uInput);
                userInput.setText("");
                return true;
            }
            return false;
        });
    }

    public void GoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void resetTimer()
    {
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                tickTime.setText(String.valueOf(secondsRemaining));
            }
            @Override
            public void onFinish() {
                tickTime.setText("0");
                showScore();
            }
        }.start();
    }
    private void showScore(){}
    private String genRandom() {
        String[] words = {"onomatopoeia", "miscellaneous", "serendipity", "ostentatious", "infinitesimal"};
        Random rand = new Random();
        int index = rand.nextInt(words.length);
        return words[index];
    }
    private void tutorial(){}
    private void popUp(){}
}