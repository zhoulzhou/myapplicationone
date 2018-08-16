package com.example.zhou.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup{
    private static final String TAG = "MyViewGroup";
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int specHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.d(TAG,"specHeightSize= " + specHeightSize);
        Log.d(TAG,"specWidthSize= " + specWidthSize);

        int childCount = getChildCount();
        Log.d(TAG, " childCount= " + childCount);

        if(childCount == 0){
            setMeasuredDimension(0,0);
        }else{
            if(specWidthMode == MeasureSpec.AT_MOST && specHeightMode == MeasureSpec.AT_MOST){
                int width = getMaxWidth();
                int height = getTotalHeight();
                Log.d(TAG, "1 width= " + width + " height= " +height);
                setMeasuredDimension(width,height);
            }else if(specWidthMode == MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxWidth(), specHeightSize);
            }else if(specHeightMode == MeasureSpec.AT_MOST){
                setMeasuredDimension(specWidthSize, getTotalHeight());
            }

        }
    }

    private int getMaxWidth(){
        int childCount = getChildCount();
        int maxWidth = 0;

        for(int i=0; i<childCount; i++){
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            Log.d(TAG, "childWidth= " + childWidth + " maxWidth= " + maxWidth);
            if(maxWidth < childWidth){
                maxWidth = childWidth;
            }
        }

        return maxWidth;
    }

    private int getTotalHeight(){
        int childCount = getChildCount();

        int totalHeight = 0;
        for(int i=0; i<childCount; i++){
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight();
            Log.d(TAG, " totalHeight= " + totalHeight + " childHeight= " + childHeight);
            totalHeight += childHeight;
        }

        return totalHeight;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int curHeight = t;

        for(int i=0; i<childCount; i++){
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            Log.d(TAG, "width= " + width + " height= " + height);
            child.layout(l, curHeight, l+width, curHeight+height);
            curHeight += height;
        }

    }
}
