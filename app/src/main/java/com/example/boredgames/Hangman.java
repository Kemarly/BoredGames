package com.example.boredgames;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.List;

public class Hangman extends AppCompatActivity {

    private int IncorrectTracker = 0;

    private int ScoreTracker = 6;

    private boolean IsCorrect = false;



    TextView PrintWord;
    TextView end;
    ImageView body;

    ImageView rightLeg;

    ImageView head;

    ImageView leftArm;

    ImageView rightArm;

    ImageView leftLeg;

    ImageButton homebutton;


    Button SaveButton;

    EditText Username;


    Button A;

    Button B;

    Button C;
    Button D;
    Button E;
    Button F;
    Button G;
    Button H;
    Button I;
    Button J;
    Button K;
    Button L;
    Button M;
    Button N;
    Button O;
    Button P;
    Button Q;
    Button r;
    Button S;
    Button T;
    Button U;
    Button V;
    Button W;
    Button X;
    Button Y;
    Button Z;


    TextView word;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        PrintWord = (TextView) findViewById(R.id.ShowWord);



        word = (TextView) findViewById(R.id.RandomWord);







        String under = " ";

        String WordBox = RandomWord();
       // PrintWord.setText(WordBox);

        // Print out dashes instead of word
        List<Character> PlayerGuess = new ArrayList<>();

        PrintWordState(WordBox, PlayerGuess);

