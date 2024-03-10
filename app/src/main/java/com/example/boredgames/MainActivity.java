package com.example.boredgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.net.InetSocketAddress;

public class MainActivity extends AppCompatActivity {

    ImageButton triviaButton;
    ImageButton scriptscavButton;

    ImageButton HangmanButton;

    ImageButton SudokuButton;

    ImageButton SettingsButton;

    ImageButton AchievementButton;

    ImageButton ProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triviaButton = (ImageButton) findViewById(R.id.imageButton);

        triviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrivia();
            }
        });

        scriptscavButton = (ImageButton) findViewById(R.id.imageView2);

        scriptscavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScriptScav();
            }
        });


        HangmanButton = (ImageButton) findViewById(R.id.imageView3);

        HangmanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHangman();
            }
        });

        SudokuButton = (ImageButton) findViewById(R.id.imageView4);

        SudokuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSudoku();
            }
        });

        SettingsButton = (ImageButton) findViewById(R.id.imageView5);

        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        AchievementButton = (ImageButton) findViewById(R.id.TrophyIcon);

        AchievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAchievements();
            }
        });

        ProfileButton = (ImageButton) findViewById(R.id.imageView8);

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

    }

    public void openTrivia(){
        Intent intent = new Intent(this, Trivia.class);
        startActivity(intent);
    }

    public void openScriptScav(){
        Intent intent = new Intent(this, ScriptScavenger.class);
        startActivity(intent);
    }

    public void openHangman(){
        Intent intent = new Intent(this, Hangman.class);
        startActivity(intent);
    }

    public void openSudoku(){
        Intent intent = new Intent(this, Sudoku.class);
        startActivity(intent);
    }

    public void openSettings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openAchievements(){
        Intent intent = new Intent(this, Achievements.class);
        startActivity(intent);
    }

    public void openProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

}