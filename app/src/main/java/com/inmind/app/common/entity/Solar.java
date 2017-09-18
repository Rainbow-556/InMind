package com.inmind.app.common.entity;

import java.io.Serializable;

/**
 * Created by lixiang on 2017/1/10.
 * 公历
 */
public class Solar implements Serializable{
    public int solarDay;
    /**1 ~ 12*/
    public int solarMonth;
    public int solarYear;

    @Override
    public String toString(){
        return "(" + solarYear + "-" + solarMonth + "-" + solarDay + ")";
    }
}
