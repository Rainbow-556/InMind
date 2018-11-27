package com.inmind.app.util;

import com.inmind.app.InMindApp;

import java.io.Closeable;
import java.util.Calendar;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class CommonUtil {
    private static final long MILLIS_OF_DAY = 24 * 60 * 60 * 1000;
    private static float DENSITY;

    static {
        DENSITY = InMindApp.getInstance().getResources().getDisplayMetrics().density;
    }

    public static void closeStream(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
        }
    }

    public static float dp2px(float dp) {
        return DENSITY * dp;
    }

    public static float px2dp(float px) {
        return px / DENSITY;
    }

    public static int getColor(int colorId) {
        return InMindApp.getInstance().getResources().getColor(colorId);
    }

    /**
     * 计算公历日期与当前日期之间的间隔天数
     * @param year
     * @param month 1 ~ 12
     * @param day
     * @return 同一天，返回0；返回天数大于0，则该日期比当前日期晚，小于0，则反之
     */
    public static long calcDiffDays(int year, int month, int day) {
        Calendar param = Calendar.getInstance();
        param.set(Calendar.YEAR, year);
        param.set(Calendar.MONTH, month - 1);
        param.set(Calendar.DAY_OF_MONTH, day);
        param.set(Calendar.HOUR_OF_DAY, 0);
        param.set(Calendar.MINUTE, 0);
        param.set(Calendar.SECOND, 0);
        Calendar cur = Calendar.getInstance();
        cur.set(Calendar.HOUR_OF_DAY, 0);
        cur.set(Calendar.MINUTE, 0);
        cur.set(Calendar.SECOND, 0);
        long diff = param.getTimeInMillis() - cur.getTimeInMillis();
        long days = diff / MILLIS_OF_DAY;
        return days;
    }
}
