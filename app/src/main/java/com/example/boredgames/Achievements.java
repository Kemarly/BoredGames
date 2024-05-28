package com.example.boredgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Achievements extends AppCompatActivity {

    ImageButton homebutton;

    TextView ScriptScoreBox;

    TextView TriviaScoreBox;

    TextView HangmanScorebox;


    private DatabaseReference ScriptscoresReference;

    private DatabaseReference TriviaScoresReference;

    private DatabaseReference HangmanReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        homebutton = (ImageButton) findViewById(R.id.homeIcon);

        ScriptScoreBox = (TextView) findViewById(R.id.ScriptScore);

        TriviaScoreBox = (TextView) findViewById(R.id.TriviaScore);

        HangmanScorebox = (TextView) findViewById(R.id.HangmanScore);



        TriviaScoresReference = FirebaseDatabase.getInstance().getReference("scores/Trivia Scores");

        ScriptscoresReference = FirebaseDatabase.getInstance().getReference("scores/ScriptScavenger Scores");

       HangmanReference = FirebaseDatabase.getInstance().getReference("scores/Hangman Scores");

      retrieveScriptScores();

        retrieveTriviaScores();

        retrieveHangmanScores();

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoHome();
            }
        });
    }

    public void GoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    private void retrieveScriptScores(){
        ScriptscoresReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Map.Entry<String, Long>> scoreList = new ArrayList<>();
                //StringBuilder scoresBuilder = new StringBuilder();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String username = snapshot.getKey();
                    Long score = snapshot.getValue(Long.class);

                    if(username != null && score != null)
                    {
                        scoreList.add(new AbstractMap.SimpleEntry<>(username, score));
                    }

                    //scoresBuilder.append(username).append(": ").append(score).append("\n");
                }
                Collections.sort(scoreList, new Comparator<Map.Entry<String, Long>>() {
                    @Override
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                List<Map.Entry<String, Long>> topScores = scoreList.subList(0, Math.min(scoreList.size(), 5));

                StringBuilder scoresBuilder = new StringBuilder();
                for(Map.Entry<String, Long> entry : topScores)
                {
                    scoresBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }

                ScriptScoreBox.setText(scoresBuilder.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ScriptScoreBox.setText("Failed to load scores...");
            }
        });
    }

   private void retrieveTriviaScores(){
        TriviaScoresReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Map.Entry<String, Long>> scoreList = new ArrayList<>();
                //StringBuilder scoresBuilder = new StringBuilder();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String username = snapshot.getKey();
                    Long score = snapshot.getValue(Long.class);

                    if(username != null && score != null)
                    {
                        scoreList.add(new AbstractMap.SimpleEntry<>(username, score));
                    }

                    //scoresBuilder.append(username).append(": ").append(score).append("\n");
                }
                Collections.sort(scoreList, new Comparator<Map.Entry<String, Long>>() {
                    @Override
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                List<Map.Entry<String, Long>> topScores = scoreList.subList(0, Math.min(scoreList.size(), 5));

                StringBuilder scoresBuilder = new StringBuilder();
                for(Map.Entry<String, Long> entry : topScores)
                {
                    scoresBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("/10").append("\n");
                }

                TriviaScoreBox.setText(scoresBuilder.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                TriviaScoreBox.setText("Failed to load scores...");
            }
        });
    }

   private void retrieveHangmanScores(){
        HangmanReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Map.Entry<String, Long>> scoreList = new ArrayList<>();
                //StringBuilder scoresBuilder = new StringBuilder();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String username = snapshot.getKey();
                    Long score = snapshot.getValue(Long.class);

                    if(username != null && score != null)
                    {
                        scoreList.add(new AbstractMap.SimpleEntry<>(username, score));
                    }

                    //scoresBuilder.append(username).append(": ").append(score).append("\n");
                }
                Collections.sort(scoreList, new Comparator<Map.Entry<String, Long>>() {
                    @Override
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                List<Map.Entry<String, Long>> topScores = scoreList.subList(0, Math.min(scoreList.size(), 5));

                StringBuilder scoresBuilder = new StringBuilder();
                for(Map.Entry<String, Long> entry : topScores)
                {
                    scoresBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("/6").append("\n");
                }

                HangmanScorebox.setText(scoresBuilder.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                HangmanScorebox.setText("Failed to load scores...");
            }
        });
    }
}