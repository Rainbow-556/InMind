package com.inmind.app.common;

import android.app.Activity;
import android.view.View;

/**
 * Created by lixiang on 2017/8/20.
 */
public final class ViewUtil{

    public static <T extends View> T findView(Object container, int id){
        if(container instanceof Activity){
            return (T) ((Activity) container).findViewById(id);
        }else if(container instanceof View){
            return (T) ((View) container).findViewById(id);
        }else{
            return null;
        }
    }
}
