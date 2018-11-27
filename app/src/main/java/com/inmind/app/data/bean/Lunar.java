package com.inmind.app.data.bean;

import java.io.Serializable;

/**
 * Created by lixiang on 2017/1/10.
 * 农历
 */
public class Lunar implements Serializable {
    /**
     * 是否为闰月
     */
    public boolean isLeap;
    public int lunarDay;
    /**
     * 1 ~ 12
     */
    public int lunarMonth;
    public int lunarYear;
    public String animal;

    @Override
    public String toString() {
        return "(" + animal + ", " + lunarYear + "-" + lunarMonth + "-" + lunarDay + ", isLeap=" + isLeap + ")";
    }
}
