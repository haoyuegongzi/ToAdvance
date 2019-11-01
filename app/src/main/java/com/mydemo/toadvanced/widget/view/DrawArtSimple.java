package com.mydemo.toadvanced.widget.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.mydemo.toadvanced.widget.view.draw.AbstractBaseDrawCore;
import com.mydemo.toadvanced.widget.view.utils.PxUtil;

/**
 * Author: ZhongB (艾文）
 * Email:zhongbin@migu.cn
 * Time: 2017/6/2
 * TODO
 */
public class DrawArtSimple extends AbstractBaseDrawCore {

    private static final String[] WEEKS = {"一", "二", "三", "四", "五", "六", "日"};
    private float mWeekBaseLine = 0;
    private float mDayBaseLine = 0;

    int mDownX;
    int mDownY;
    int mTouchedItem;
    int mInitialTarget;
    int mTouchSlopSquared;
    private CheckForTap mPendingCheckForTap;

    private int mSelectStartDay = -1;
    private int mSelectEndDay = -1;

    public DrawArtSimple(DayDisplayView view) {
        super(view);
        mTouchSlopSquared =
                ViewConfiguration.get(view.getContext()).getScaledTouchSlop()
                        * ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
    }

    @Override
    public void drawWeek(Canvas g) {
        if (mWeekRect == null) {
            initWeekRect();
        }
        float itemWidth = mWeekRect.width() / 7.0f;
        mWeekPaint.setColor(mWeekTxtColor);
        if (mWeekBaseLine == 0) {
            Paint.FontMetricsInt fontMetrics = mWeekPaint.getFontMetricsInt();
            mWeekBaseLine = (mWeekRect.bottom + mWeekRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        }
        for (int i = 0; i < WEEKS.length; i++) {
            g.drawText(WEEKS[i], itemWidth * i + itemWidth / 2.0f, mWeekBaseLine, mWeekPaint);
        }

        mWeekPaint.setColor((mWeekTxtColor & 0X00FFFFFF) | 0X44000000);
        g.drawLine(
                mWeekRect.left,
                mWeekRect.bottom - PxUtil.dip2px(mDrawView.getContext(), 3),
                mWeekRect.right,
                mWeekRect.bottom - PxUtil.dip2px(mDrawView.getContext(), 3),
                mWeekPaint);
    }

    @Override
    public synchronized void drawDay(Canvas g) {
        if (mDayRect == null) {
            initDayRect();
        }
        if (mDaysInMonth > 0) {
            float itemDayHeight = mDayRect.height() / SHOW_WEEKS;
            if (mDayBaseLine == 0) {
                Paint.FontMetricsInt fontMetrics = mDayPaint.getFontMetricsInt();
                mDayBaseLine = (mDayRect.top + mDayRect.top + itemDayHeight - fontMetrics.bottom - fontMetrics.top) / 2;
            }
            int wrap = mDayOfWeekStart - 1;
            if (wrap == 0) {
                wrap = 7;
            }
            int drawCount = 1;
            float offsetY = mDayBaseLine;
            float offsetX;
            float raws = 0;
            float itemWidth = mWeekRect.width() / 7.0f;
            while (drawCount <= mDaysInMonth) {
                offsetX = (wrap - 1) * itemWidth + itemWidth / 2.0f;
                if (drawCount == mTouchedItem || drawCount == mSelectStartDay) {
                    mSelectPaint.setColor(mDaySelectedBgColor);
                    g.drawCircle(
                            offsetX,
                            mDayRect.top + raws * itemDayHeight + itemDayHeight / 2.0f,
                            Math.min(itemWidth, itemDayHeight) / 2.0f,
                            mSelectPaint);
                    mDayPaint.setColor(mDaySelectedTxtColor);
                } else {
                    if (drawCount == mToday) {
                        mDayPaint.setColor(mDayCurrentColor);
                    } else {
                        mDayPaint.setColor(mDayTxtColor);
                    }
                }
                g.drawText(String.valueOf(drawCount), offsetX, offsetY, mDayPaint);
                wrap++;
                drawCount++;
                if (wrap > 7) {
                    wrap = 1;
                    raws++;
                    offsetY = offsetY + itemDayHeight;
                }
            }
        }
    }

    private void initWeekRect() {
        int weekHeight = Math.round(PxUtil.getFontHeight(new Paint(Paint.ANTI_ALIAS_FLAG), mWeekTxtSize)) * 2;
        mWeekRect = new RectF(mPaddingLeft, mPaddingTop, mWidth - mPaddingLeft - mPaddingRight, mPaddingTop + weekHeight);
    }

    private void initDayRect() {
        int itemDayHeight = Math.round(PxUtil.getFontHeight(new Paint(Paint.ANTI_ALIAS_FLAG), mWeekTxtSize)) * 2;
        int rectHeight = Math.min(itemDayHeight * 7, (int) (mHeight - mWeekRect.height()));
        mDayRect =
                new RectF(mPaddingLeft, mWeekRect.bottom, mWidth - mPaddingLeft - mPaddingRight, mWeekRect.bottom + rectHeight);
    }

    @Override
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int weekHeight = Math.round(PxUtil.getFontHeight(new Paint(Paint.ANTI_ALIAS_FLAG), mWeekTxtSize));
        float peiSize = weekHeight * 2;
        /**
         * 设置宽度
         */
        int specMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int specSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int width;
        if (specMode == View.MeasureSpec.EXACTLY) { // match_parent , accurate
            width = specSize;
        } else {
            if (specMode == View.MeasureSpec.AT_MOST) { // wrap_content
                int desire = (int) (mPaddingLeft + mPaddingRight + peiSize * 7);
                width = Math.min(desire, specSize);
            } else {
                width = (int) (mPaddingLeft + mPaddingRight + peiSize * 7);
            }
        }
        /***
         * 设置高度
         */
        int height;
        specMode = View.MeasureSpec.getMode(heightMeasureSpec);
        specSize = View.MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == View.MeasureSpec.EXACTLY) { // match_parent , accurate
            height = specSize;
        } else {
            if (specMode == View.MeasureSpec.AT_MOST) { // wrap_content
                int desire = (int) (mPaddingTop + mPaddingBottom + peiSize * (SHOW_WEEKS + 1));
                height = Math.min(desire, specSize);
            } else {
                height = (int) (mPaddingTop + mPaddingBottom + peiSize * (SHOW_WEEKS + 1));
            }
        }
        int[] size = new int[2];
        size[0] = width;
        size[1] = height;
        return size;
    }

    private final class CheckForTap implements Runnable {

        @Override
        public void run() {
            mTouchedItem = getDayAtLocation(mDownX, mDownY);
            mDrawView.invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int x = (int) (event.getX() + 0.5f);
        final int y = (int) (event.getY() + 0.5f);

        final int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            mDownX = x;
            mDownY = y;
            mInitialTarget = getDayAtLocation(mDownX, mDownY);

            if (mInitialTarget < 0) {
                return false;
            }

            if (mPendingCheckForTap == null) {
                mPendingCheckForTap = new CheckForTap();
            }
            mDrawView.postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());

        } else if (action == MotionEvent.ACTION_MOVE) {
            if (!isStillAClick(x, y)) {
                if (mPendingCheckForTap != null) {
                    mDrawView.removeCallbacks(mPendingCheckForTap);
                }
                mInitialTarget = -1;
                if (mTouchedItem >= 0) {
                    mTouchedItem = -1;
                    mDrawView.invalidate();
                }
            }

        } else if (action == MotionEvent.ACTION_UP) {
            if (mInitialTarget != -1) {
                onDayClicked(mInitialTarget);
                mSelectStartDay = mInitialTarget;
            }
            mDrawView.invalidate();

        } else if (action == MotionEvent.ACTION_CANCEL) {
            if (mPendingCheckForTap != null) {
                mDrawView.removeCallbacks(mPendingCheckForTap);
            }
            // Reset touched day on stream end.
            mTouchedItem = -1;
            mInitialTarget = -1;
            mDrawView.invalidate();
        }
        return true;
    }

    @Override
    public void setSelectedDay(int day) {
        mSelectStartDay = day;
    }

    public int getDayAtLocation(int x, int y) {
        if (x > mDayRect.left && x < mDayRect.right && y > mDayRect.top && y < mDayRect.bottom) {
            int dx = (int) ((x - mDayRect.left) / (mDayRect.width() / 7)) + 1;
            int dy = (int) ((y - mDayRect.top) / (mDayRect.height() / SHOW_WEEKS)) + 1;
            int wrap = mDayOfWeekStart - 1;
            if (wrap == 0) {
                wrap = 7;
            }
            int value = (dy - 1) * 7 + dx - wrap + 1;
            if (value >= 1 && value <= mDaysInMonth) {
                return value;
            }
        }
        return -1;
    }

    private boolean isStillAClick(int x, int y) {
        return (((x - mDownX) * (x - mDownX)) + ((y - mDownY) * (y - mDownY))) <= mTouchSlopSquared;
    }
}
