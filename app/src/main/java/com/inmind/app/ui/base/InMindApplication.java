package com.inmind.app.ui.base;

import android.app.Application;

/**
 * Created by lixiang on 2017/8/19.
 */
public class InMindApplication extends Application{
    private static InMindApplication sInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
    }

    public static InMindApplication getInstance(){
        return sInstance;
    }
}
