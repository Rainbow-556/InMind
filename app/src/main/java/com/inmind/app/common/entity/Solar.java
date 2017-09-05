package com.inmind.app.common.entity;

/**
 * Created by lixiang on 2017/1/10.
 * 公历
 */
public class Solar{
    public int solarDay;
    public int solarMonth;
    public int solarYear;

    @Override
    public String toString(){
        return "(" + solarYear + "-" + solarMonth + "-" + solarDay + ")";
    }
}
