package com.inmind.app.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.inmind.app.R;
import com.inmind.app.common.entity.User;
import com.inmind.app.ui.base.BaseActivity;
import com.inmind.app.ui.mvp.contract.HomeContract;
import com.inmind.app.ui.mvp.presenter.UserPresenter;

import java.util.List;

public class MainActivity extends BaseActivity implements HomeContract.IHomeView{
    private HomeContract.IUserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserPresenter = new UserPresenter();
        mUserPresenter.attachView(this);
        mUserPresenter.fetchUsers();
    }

    @Override
    public void showLoading(boolean cancelable){
        showLoadingInner(cancelable);
    }

    @Override
    public void hideLoading(){
        hideLoadingInner();
    }

    @Override
    public void showToast(String msg){
        showToastInner(msg);
    }

    @Override
    protected void initPresenter(){
        mUserPresenter = new UserPresenter();
        mUserPresenter.attachView(this);
    }

    @Override
    protected void destroyPresenter(){
        mUserPresenter.detachView();
    }

    @Override
    public void showUserList(List<User> users){
        Log.e("lx", users.toString());
    }
}
