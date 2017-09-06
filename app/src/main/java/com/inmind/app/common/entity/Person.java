package com.inmind.app.common.entity;

import android.text.TextUtils;

import com.inmind.app.common.CommonUtil;
import com.inmind.app.common.LunarSolarConverter;

import java.util.Calendar;

/**
 * Created by lixiang on 2017/9/4.
 */
public class Person{
    public int id;
    public String realName;
    public String nickName;
    /**true过农历，false过公历*/
    public boolean isLunar;
    /**农历*/
    public Lunar lunar;
    /**公历*/
    public Solar solar;
    public long remainDays;
    public String birthdayDateText;

    /**
     * @param nickName
     * @param year
     * @param month
     * @param day
     * @param isLunar
     * @param isLeap
     */
    public Person(String nickName, int year, int month, int day, boolean isLunar, boolean isLeap){
        this.nickName = nickName;
        this.isLunar = isLunar;
        if(isLunar){
            // 农历
            lunar = new Lunar();
            lunar.lunarYear = year;
            lunar.lunarMonth = month;
            lunar.lunarDay = day;
            lunar.isLeap = isLeap;
            lunar.animal = LunarSolarConverter.lunarYearToShengXiao(year);
            solar = LunarSolarConverter.lunarToSolar(lunar);
        }else{
            // 公历
            solar = new Solar();
            solar.solarYear = year;
            solar.solarMonth = month;
            solar.solarDay = day;
            lunar = LunarSolarConverter.solarToLunar(solar);
        }
        calcRemainDays();
    }

    public void calcRemainDays(){
        Calendar cur = Calendar.getInstance();
        int year = cur.get(Calendar.YEAR);
        int month, day;
        if(!isLunar){
            month = solar.solarMonth;
            day = solar.solarDay;
        }else{
            Lunar tempLunar = new Lunar();
            tempLunar.lunarYear = year;
            tempLunar.lunarMonth = lunar.lunarMonth;
            tempLunar.lunarDay = lunar.lunarDay;
            Solar solar = LunarSolarConverter.lunarToSolar(tempLunar);
            year = solar.solarYear;
            month = solar.solarMonth;
            day = solar.solarDay;
        }
        long days = CommonUtil.calcDiffDays(year, month, day);
        if(days < 0){
            days = CommonUtil.calcDiffDays(year + 1, month, day);
        }
        this.remainDays = days;
    }

    public void initExtraFields(){
        calcRemainDays();
        this.birthdayDateText = LunarSolarConverter.formatBirthdayDate(this);
    }

    @Override
    public String toString(){
        return "id=" + id + ", realName=" + returnNotEmptyString(realName) + ", "
                + "nickName=" + returnNotEmptyString(nickName) + ", "
                + "农历=" + lunar.toString() + ", "
                + "公历=" + solar.toString();
    }

    @Override
    public boolean equals(Object obj){
        return ((Person)obj).id == this.id;
    }

    public static String returnNotEmptyString(String s){
        return TextUtils.isEmpty(s) ? "" : s;
    }
}
