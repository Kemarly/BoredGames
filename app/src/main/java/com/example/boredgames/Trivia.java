package com.example.boredgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;







public class Trivia extends AppCompatActivity {

    ImageButton homebutton;

    ImageButton SettingsButton;

    ImageButton AchievementButton;

    ImageButton ProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);


        homebutton = (ImageButton) findViewById(R.id.imageView7);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
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


        TextView Ques = (TextView) findViewById(R.id.Question);
        Button Answer1 = (Button) findViewById(R.id.Answer1);
        Button Answer2 = (Button) findViewById(R.id.Answer2);
        Button Answer3 = (Button) findViewById(R.id.Answer3);
        Button Answer4 = (Button) findViewById(R.id.Answer4);

        String url = "https://opentdb.com/api.php?amount=1&type=multiple";

        JsonObjectRequest jsonObjectRequest_ = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   // String correct_answer = response.getString("correct_answer");


                    JSONArray resultsArray = response.getJSONArray("results");
                    JSONObject resultObject = resultsArray.getJSONObject(0);
                    String correctAnswer = resultObject.getString("correct_answer");
                    String question = resultObject.getString("question");
                    JSONArray incorrectAnswersArray = resultObject.getJSONArray("incorrect_answers");
                   // String incorrect_answer1 = resultObject.getString("incorrect_answers");

                    for (int i = 0; i < Math.min(3, incorrectAnswersArray.length()); i++) {
                        String incorrectAnswer = incorrectAnswersArray.getString(i);
                        if (i == 0) {
                            Answer2.setText(incorrectAnswer);
                        } else if (i == 1) {
                            Answer3.setText(incorrectAnswer);
                        } else if(i == 2){
                            Answer4.setText(incorrectAnswer);
                        }
                    }


                  //  tv.setText(correctAnswer);
                    Ques.setText(question);
                    Answer1.setText(correctAnswer);
                   // Answer2.setText(incorrect_answer1);
                   // tv.setText(correct_answer);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Ques.setText("error");
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest_);


    }






    public void goHome(){
        Intent intent = new Intent(this, MainActivity.class);
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