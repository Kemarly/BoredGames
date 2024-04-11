package com.example.boredgames;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import java.net.InetSocketAddress;

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
        //triviaButton.setTooltipText("Trivia");
        triviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrivia();
                showSnackbar("Trivia");
            }
        });

        scriptscavButton = (ImageButton) findViewById(R.id.imageView2);
        scriptscavButton.setTooltipText("Script Scavenger");
        scriptscavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScriptScav();
                showSnackbar("Script Scavenger");
            }
        });

        HangmanButton = (ImageButton) findViewById(R.id.imageView3);
        HangmanButton.setTooltipText("Hangman");
        HangmanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHangman();showSnackbar("Hangman");}
        });

        SudokuButton = (ImageButton) findViewById(R.id.imageView4);
        SudokuButton.setTooltipText("Sudoku");
        SudokuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openSudoku();showSnackbar("Sudoku");}
        });

        SettingsButton = (ImageButton) findViewById(R.id.imageView5);
        SettingsButton.setTooltipText("Settings");
        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();showSnackbar("Settings");}
        });

        AchievementButton = (ImageButton) findViewById(R.id.TrophyIcon);
        AchievementButton.setTooltipText("Achievements");
        AchievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAchievements();showSnackbar("Achievements");}
        });

        HomeButton=(ImageButton)findViewById(R.id.imageView7);
        HomeButton.setTooltipText("Home");
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showSnackbar("Home");}
        });

        ProfileButton = (ImageButton) findViewById(R.id.imageView8);
        ProfileButton.setTooltipText("Profile");
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openProfile();showSnackbar("Profile");}
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
    private void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextAppearance(R.style.CustomSnackbarText);
        snackbar.show();
    }

}