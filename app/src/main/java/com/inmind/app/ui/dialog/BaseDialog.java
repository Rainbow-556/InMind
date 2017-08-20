package com.inmind.app.ui.dialog;

import android.content.Context;

/**
 * Created by lixiang on 2017/8/19.
 */
public abstract class BaseDialog{
    protected Context mContext;

    public BaseDialog(Context context){
        this.mContext = context;
    }

    public abstract void init();

    public abstract void show();

    public abstract void show(boolean cancelable);

    public abstract void hide();

    public abstract void dismiss();
}
