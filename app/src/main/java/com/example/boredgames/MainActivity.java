package com.example.boredgames;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton triviaButton;
    ImageButton scriptscavButton;
    ImageButton HangmanButton;
    ImageButton SudokuButton;
    ImageButton SettingsButton;
    ImageButton AchievementButton;
    ImageButton HomeButton;
    ImageButton ProfileButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        triviaButton = (ImageButton) findViewById(R.id.imageButton);

        triviaButton.setTooltipText("Trivia");

        triviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openTrivia(); }
        });

        scriptscavButton = (ImageButton) findViewById(R.id.imageView2);
        scriptscavButton.setTooltipText("Script Scavenger: How many words can you find from the letters of the word given?");
        scriptscavButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openScriptScav();

            }

        });

        HangmanButton = (ImageButton) findViewById(R.id.imageView3);
        HangmanButton.setTooltipText("Hangman");
        HangmanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openHangman();}
        });

        SudokuButton = (ImageButton) findViewById(R.id.imageView4);
        SudokuButton.setTooltipText("Sudoku");
        SudokuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openSudoku();}
        });

        SettingsButton = (ImageButton) findViewById(R.id.settingsIcon);
        SettingsButton.setTooltipText("Settings");
        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openSettings();}
        });

        AchievementButton = (ImageButton) findViewById(R.id.TrophyIcon);
        AchievementButton.setTooltipText("Achievements");
        AchievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openAchievements();}
        });

        HomeButton=(ImageButton)findViewById(R.id.homeIcon);
        HomeButton.setTooltipText("Home");
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        ProfileButton = (ImageButton) findViewById(R.id.profileIcon);
        ProfileButton.setTooltipText("Profile");
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openProfile();}
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