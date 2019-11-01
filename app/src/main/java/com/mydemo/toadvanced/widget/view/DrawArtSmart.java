package com.mydemo.toadvanced.widget.view;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.mydemo.toadvanced.widget.view.draw.AbstractBaseDrawCore;

/**
 * Author: ZhongB (艾文）
 * Email:zhongbin@migu.cn
 * Time: 2017/6/5
 * TODO
 */
public class DrawArtSmart extends AbstractBaseDrawCore {

    public DrawArtSmart(DayDisplayView view) {
        super(view);
    }

    @Override
    public void drawWeek(Canvas g) {
    }

    @Override
    public void drawDay(Canvas g) {
    }

    @Override
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void setSelectedDay(int day) {
    }
}
