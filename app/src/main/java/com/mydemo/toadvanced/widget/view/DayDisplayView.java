package com.mydemo.toadvanced.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mydemo.toadvanced.R;
import com.mydemo.toadvanced.widget.view.draw.AbstractBaseDrawCore;
import com.mydemo.toadvanced.widget.view.utils.PxUtil;

import androidx.annotation.Nullable;

/**
 * Author: ZhongB (艾文）
 * Email:zhongbin@migu.cn
 * Time: 2017/6/2
 * 每月数据展示
 */
public class DayDisplayView extends View {

    public static final int TYPE_SIMPLE = 1;
    public static final int TYPE_SMART = 2;
    private int mType = TYPE_SMART;
    private AbstractBaseDrawCore mCalenderView;

    public DayDisplayView(Context context) {
        this(context, null);
    }

    public DayDisplayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayDisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.sol_DayDisplayView);
//        if (typedArray != null) {
//            mType = typedArray.getInteger(R.styleable.sol_DayDisplayView_sol_type, 1);
//            if (mType == TYPE_SMART) {
//                mCalenderView = new DrawArtSmart(this);
//            } else {
//                mCalenderView = new DrawArtSimple(this);
//            }
//            int weekColor = typedArray.getColor(R.styleable.sol_DayDisplayView_sol_weekColor, 0xFF999999);
//            int weekSize = typedArray.getDimensionPixelSize(R.styleable.sol_DayDisplayView_sol_weekSize, PxUtil.dip2px(context, 16));
//            int dayColor = typedArray.getColor(R.styleable.sol_DayDisplayView_sol_dayColor, 0XFF333333);
//            int daySize = typedArray.getDimensionPixelSize(R.styleable.sol_DayDisplayView_sol_daySize, PxUtil.dip2px(context, 14));
//
//            int currentTvColor = typedArray.getColor(R.styleable.sol_DayDisplayView_sol_currentDayColor, 0XFFFF0000);
//            int selectTvColor = typedArray.getColor(R.styleable.sol_DayDisplayView_sol_selectTextColor, 0XFFFFFFFF);
//            int selectBackgroundColor = typedArray.getColor(R.styleable.sol_DayDisplayView_sol_selectBackgroundColor, 0XFFFF0000);
//
//            mCalenderView.initColor(weekColor, dayColor, currentTvColor, selectBackgroundColor, selectTvColor);
//            mCalenderView.initTextSize(weekSize, daySize);
//            mCalenderView.initPaint();
//            typedArray.recycle();
//        }
        if (mCalenderView == null) {
            mCalenderView = new DrawArtSmart(this);
        }
        mCalenderView.initPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mCalenderView != null) {
            int[] size = mCalenderView.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(size[0], size[1]);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (mCalenderView != null) {
            mCalenderView.drawWeek(canvas);
            mCalenderView.drawDay(canvas);
        }
        canvas.restore();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mCalenderView != null) {
            mCalenderView.initSize(right - left, bottom - top);
        }
        postInvalidate();
    }

    public void setDate(int year, int month) {
        mCalenderView.setDate(year, month);
        postInvalidate();
    }

    public void setDate(int year, int month, int selectedDay) {
        mCalenderView.setDate(year, month, selectedDay);
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCalenderView.onTouchEvent(event);
        return true;
    }

    public void setOnDateSelectListener(OnDateSelectListener listener) {
        if (mCalenderView != null) {
            mCalenderView.setOnDateSelectListener(listener);
        }
    }

    public interface OnDateSelectListener {
        void dateSelected(int year, int month, int day);
    }
}
