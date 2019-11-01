package com.mydemo.toadvanced.average_distribut;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @创建者
 * @TODO:
 */
public class SuiYiXie extends LinearLayout {
    public SuiYiXie(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMod = MeasureSpec.getMode(widthMeasureSpec);
        int sSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMod = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams mlp = (MarginLayoutParams) view.getLayoutParams();
            int childHeight = view.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;
            int childWidth = view.getMeasuredHeight() + mlp.leftMargin + mlp.leftMargin;
            height += childHeight;
            width = Math.max(width, childWidth);
        }
        setMeasuredDimension(wMod == MeasureSpec.AT_MOST ? width : sSize, hMod == MeasureSpec.AT_MOST ? height : hSize);
    }
}
