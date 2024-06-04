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
    CountDownTimer timer;
    private TextView[][] grid;
    private int [][] answers;
    TextView gridCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        HomeButton = findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(v -> GoHome());

        grid = new TextView[9][9];
        makeGrid();
        resetTimer();

        tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener(v -> tutorial());
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
            }
            @Override
            public void onFinish() {
            }
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
        public int getCount() {return 81;}
        @NonNull
        @Override
        public Object getItem(int position) {return 0;}
        @Override
        public long getItemId(int position) {return 0;}
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);}
            gridCell = convertView.findViewById(R.id.gridCell);
            int row = position / 9;
            int col = position % 9;
            grid[row][col] = gridCell;
            convertView.setBackgroundResource(R.drawable.grid_line);
            int width = parent.getWidth() / 9;
            convertView.setLayoutParams(new GridView.LayoutParams(width, width));
            return convertView;
        }
    }

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

    public class SudokuBoard extends View {
        private final int boardColor;
        private final Paint boardPaint = new Paint();

        public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            TypedArray arr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard, 0, 0);
            try {
                boardColor = arr.getColor(R.styleable.SudokuBoard_boardColor, 0);
            } finally {
                arr.recycle();
            }
        }
        @Override
        protected void onMeasure(int wid, int hei) {
            super.onMeasure(wid, hei);
            int dimension = Math.min(this.getWidth(), this.getHeight());
            setMeasuredDimension(dimension, dimension);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            boardPaint.setStyle(Paint.Style.STROKE);
            boardPaint.setStrokeWidth(16);
            boardPaint.setColor(boardColor);
            boardPaint.setAntiAlias(true);

            canvas.drawRect(0, 0, getWidth(), getHeight(), boardPaint);
            board(canvas);
        }
        private void thickLine() {
            boardPaint.setStyle(Paint.Style.STROKE);
            boardPaint.setStrokeWidth(10);
            boardPaint.setColor(boardColor);
        }
        private void thinLine() {
            boardPaint.setStyle(Paint.Style.STROKE);
            boardPaint.setStrokeWidth(4);
            boardPaint.setColor(boardColor);
        }
        private void board(Canvas canvas) {
            int width = getWidth();
            int height = getHeight();
            int cellSize = width / 9;
            for (int i = 0; i <= 9; i++) {
                if (i % 3 == 0) thickLine();
                else thinLine();
                canvas.drawLine(i * cellSize, 0, i * cellSize, height, boardPaint);
                canvas.drawLine(0, i * cellSize, width, i * cellSize, boardPaint);
            }
        }
    }
}