        A = (Button) findViewById(R.id.ButtonA);
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'a';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    A.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    A.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });


        B = (Button) findViewById(R.id.ButtonB);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkB = false;
                char GuessedLetter = 'b';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                StringBuilder wordText = new StringBuilder();

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    B.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);
                }
                else
                {
                    B.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        C = (Button) findViewById(R.id.ButtonC);
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'c';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    C.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    C.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        D = (Button) findViewById(R.id.ButtonD);
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'd';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    D.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    D.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });


        E = (Button) findViewById(R.id.ButtonE);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'e';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);


                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    E.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);
                }
                else
                {
                    E.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        F = (Button) findViewById(R.id.ButtonF);
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'f';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    F.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    F.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        G = (Button) findViewById(R.id.ButtonG);
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'g';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    G.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    G.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        H = (Button) findViewById(R.id.ButtonH);
        H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'h';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    H.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    H.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        I = (Button) findViewById(R.id.ButtonI);
        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'i';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    I.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    I.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        J = (Button) findViewById(R.id.ButtonJ);
        J.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'j';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    J.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    J.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        K = (Button) findViewById(R.id.ButtonK);
        K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'k';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    K.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    K.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        L = (Button) findViewById(R.id.ButtonL);
        L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'l';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    L.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    L.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        M = (Button) findViewById(R.id.ButtonM);
        M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'm';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    M.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    M.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        N = (Button) findViewById(R.id.ButtonN);
        N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'n';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    N.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    N.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        O = (Button) findViewById(R.id.ButtonO);
        O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'o';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    O.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    O.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        P = (Button) findViewById(R.id.ButtonP);
        P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'p';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    P.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    P.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        Q = (Button) findViewById(R.id.ButtonQ);
        Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'q';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    Q.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    Q.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        r = (Button) findViewById(R.id.ButtonR);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'r';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    r.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    r.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        S = (Button) findViewById(R.id.ButtonS);
        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 's';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    S.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    S.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        T = (Button) findViewById(R.id.ButtonT);
        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 't';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    T.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    T.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        U = (Button) findViewById(R.id.ButtonU);
        U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'u';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    U.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    U.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        V = (Button) findViewById(R.id.ButtonV);
        V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'v';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    V.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    V.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        W = (Button) findViewById(R.id.ButtonW);
        W.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'w';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    W.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    W.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        X = (Button) findViewById(R.id.ButtonX);
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'x';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    X.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    X.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        Y = (Button) findViewById(R.id.ButtonY);
        Y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'y';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    Y.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    Y.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });

        Z = (Button) findViewById(R.id.ButtonZ);
        Z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char GuessedLetter = 'z';
                PlayerGuess.add(GuessedLetter);
                //PrintWordState(WordBox, PlayerGuess);

                if(WordBox.contains(String.valueOf(GuessedLetter)))
                {
                    Z.setBackgroundColor(Color.GREEN);
                    PrintWordState(WordBox, PlayerGuess);

                }
                else
                {
                    Z.setBackgroundColor(Color.RED);
                    IncorrectTracker++;
                    ScoreTracker--;
                    ShowHangman();
                }

            }
        });



        homebutton = (ImageButton) findViewById(R.id.homeIcon);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoHome();
            }
        });

        leftLeg = (ImageView) findViewById(R.id.LeftLeg);
        rightLeg = (ImageView) findViewById(R.id.RightLeg);
        leftArm = (ImageView) findViewById(R.id.LeftArm);
        rightArm = (ImageView) findViewById(R.id.RightArm);
        head = (ImageView) findViewById(R.id.Head);
        body = (ImageView) findViewById(R.id.Body);
        SaveButton = (Button) findViewById(R.id.SaveScore);
        Username = (EditText) findViewById(R.id.Prompt);
        //end = (TextView) findViewById(R.id.GameOver);





    }

    private void PrintWordState(String WordBox, List<Character> PlayerGuess) {
        StringBuilder wordText = new StringBuilder();
        int CorrectTracker = 0;
        for(int i = 0; i < WordBox.length(); i++){
            char CurrentChar = WordBox.charAt(i);
            if(PlayerGuess.contains(CurrentChar)){
                wordText.append(CurrentChar);
                CorrectTracker++;
            }
            else{
                wordText.append("-");
            }
        }
        word.setText(wordText.toString());
        if(WordBox.length() == CorrectTracker)
        {
            SaveButton.setVisibility(View.VISIBLE);
            Username.setVisibility(View.VISIBLE);
            SaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Username = (EditText) findViewById(R.id.Prompt);

                    Username.setVisibility(View.VISIBLE);


                    String UserInput = Username.getText().toString();
                    word.setText("Save Score After Entering Name");

                    if(!UserInput.isEmpty()) {
                        word.setText("Score has been saved for " + UserInput);

                        Username.setVisibility(View.GONE);
                        //QuestionBox.setText("SCORE SAVED");

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("scores");

                        // Save the score under a new auto-generated key
                        reference.child("Hangman Scores").child(UserInput).setValue(ScoreTracker);

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please enter a valid username", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            PrintWord.setTextSize(25);
            PrintWord.setText("YOU WON, PLEASE ENTER YOUR NAME AND THEN SAVE SCORE IF YOU WANT YOUR SCORE SAVED" + "\n" + "Score: " + ScoreTracker + " / 6");
        }
    }


    public void GoHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void ShowHangman() {
        switch (IncorrectTracker) {
            case 1:
                head.setVisibility(View.VISIBLE);
                break;
            case 2:
                body.setVisibility(View.VISIBLE);
                break;
            case 3:
                leftArm.setVisibility(View.VISIBLE);
                break;
            case 4:
                rightArm.setVisibility(View.VISIBLE);
                break;
            case 5:
                leftLeg.setVisibility(View.VISIBLE);
                break;
            case 6:
                rightLeg.setVisibility(View.VISIBLE);
                //SaveButton.setVisibility(View.VISIBLE);
                PrintWord.setTextSize(25);
                PrintWord.setText("GAME OVER, YOU LOSE, IF YOU WOULD LIKE TO TRY AGAIN GO BACK TO THE HOME SCREEN AND CLICK ON HANGMAN." + "\n" + "Score: " + ScoreTracker + " / 6");
                break;
        }


    }



    private String RandomWord() {
        String[] words = {
                "apple", "banana", " orange", "grape", "lemon",
                "peach", "kiwi", "melon", "berry", "plum",
                "mango", "pear", "cherry", "apricot", "fig",
                "lime", "olive", "papaya", "peanut", "pecan",
                "almond", "walnut", "cashew", "hazelnut", "macadamia",
                "acorn", "carrot", "potato", "tomato", "onion",
                "celery", "lettuce", "cabbage", "pepper", "radish",
                "garlic", "ginger", "cereal", "biscuit", "muffin",
                "cookie", "pastry", "bagel", "pretzel", "croissant",
                "doughnut", "pancake", "waffle", "crepe", "toast",
                "cheese", "yogurt", "cream", "butter", "milk",
                "juice", "soda", "water", "coffee", "tea",
                "espresso", "latte", "cappuccino", "macchiato", "americano",
                "chicken", "beef", "pork", "lamb", "bacon",
                "sausage", "ham", "steak", "salmon", "tuna",
                "shrimp", "lobster", "crab", "scallop", "squid",
                "octopus", "clam", "mussel", "oyster", "snail",
                "frog", "turtle", "rabbit", "pigeon", "quail",
                "duck", "goose", "turkey", "parrot", "peacock",
                "sparrow", "canary", "penguin", "eagle", "hawk", "octopus"
        };
        Random rand = new Random();
        int index = rand.nextInt(words.length);
        return words[index];
    }
}


