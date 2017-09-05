package com.inmind.app.common.entity;

/**
 * Created by lixiang on 2017/1/10.
 * 农历
 */
public class Lunar{
    /**
     * 是否为闰月
     */
    public boolean isLeap;
    public int lunarDay;
    public int lunarMonth;
    public int lunarYear;
    public String animal;

    @Override
    public String toString(){
        return "(" + animal + ", " + lunarYear + "-" + lunarMonth + "-" + lunarDay + ", isLeap=" + isLeap + ")";
    }
}
