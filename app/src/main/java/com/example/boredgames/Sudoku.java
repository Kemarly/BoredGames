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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
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

        HomeButton = (ImageButton) findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(v -> GoHome());

        grid = new TextView[9][9];
        makeGrid();
        resetTimer();

        tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener(v -> tutorial());
    }
    public void GoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void resetTimer() {
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

    private void makeGrid() {
        GridView gridView = findViewById(R.id.gridView);
        SudokuAdapter adapter = new SudokuAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            int row = position / 9;
            int col = position % 9;
            userAnswers(grid[row][col]);
        });
    }
    private int[][] makePuzzle(){
        int[][] puzzle = new int[9][9];
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = random.nextInt(9) + 1;
            }
        }
        return puzzle;
    }

    private void userAnswers(TextView num) {}

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
        }
    }
}
