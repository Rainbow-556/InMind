package com.inmind.app.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.inmind.app.mvp.IPresenter;
import com.inmind.app.mvp.IView;
import com.inmind.app.util.ToastUtil;
import com.inmind.app.ui.dialog.LoadingDialog;

import java.util.ArrayList;

/**
 * Created by lixiang on 2017/8/19.
 */
public abstract class BaseActivity extends AppCompatActivity implements IView {
    private LoadingDialog mLoadingDialog;
    protected BaseActivity mThisActivity;
    private ArrayList<IPresenter> mPresenters = new ArrayList<>(4);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThisActivity = this;
        setContentView(getLayoutId());
        initView();
        initPresenter();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        destroyPresenter();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected final void addPresenter(IPresenter presenter) {
        mPresenters.add(presenter);
    }

    private void destroyPresenter() {
        for (IPresenter presenter : mPresenters) {
            presenter.onDestroy();
        }
        mPresenters.clear();
    }

    @Override
    public void showLoading(boolean cancelable) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(mThisActivity);
        }
        mLoadingDialog.show(cancelable);
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(mThisActivity, msg);
    }
}
