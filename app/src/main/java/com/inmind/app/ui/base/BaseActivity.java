package com.inmind.app.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.inmind.app.common.ToastUtil;
import com.inmind.app.ui.dialog.BaseDialog;
import com.inmind.app.ui.dialog.LoadingDialog;

/**
 * Created by lixiang on 2017/8/19.
 */
public abstract class BaseActivity extends AppCompatActivity{
    private BaseDialog mLoadingDialog;
    protected BaseActivity mThisActivity;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mThisActivity = this;
        setContentView(getLayoutId());
        initView();
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

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract void destroyPresenter();

    protected void showLoadingInner(boolean cancelable){
        if(mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(mThisActivity);
        }
        mLoadingDialog.show(cancelable);
    }

    protected void hideLoadingInner(){
        if(mLoadingDialog != null){
            mLoadingDialog.hide();
        }
    }

    protected void showToastInner(String msg){
        ToastUtil.showToast(mThisActivity, msg);
    }
}
