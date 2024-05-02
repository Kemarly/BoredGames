package com.example.boredgames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.CountDownTimer;
import android.widget.TextView;
import org.languagetool.JLanguageTool;
import org.languagetool.Languages;
//import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;
import java.util.List;
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
        enterButton.setOnClickListener(v -> {
            String inputText = userInput.getText().toString();
            if (!inputText.isEmpty()) {
                String currentAnswers = answers.getText().toString();
                if (!currentAnswers.isEmpty()) {
                    currentAnswers += "\n";
                }
                currentAnswers += inputText;
                answers.setText(currentAnswers);
                score += inputText.length();

                userInput.setText("");
            }
        });

        tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener(v -> tutorial());

        //checkSpelling();
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
                "phenolphthalein","pejoratively", "sanctimonious", "improvisatory", "ordinariness",
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
    private void checkSpelling() {
        JLanguageTool langTool = new JLanguageTool(Languages.getLanguageForShortCode("en-GB"));
        //JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
        try {
            List<RuleMatch> matches = langTool.check("ERROR!");
            for (RuleMatch match : matches) {
                System.out.println("Potential typo at characters " +
                        match.getFromPos() + "-" + match.getToPos() + ": " +
                        match.getMessage());
                System.out.println("Suggested correction(s): " +
                        match.getSuggestedReplacements());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}