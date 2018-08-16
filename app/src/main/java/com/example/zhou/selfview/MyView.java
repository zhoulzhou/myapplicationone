package com.example.zhou.selfview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.zhou.myapplication.R;

public class MyView extends View{
    private static final String TAG = "MyView";
    private Paint mPaint = new Paint();
    private int mdefaultSize;

    public MyView(Context context){
        super(context);
    }

    public MyView(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        mdefaultSize = ta.getDimensionPixelSize(R.styleable.MyView_default_size, 100);

        initPaint();
    }

    private void initPaint(){
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2f);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(mdefaultSize,widthMeasureSpec);
        int height = getMySize(mdefaultSize,heightMeasureSpec);
        Log.d(TAG,"width= " + width + " height= " + height);
        if(width < height){
            height = width;
        }else{
            width =height;
        }

        Log.d(TAG,"width= " + width + " height= " + height);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int r = getWidth() / 2;
        int centerX = getLeft() + r;
        int centerY = getTop() + r;

        canvas.drawCircle(centerX, centerY, r, mPaint);
    }

    private int getMySize(int defaultSize, int measureSpec){
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {
                mySize = size;
                break;
            }
        }
        Log.d(TAG," mySize= " + mySize + " size= " + size + " defaultSize "  + defaultSize);
        return mySize;
    }
}