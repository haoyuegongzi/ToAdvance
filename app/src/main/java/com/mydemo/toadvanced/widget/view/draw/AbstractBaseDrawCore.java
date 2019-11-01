package com.mydemo.toadvanced.widget.view.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.mydemo.toadvanced.widget.view.DayDisplayView;
import com.mydemo.toadvanced.widget.view.utils.CalendarUtil;

import java.util.Calendar;

/**
 * Author: ZhongB (艾文）
 * Email:zhongbin@migu.cn
 * Time: 2017/6/2
 */
public abstract class AbstractBaseDrawCore {

    protected static final int SHOW_WEEKS = 6;

    protected DayDisplayView mDrawView;

    /**
     * 周画笔
     */
    protected Paint mWeekPaint;
    /**
     * 日期画笔
     */
    protected Paint mDayPaint;
    /**
     * 不可用状态
     */
    protected Paint mUnablePaint;

    /**
     * 选中状态
     */
    protected Paint mSelectPaint;

    protected int mWidth;
    protected int mHeight;

    // Week
    protected int mWeekTxtColor;
    protected int mWeekTxtSize = 16;

    // Day
    protected int mDayTxtColor;
    protected int mDayTxtSize = 16;

    // Select
    protected int mDayCurrentColor;
    protected int mDaySelectedBgColor;
    protected int mDaySelectedTxtColor;

    protected int mUnableColor = 0X22999999;

    // padding
    protected int mPaddingLeft, mPaddingRight, mPaddingTop, mPaddingBottom;

    protected RectF mWeekRect;
    protected RectF mDayRect;

    // data
    protected int mYear;
    protected int mMonth;
    protected int mDay;
    protected int mDayOfWeekStart;
    protected int mDaysInMonth;
    protected int mToday;

    private Calendar mCalendar;

    private DayDisplayView.OnDateSelectListener mSelectedListener;

    public AbstractBaseDrawCore(DayDisplayView view) {
        this.mDrawView = view;
        mCalendar = Calendar.getInstance();
    }

    public final void initColor(int weekColor, int dayColor, int todayColor, int selectedBgColor, int selectedTxtColor) {
        this.mWeekTxtColor = weekColor;
        this.mDayTxtColor = dayColor;
        this.mDayCurrentColor = todayColor;
        this.mDaySelectedBgColor = selectedBgColor;
        this.mDaySelectedTxtColor = selectedTxtColor;
    }

    public final void initTextSize(int weekTvSize, int dayTvSize) {
        this.mWeekTxtSize = weekTvSize;
        this.mDayTxtSize = dayTvSize;
    }

    public final void initSize(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public final void initPadding(int left, int top, int right, int bottom) {
        this.mPaddingLeft = left;
        this.mPaddingTop = top;
        this.mPaddingRight = right;
        this.mPaddingBottom = bottom;
    }

    public final void initPaint() {
        mWeekPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWeekPaint.setColor(mWeekTxtColor);
        mWeekPaint.setTextSize(mWeekTxtSize);
        mWeekPaint.setTextAlign(Paint.Align.CENTER);

        mDayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDayPaint.setTextSize(mDayTxtSize);
        mDayPaint.setTextAlign(Paint.Align.CENTER);

        mUnablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnablePaint.setColor(mUnableColor);
        mSelectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSelectPaint.setColor(mDaySelectedTxtColor);
    }

    public void setDate(int year, int month, int day) {
        if (CalendarUtil.isValidMonth(month)) {
            this.mMonth = month;
        }
        mYear = year;
        mCalendar.set(Calendar.MONTH, mMonth);
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        mToday = -1;
        mDay = day;
        setSelectedDay(day);
        mDayOfWeekStart = mCalendar.get(Calendar.DAY_OF_WEEK);
        mDaysInMonth = CalendarUtil.getDaysInMonth(month, year);
        final Calendar todayCalendar = Calendar.getInstance();
        for (int i = 0; i < mDaysInMonth; i++) {
            final int today = i + 1;
            if (sameDay(today, todayCalendar)) {
                mToday = today;
            }
        }
    }

    public void setDate(int year, int month) {
        setDate(year, month, -1);
    }

    private boolean sameDay(int day, Calendar today) {
        return mYear == today.get(Calendar.YEAR)
                && mMonth == today.get(Calendar.MONTH)
                && day == today.get(Calendar.DAY_OF_MONTH);
    }

    protected void onDayClicked(int targetDay) {
        mDay = targetDay;
        if (mSelectedListener != null) {
            mSelectedListener.dateSelected(mYear, mMonth, mDay);
        }
    }

    public void setOnDateSelectListener(DayDisplayView.OnDateSelectListener listener) {
        mSelectedListener = listener;
    }

    public abstract void drawWeek(Canvas g);

    public abstract void drawDay(Canvas g);

    public abstract int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec);

    public abstract boolean onTouchEvent(MotionEvent event);

    public abstract void setSelectedDay(int day);
}
