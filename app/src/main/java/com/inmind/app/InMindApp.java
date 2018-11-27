package com.inmind.app;

import android.app.Application;

/**
 * Created by lixiang on 2017/8/19.
 */
public class InMindApp extends Application{
    private static InMindApp sInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
    }

    public static InMindApp getInstance(){
        return sInstance;
    }
}
