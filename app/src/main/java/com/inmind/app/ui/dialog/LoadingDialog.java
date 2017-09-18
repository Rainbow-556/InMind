package com.inmind.app.ui.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class LoadingDialog extends BaseDialog{
    private final static int MSG_SHOW = 1;
    private final static int MSG_HIDE = 2;
    private final static int DELAY = 500;
    private Dialog mDialog;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case MSG_SHOW:
                    if(hasMessages(MSG_HIDE)){
                        break;
                    }
                    mDialog.show();
                    break;
                case MSG_HIDE:
                    mDialog.hide();
                    break;
            }
        }
    };

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
        mHandler.sendEmptyMessageDelayed(MSG_SHOW, DELAY);
    }

    @Override
    public void hide(){
        init();
        if(!mHandler.hasMessages(MSG_SHOW)){
            mHandler.sendEmptyMessage(MSG_HIDE);
        }else{
            mHandler.sendEmptyMessageDelayed(MSG_HIDE, DELAY);
        }
    }

    @Override
    public void dismiss(){
        mHandler.removeCallbacksAndMessages(null);
        if(mDialog == null){
            return;
        }
        mDialog.dismiss();
    }
}
