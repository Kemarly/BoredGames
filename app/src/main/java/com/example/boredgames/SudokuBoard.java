package com.example.boredgames;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SudokuBoard extends View {
    private final int boardColor;
    private final Paint boardPaint = new Paint();

    public SudokuBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard, 0, 0);
        try {
            boardColor = arr.getColor(R.styleable.SudokuBoard_boardColor, 0);
        } finally {
            arr.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int cellSize = width / 9;

        boardPaint.setStyle(Paint.Style.STROKE);
        boardPaint.setStrokeWidth(2);
        boardPaint.setColor(boardColor);
        for (int i = 0; i <= 9; i++) {
            int startX = i * cellSize;
            int startY = 0;
            int endX = startX;
            int endY = height;
            canvas.drawLine(startX, startY, endX, endY, boardPaint);

            startX = 0;
            startY = i * cellSize;
            endX = width;
            endY = startY;
            canvas.drawLine(startX, startY, endX, endY, boardPaint);
        }
    }
}
