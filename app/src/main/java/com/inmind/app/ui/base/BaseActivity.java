package com.inmind.app.ui.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by lixiang on 2017/8/19.
 */
public abstract class BaseActivity extends AppCompatActivity{
    private Dialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState){
        super.onCreate(savedInstanceState, persistentState);
        initPresenter();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        destroyPresenter();
    }

    protected abstract void initPresenter();

    protected abstract void destroyPresenter();

    protected void showLoadingInner(boolean cancelable){
        if(mLoadingDialog == null){
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setTitle("Loading...");
        }
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.show();
    }

    protected void hideLoadingInner(){
        if(mLoadingDialog != null){
            mLoadingDialog.hide();
        }
    }

    protected void showToastInner(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
