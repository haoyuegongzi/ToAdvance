package com.mydemo.toadvanced.widget.view.utils;

import android.content.Context;
import android.graphics.Paint;

/**
 * Author: ZhongB (艾文）
 * Email:zhongbin@migu.cn
 * Time: 2017/6/5
 * PX与DP之间的转换工具
 */
public class PxUtil {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getFontHeight(Paint paint, int size) {
        paint.setTextSize(size);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (float) Math.ceil(fm.descent - fm.ascent);
    }
}
