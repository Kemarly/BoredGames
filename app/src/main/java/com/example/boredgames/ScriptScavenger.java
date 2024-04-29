package com.example.boredgames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    TextView answers;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_scavenger);

        tickTime = findViewById(R.id.Time);
        resetTimer();
        timer.start();

        HomeButton = findViewById(R.id.homeIcon);
        HomeButton.setOnClickListener(v -> GoHome());

        generateWord = findViewById(R.id.generateWord);
        String randomWord = genRandom();
        generateWord.setText(randomWord);

        answers = findViewById(R.id.answers);
        userInput = findViewById(R.id.userInput);

        Button enterButton = findViewById(R.id.enter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = userInput.getText().toString();
                if (!inputText.isEmpty()) {
                    String currentAnswers = answers.getText().toString();
                    if (!currentAnswers.isEmpty()) {
                        currentAnswers += "\n";
                    }
                    currentAnswers += inputText;
                    answers.setText(currentAnswers);
                    userInput.setText("");
                }
            }
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
    private void showScore(){
        score = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        builder.setMessage("Your score: " + score);
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private String genRandom() {
        String[] words = {"onomatopoeia", "miscellaneous", "serendipity", "ostentatious", "infinitesimal"};
        Random rand = new Random();
        int index = rand.nextInt(words.length);
        return words[index];
    }
    private void tutorial(){}
}