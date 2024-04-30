package com.example.boredgames;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.text.Html;


import java.lang.Thread;

import android.os.Handler;
import android.widget.Toast;


public class Trivia extends AppCompatActivity {



    private int QuestionTracker = 0;

    private int ScoreTracker = 0;

    EditText Username;

    TextView QuestionBox;

    Button AnswerBox1;

    Button AnswerBox2;

    Button AnswerBox3;

    Button AnswerBox4;

    ImageButton homebutton;

    ImageButton SettingsButton;

    ImageButton AchievementButton;

    ImageButton ProfileButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);



        ScoreTracker = 0;

        QuestionTracker = 0;

        makeApiCall();

        homebutton = (ImageButton) findViewById(R.id.homeIcon);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });

        SettingsButton = (ImageButton) findViewById(R.id.settingsIcon);

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

        ProfileButton = (ImageButton) findViewById(R.id.profileIcon);

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });


        //private void makeApiCall() {
        AnswerBox1 = (Button) findViewById(R.id.Answer1);

        QuestionBox = (TextView) findViewById(R.id.Question);


        AnswerBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer1Choose();
                AnswerChoose(AnswerBox1);

            }
        });

        AnswerBox2 = (Button) findViewById(R.id.Answer2);

        AnswerBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer2Choose();
                AnswerChoose(AnswerBox2);
            }
        });

        AnswerBox3 = (Button) findViewById(R.id.Answer3);
        AnswerBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer3Choose();
                AnswerChoose(AnswerBox3);
            }
        });

        AnswerBox4 = (Button) findViewById(R.id.Answer4);

        AnswerBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer4Choose();
                AnswerChoose(AnswerBox4);
            }
        });

    }

    private void makeApiCall() {



        TextView Ques = (TextView) findViewById(R.id.Question);
        Button Answer1 = (Button) findViewById(R.id.Answer1);

        //  AnswerBox1.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //      public void onClick(View v) {
        //          Answer1Choose();
        //          AnswerChoose(AnswerBox1);

        //      }
        //  });



        Button Answer2 = (Button) findViewById(R.id.Answer2);
        Button Answer3 = (Button) findViewById(R.id.Answer3);
        Button Answer4 = (Button) findViewById(R.id.Answer4);

        if(QuestionTracker == 10)
        {
            FinishedMenu();
            return;
        }


        //Answer1.setBackgroundColor(0xd3d3d3);

        String url = "https://opentdb.com/api.php?amount=1&type=multiple";

        JsonObjectRequest jsonObjectRequest_ = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    int result = response.getInt("response_code");
                    if (result == 5) {
                        //makeApiCall();
                        //onResponse(response);
                        //makeApiCall();
                        return;
                    }


                    if (result == 0) {


                        JSONArray resultsArray = response.getJSONArray("results");

                        JSONObject resultObject = resultsArray.getJSONObject(0);
                        String correctAnswer = resultObject.getString("correct_answer");
                        String question = resultObject.getString("question");

                        JSONArray incorrectAnswersArray = resultObject.getJSONArray("incorrect_answers");


                        Ques.setText(question);
                        if (question.contains("&quot;")) {
                            // String newquestion = question.replace("&quot;", "");
                            String newquestion = Html.fromHtml(question).toString();
                            Ques.setText(newquestion);

                        }

                        if (question.contains("&#039;")) {
                            //String newquestion = question.replace("&#039;", "");
                            String newquestion = Html.fromHtml(question).toString();
                            Ques.setText(newquestion);
                        }

                        if (question.contains("&amp;")) {
                            //String newquestion = question.replace("&amp;", "");
                            String newquestion = Html.fromHtml(question).toString();
                            Ques.setText(newquestion);
                        }

                        Answer1.setText(correctAnswer);


                        for (int i = 0; i < Math.min(3, incorrectAnswersArray.length()); i++) {
                            String incorrectAnswer = incorrectAnswersArray.getString(i);
                            if (i == 0) {
                                Answer2.setText(incorrectAnswer);
                                if (incorrectAnswer.contains("&AMP;")) {
                                    //String newanswer = incorrectAnswer.replace("&AMP;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer2.setText(incorrectAnswer);
                                }
                                if (incorrectAnswer.contains("#039;")) {
                                    //  String newanswer = incorrectAnswer.replace("#039;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer2.setText(incorrectAnswer);
                                }
                                if (incorrectAnswer.contains("&#039;")) {
                                    // String newanswer = incorrectAnswer.replace("&#039;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer2.setText(incorrectAnswer);
                                }
                            } else if (i == 1) {
                                Answer3.setText(incorrectAnswer);
                                if (incorrectAnswer.contains("&AMP;")) {
                                    //String newanswer = incorrectAnswer.replace("&AMP;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer3.setText(incorrectAnswer);
                                }
                                if (incorrectAnswer.contains("#039;")) {
                                    //String newanswer = incorrectAnswer.replace("#039;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer3.setText(incorrectAnswer);
                                }
                                if (incorrectAnswer.contains("&#039;")) {
                                    //String newanswer = incorrectAnswer.replace("&#039;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer3.setText(incorrectAnswer);
                                }
                            } else if (i == 2) {
                                Answer4.setText(incorrectAnswer);
                                if (incorrectAnswer.contains("&AMP;")) {
                                    // String newanswer = incorrectAnswer.replace("&AMP;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer4.setText(incorrectAnswer);
                                }
                                if (incorrectAnswer.contains("#039;")) {
                                    //String newanswer = incorrectAnswer.replace("#039;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer4.setText(incorrectAnswer);
                                }
                                if (incorrectAnswer.contains("&#039;")) {
                                    //String newanswer = incorrectAnswer.replace("&#039;", "");
                                    String newanswer = Html.fromHtml(incorrectAnswer).toString();
                                    Answer4.setText(incorrectAnswer);
                                }
                            }

                        }

                    }


                } catch (JSONException e) {
                    //   throw new RuntimeException(e);
                    e.printStackTrace();
                    Ques.setText("Error parsing JSON");
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Ques.setText("Loading...");

                makeApiCall();


            }

        });



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest_);



    }




    public void OnResponse(){

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

    public void Answer1Choose(){
        AnswerBox1.setBackgroundColor(Color.GREEN);
        resetButtonColor(AnswerBox1);
        ScoreTracker++;
    }

    public void Answer2Choose(){
        AnswerBox2.setBackgroundColor(Color.RED);
        resetButtonColor(AnswerBox2);
    }

    public void Answer3Choose(){
        AnswerBox3.setBackgroundColor(Color.RED);
        resetButtonColor(AnswerBox3);
    }

    public void Answer4Choose(){
        AnswerBox4.setBackgroundColor(Color.RED);
        resetButtonColor(AnswerBox4);
    }

    private void resetButtonColor(final Button button){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setBackgroundColor(Color.parseColor("#d3d3d3"));
            }
        }, 3000);
    }


    private void TriviaStart(){
        QuestionTracker = 0;

        makeApiCall();

        homebutton = (ImageButton) findViewById(R.id.homeIcon);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });

        SettingsButton = (ImageButton) findViewById(R.id.settingsIcon);

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

        ProfileButton = (ImageButton) findViewById(R.id.profileIcon);

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });


        //private void makeApiCall() {
        AnswerBox1 = (Button) findViewById(R.id.Answer1);

        QuestionBox = (TextView) findViewById(R.id.Question);


        AnswerBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer1Choose();
                AnswerChoose(AnswerBox1);

            }
        });

        AnswerBox2 = (Button) findViewById(R.id.Answer2);

        AnswerBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer2Choose();
                AnswerChoose(AnswerBox2);
            }
        });

        AnswerBox3 = (Button) findViewById(R.id.Answer3);
        AnswerBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer3Choose();
                AnswerChoose(AnswerBox3);
            }
        });

        AnswerBox4 = (Button) findViewById(R.id.Answer4);

        AnswerBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer4Choose();
                AnswerChoose(AnswerBox4);
            }
        });


    }

    public void FinishedMenu(){
        AnswerBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });

        QuestionBox.setText("FINISHED!");
        AnswerBox1.setText("GO HOME");

        AnswerBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == AnswerBox2){
                    QuestionBox.setText(ScoreTracker + "/" + "10");


                    AnswerBox4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (v == AnswerBox4) {

                                Username = (EditText) findViewById(R.id.Prompt);

                                Username.setVisibility(View.VISIBLE);


                                String UserInput = Username.getText().toString();
                                QuestionBox.setText("Save Score After Entering Name");

                                if(!UserInput.isEmpty()) {
                                    QuestionBox.setText("Score has been saved for " + UserInput);

                                    Username.setVisibility(View.GONE);
                                    //QuestionBox.setText("SCORE SAVED");

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference reference = database.getReference("scores");

                                    // Save the score under a new auto-generated key
                                    reference.child("Trivia Scores").child(UserInput).setValue(ScoreTracker + "/10");

                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Please enter a valid username", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                    AnswerBox4.setText("SAVE SCORE");
                }
            }
        });

        AnswerBox2.setText("SEE SCORE");
        AnswerBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionTracker = 0;
                TriviaStart();
            }
        });
        AnswerBox3.setText("PLAY AGAIN");
        AnswerBox4.setText("");

    }

    private void AnswerChoose(Button selectedAnswer){

        if(selectedAnswer == AnswerBox1){
            QuestionTracker++;
            makeApiCall();
        }
        else{
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            QuestionTracker++;
            makeApiCall();
        }


    }
}