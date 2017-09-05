package com.inmind.app.ui.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class LoadingDialog extends BaseDialog{
    private Dialog mDialog;

    public LoadingDialog(Context context){
        super(context);
    }

    @Override
    public void init(){
        if(mDialog != null){
            return;
        }
        mDialog = new ProgressDialog(mContext);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setTitle("Loading...");
    }

    @Override
    public void show(){
        show(true);
    }

    @Override
    public void show(boolean cancelable){
        init();
        mDialog.setCancelable(cancelable);
        mDialog.show();
    }

    @Override
    public void hide(){
        init();
        mDialog.hide();
    }

    @Override
    public void dismiss(){
        if(mDialog == null){
            return;
        }
        mDialog.dismiss();
    }
}
