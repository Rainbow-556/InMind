package com.inmind.app.common;

import java.io.Closeable;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class CommonUtil{
    private static final float DENSITY = 3;

    public void closeStream(Closeable closeable){
        try{
            if(closeable != null){
                closeable.close();
            }
        }catch(Exception e){
        }
    }

    public static float dp2px(float dp){
        return DENSITY * dp;
    }

    public static float px2dp(float px){
        return px / DENSITY;
    }
}
