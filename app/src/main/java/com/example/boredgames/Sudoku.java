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
    ImageButton SettingsButton;
    ImageButton AchievementButton;
    ImageButton ProfileButton;
    ImageButton tutorial;
    Button complete;
    CountDownTimer timer;
    private TextView[][] grid;
    private int [][] answers;
    private int [][] puzzle;
    TextView gridCell;
    int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        HomeButton = findViewById(R.id.HomeButton);
        HomeButton.setTooltipText("Home");
        HomeButton.setOnClickListener(v -> GoHome());

        SettingsButton = (ImageButton) findViewById(R.id.settingsIcon);
        SettingsButton.setTooltipText("Settings");
        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openSettings();}
        });

        AchievementButton = (ImageButton) findViewById(R.id.TrophyIcon);
        AchievementButton.setTooltipText("Achievements");
        AchievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openAchievements();}
        });

        ProfileButton = (ImageButton) findViewById(R.id.profileIcon);
        ProfileButton.setTooltipText("Profile");
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openProfile();}
        });

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
    private void resetTimer() {
        timer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            long elapsedTime = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                elapsedTime += 1000;
                TextView timerTextView = findViewById(R.id.Time);
                int minutes = (int) (elapsedTime / 1000) / 60;
                int seconds = (int) (elapsedTime / 1000) % 60;
                String totalTime = String.format("%02d:%02d", minutes, seconds);
                timerTextView.setText(totalTime);
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
        SudokuAdapter adapter = new SudokuAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            int row = position / 9;
            int col = position % 9;
            userAnswers(grid[row][col]);
        });
        puzzle = makePuzzle();
        answers = makeSolution(puzzle);
        fillGrid();
    }

    @NonNull
    private int[][] makePuzzle() {
        int[][] board = new int[9][9];
        Random random = new Random();
        int filledCells = random.nextInt(6) + 20;

        while (filledCells > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (board[row][col] == 0) {
                int num = random.nextInt(9) + 1;
                board[row][col] = num;
                filledCells--;
            }
        }
        return board;
    }

    private int[][] makeSolution(int[][] board) {
        int[][] solution = new int[9][9];
        for (int i = 0; i < 9; i++) {System.arraycopy(board[i], 0, solution[i], 0, 9);}
        solve(solution);
        return solution;
    }

    private void fillGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != null) {
                    if (puzzle[i][j] != 0) {
                        grid[i][j].setText(String.valueOf(puzzle[i][j]));
                        grid[i][j].setEnabled(false);
                    } else {
                        grid[i][j].setText("");
                        grid[i][j].setEnabled(true);
                    }
                }
            }
        }
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {return true;}
                            board[row][col] = 0;
                        }
                    } return false;
                }
            }
        } return true;
    }
    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {if (board[row][i] == num || board[i][col] == num) {return false;}}
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int r = 0; r < 3; r++) {for (int d = 0; d < 3; d++) {if (board[r + boxRowStart][d + boxColStart] == num) {return false;}}}
        return true;
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
        private Context context;

        SudokuAdapter(Context context) {this.context = context;}

        @Override
        public int getCount() {return 81;}

        @Override
        public Object getItem(int position) {return null;}

        @Override
        public long getItemId(int position) {return 0;}

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

            int backgroundResource = R.drawable.grid_line_thin;
            convertView.setBackgroundResource(backgroundResource);

            int width = parent.getWidth() / 9;
            convertView.setLayoutParams(new GridView.LayoutParams(width, width));

            if (puzzle[row][col] != 0) {
                gridCell.setText(String.valueOf(puzzle[row][col]));
                gridCell.setEnabled(false);
            } else {
                gridCell.setText("");
                gridCell.setEnabled(true);
            }
            return convertView;
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