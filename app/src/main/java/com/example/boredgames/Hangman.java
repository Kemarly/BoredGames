package com.example.boredgames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Hangman extends AppCompatActivity {

    private int IncorrectTracker = 0;

    TextView end;
    ImageView body;

    ImageView rightLeg;

    ImageView head;

    ImageView leftArm;

    ImageView rightArm;

    ImageView leftLeg;

    ImageButton homebutton;

    Button A;

    Button B;

    TextView word;

    Button I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);



        word = (TextView) findViewById(R.id.RandomWord);


        String under = " ";

        String WordBox = RandomWord();
        int len = WordBox.length();
        char chararray[] = WordBox.toCharArray();

        for (char c: chararray)
        {
            under += "_ ";
        }
    //    for(int i = 0; i < len; i++){
  //          under += "_ ";
//
      //  }
        word.setText(under);
        //word.setText(WordBox);



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
        end = (TextView) findViewById(R.id.GameOver);


        A = (Button) findViewById(R.id.ButtonA);
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncorrectTracker++;
                ShowHangman();
            }
        });

        B = (Button) findViewById(R.id.ButtonB);

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    word.setText(WordBox);

            }
        });

        I = (Button) findViewById(R.id.ButtonI);

        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char target = 'i';
                for (char c: chararray)
                {
                    if(c == target){
                        word.setText(WordBox);
                    }
                }
            }
        });

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
                break;
            default:
                end.setVisibility(View.VISIBLE);
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




