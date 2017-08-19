package com.inmind.app.common.entity;

import android.text.TextUtils;

/**
 * Created by lixiang on 2017/1/13.
 */
public final class BirthdayModel{
    public String realName;
    public String nickName;
    /**1男，2女*/
    public int sex;
    /**1过农历，2过阳历*/
    public int birthdayType;
    /**生日农历年*/
    public int lunarYear;
    /**生日农历月*/
    public int lunarMonth;
    /**生日农历日*/
    public int lunarDay;
    /**生日阳历年*/
    public int solarYear;
    /**生日阳历月*/
    public int solarMonth;
    /**生日阳历日*/
    public int solarDay;
    /**生肖*/
    public String animal;

    @Override
    public String toString(){
        return "realName:"+returnNotEmptyString(realName)+","
                +"nickName:"+returnNotEmptyString(nickName)+","
                +"sex:"+String.valueOf(sex)+","
                +"realName:"+returnNotEmptyString(realName)+","
                +"realName:"+returnNotEmptyString(realName)+","
                +"realName:"+returnNotEmptyString(realName)+","
                ;
    }

    public static String returnNotEmptyString(String s){
        return TextUtils.isEmpty(s)?"":s;
    }
}
