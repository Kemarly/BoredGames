package com.example.boredgames;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Sudoku extends AppCompatActivity {
    ImageButton HomeButton;
    ImageButton tutorial;
    Button complete;
    CountDownTimer timer;
    private TextView[][] grid;
    private int [][] answers;
    TextView gridCell;
    int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        HomeButton = findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(v -> GoHome());

        complete = findViewById(R.id.complete);
        complete.setOnClickListener(v -> completeGame());

        grid = new TextView[9][9];
        makeGrid();
        resetTimer();

        tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener(v -> tutorial());
    }
    public void completeGame() {
        int emptyCells = 0;
        for (int i = 0; i < 9; i++) {for (int j = 0; j < 9; j++) {if (grid[i][j].getText().toString().isEmpty()) {emptyCells++;}}}
        score -= emptyCells;
        if (score < 0) score = 0;
        showScore();
    }

    public void GoHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void resetTimer() {
        timer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            long elapsedTime = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                elapsedTime += 1000;
                TextView timerTextView = findViewById(R.id.Time);
                timerTextView.setText(String.valueOf(elapsedTime / 1000));
                if ((elapsedTime / 1000) % 10 == 0) {
                    score -= 1;
                    if (score < 0) score = 0;
                }
            }
            @Override
            public void onFinish() {}
        }.start();
    }

    private void makeGrid() {
        GridView gridView = findViewById(R.id.gridView);
        SudokuAdapter adapter = new SudokuAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            int row = position / 9;
            int col = position % 9;
            userAnswers(grid[row][col]);
        });
        answers = makePuzzle();
    }

    @NonNull
    private int[][] makePuzzle() {
        int[][] puzzle = new int[9][9];
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = random.nextInt(9) + 1;
            }
        }
        return puzzle;
    }

    private void userAnswers(TextView num) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", (dialog, which) -> {
            String inputNum = input.getText().toString();
            if (inputNum.matches("\\d")) {
                int numEntered = Integer.parseInt(inputNum);
                if (numEntered >= 1 && numEntered <= 9) {num.setText(inputNum);}}
            else {Toast.makeText(Sudoku.this, "Please enter only numbers between 1 and 9", Toast.LENGTH_SHORT).show();}
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private class SudokuAdapter extends BaseAdapter {
        @Override
        public int getCount() { return 81; }
        @NonNull
        @Override
        public Object getItem(int position) { return 0; }
        @Override
        public long getItemId(int position) { return 0; }
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            }

            gridCell = convertView.findViewById(R.id.gridCell);
            int row = position / 9;
            int col = position % 9;
            grid[row][col] = gridCell;

            int backgroundResource = getBackgroundResource(row, col);
            convertView.setBackgroundResource(backgroundResource);

            int width = parent.getWidth() / 9;
            convertView.setLayoutParams(new GridView.LayoutParams(width, width));

            return convertView;
        }

        private int getBackgroundResource(int row, int col) {
            boolean isTop = row == 0;
            boolean isBottom = row == 8;
            boolean isLeft = col == 0;
            boolean isRight = col == 8;

            boolean isSubgridTop = row % 3 == 0 && !isTop;
            boolean isSubgridBottom = row % 3 == 2 && !isBottom;
            boolean isSubgridLeft = col % 3 == 0 && !isLeft;
            boolean isSubgridRight = col % 3 == 2 && !isRight;

            if (isTop && isLeft) return R.drawable.top_left_thick;
            if (isTop && isRight) return R.drawable.top_right_thick;
            if (isBottom && isLeft) return R.drawable.bottom_left_thick;
            if (isBottom && isRight) return R.drawable.bottom_right_thick;

            if (isTop) return R.drawable.top_thick;
            if (isBottom) return R.drawable.bottom_thick;
            if (isLeft) return R.drawable.left_thick;
            if (isRight) return R.drawable.right_thick;

            if (isSubgridTop && isSubgridLeft) return R.drawable.top_left_thick;
            if (isSubgridTop && isSubgridRight) return R.drawable.top_right_thick;
            if (isSubgridBottom && isSubgridLeft) return R.drawable.bottom_left_thick;
            if (isSubgridBottom && isSubgridRight) return R.drawable.bottom_right_thick;

            if (isSubgridTop) return R.drawable.top_thick;
            if (isSubgridBottom) return R.drawable.bottom_thick;
            if (isSubgridLeft) return R.drawable.left_thick;
            if (isSubgridRight) return R.drawable.right_thick;

            return R.drawable.grid_line_thin;
        }
    }

    private void showScore() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        builder.setMessage("Your score: " + score);
        builder.setPositiveButton("Quit", (dialog, which) -> {
            dialog.dismiss();
            GoHome();
            score = 100;
        });
        AlertDialog dialog = builder.create();
        dialog.show();}
    private void tutorial() {
        timer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instructions");
        builder.setMessage("Fill in the squares with numbers from 1-9 with no repeated numbers in each line, horizontally or vertically.\nFor each 3×3 squares marked out in the grid, there cannot be any repeat numbers either");
        builder.setPositiveButton("OK", (dialog, which) -> {
            resetTimer();
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
