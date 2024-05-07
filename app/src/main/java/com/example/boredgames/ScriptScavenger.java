package com.example.boredgames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.Random;

public class ScriptScavenger extends AppCompatActivity {
    ImageButton HomeButton;
    ImageButton tutorial;
    CountDownTimer timer;
    EditText userInput;
    TextView generateWord;
    TextView tickTime;
    TextView answers;
    int score;
    long timeLeftInMillis = 60000;
    DictionaryService service; // Declare service at the class level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_scavenger);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dictionaryapi.com/api/v3/references/collegiate/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(DictionaryService.class); // Initialize service

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
        enterButton.setOnClickListener(v -> {
            String inputText = userInput.getText().toString();
            if (!inputText.isEmpty()) {
                checkSpelling(inputText);
            }
        });

        tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener(v -> tutorial());
    }

    public void GoHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void resetTimer() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                tickTime.setText(String.valueOf(secondsRemaining));
                timeLeftInMillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                tickTime.setText("0");
                showScore();
            }
        }.start();
    }

    private void showScore() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        builder.setMessage("Your score: " + score);
        builder.setPositiveButton("Quit", (dialog, which) -> {
            dialog.dismiss();
            GoHome();
            score = 0;
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String genRandom() {
        String[] words = {"onomatopoeia", "miscellaneous", "serendipity", "ostentatious", "infinitesimal",
                "phenolphthalein", "pejoratively", "sanctimonious", "improvisatory", "ordinariness",
                "juxtaposition", "apprehensive", "cumulonimbus", "equivocation", "ambiguity",
                "disenfranchisement", "ecclesiastical", "adaptability", "maneuverability", "decriminalization",
                "compartmentalization", "verisimilitude", "anisopoikilocytosis", "antidisestablishmentarianism", " decamethyltetrasiloxane",
                "bathysiderodromophobia", "schizosaccharomycetaceae", "methylenetetrahydrofolate", "floccinaucinihilipilificate", "sulfoquinovosyldiacylglycerol",
                "uvulopalatopharyngoplasty", "laryngotracheobronchopneumonitis", "representative", "Superfluous", "anaesthesia",
                "artificial", "responsibility", "magnanimous", "emotionally", "zygomaticofacial",
                "abduction", "academic", "diabetic", "capitalism", "encyclopedia",
                "insubordination", "lobotomize", "enthusiastic", "anniversary", "examination"};
        Random rand = new Random();
        int index = rand.nextInt(words.length);
        return words[index];
    }

    private void tutorial() {
        timer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tutorial");
        builder.setMessage("Try to create as many words as you can with the letters of the word that has been given.");
        builder.setPositiveButton("OK", (dialog, which) -> {
            resetTimer();
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkLetters(String inputText) {
        String generatedWord = generateWord.getText().toString().toLowerCase();
        inputText = inputText.toLowerCase();
        boolean isValid = true;
        for (int i = 0; i < inputText.length(); i++) {
            char c = inputText.charAt(i);
            if (generatedWord.indexOf(c) == -1) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            String currentAnswers = answers.getText().toString();
            if (!currentAnswers.isEmpty()) {
                currentAnswers += "\n";
            }
            currentAnswers += inputText;
            answers.setText(currentAnswers);
            score += inputText.length();
            userInput.setText("");
        } else {
            Toast.makeText(this, "Invalid word! Use only letters from the current word.", Toast.LENGTH_SHORT).show();
            userInput.setText("");
        }
    }

    private void checkSpelling(String inputText) {
        checkLetters(inputText);
        Call<Object> call = service.lookupWord(inputText);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    // Word is valid, add it to currentAnswers
                    String currentAnswers = answers.getText().toString();
                    if (!currentAnswers.isEmpty()) {
                        currentAnswers += "\n";
                    }
                    currentAnswers += inputText;
                    answers.setText(currentAnswers);
                    score += inputText.length();
                    userInput.setText("");
                } else {
                    // Word is not valid, show an error message
                    Toast.makeText(ScriptScavenger.this, "Invalid word! Use only letters from the current word.", Toast.LENGTH_SHORT).show();
                    userInput.setText("");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                // Show an error message in case of failure
                Toast.makeText(ScriptScavenger.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}