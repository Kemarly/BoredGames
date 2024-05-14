package com.example.boredgames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Sudoku extends AppCompatActivity {

    ImageButton HomeButton;
    CountDownTimer timer;
    private TextView[][] grid;
    private int [][] answers;
    TextView gridCell;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        HomeButton = (ImageButton) findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(v -> GoHome());

        grid = new TextView[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

            }
        }
        //makeGrid();
        resetTimer();
    }

    public void GoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void resetTimer()
    {
        timer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            long elapsedTime = 0;
            @Override
            public void onTick(long millisUntilFinished)
            {
                elapsedTime += 1000;
                TextView timerTextView = findViewById(R.id.Time);
                timerTextView.setText(String.valueOf(elapsedTime / 1000));
            }
            @Override
            public void onFinish() { }
        }.start();
    }
    private void makeGrid()
    {
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new SudokuAdapter());
    }
    private void userAnswers(TextView num)
    {

    }
    private class SudokuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 81;
        }
        @Override
        public Object getItem(int position) {
            return 0;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            }
            gridCell = convertView.findViewById(R.id.gridCell);
            convertView.setBackgroundResource(R.drawable.grid_line);
            return convertView;
        }
    }
}