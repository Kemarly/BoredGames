package com.example.boredgames;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoard extends View {
    private final int boardColor;
    private  final Paint boardPaint = new Paint();
    public SudokuBoard(Context context, @Nullable AttributeSet attrs, int boardColor, int boardColor1) {
        super(context, attrs);
        this.boardColor = boardColor1;
        TypedArray arr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,0,0);

        try {
                boardColor=arr.getInteger(R.styleable.SudokuBoard_boardColor,0);
        }finally {
            arr.recycle();
        }
    }
    @Override
    protected void onMeasure(int wid, int hei)
    {
        super.onMeasure(wid,hei);

        int dimension = Math.min(this.getWidth(), this.getHeight());
        setMeasuredDimension(dimension,dimension);
    }
    @Override
    protected void onDraw(Canvas canvas){
        boardPaint.setStyle(Paint.Style.STROKE);
        boardPaint.setStrokeWidth(16);
        boardPaint.setColor(boardColor);
        boardPaint.setAntiAlias(true);

        canvas.drawRect(0,0, getWidth(), getHeight(),boardPaint);
    }
}
