package com.inmind.app.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.inmind.app.R;
import com.inmind.app.common.CommonUtil;
import com.inmind.app.common.ViewUtil;
import com.inmind.app.common.entity.User;
import com.inmind.app.model.UserRepositoryImpl;
import com.inmind.app.ui.base.BaseActivity;
import com.inmind.app.ui.mvp.contract.HomeContract;
import com.inmind.app.ui.mvp.presenter.UserPresenter;

import java.util.List;

public class MainActivity extends BaseActivity implements HomeContract.IHomeView, View.OnClickListener{
    private HomeContract.IUserPresenter mUserPresenter;
    private PopupWindow mPopupWindow;
    private ImageView ivOption;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mUserPresenter.fetchUsers();
    }

    @Override
    protected int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    protected void initView(){
        ivOption = ViewUtil.findView(this, R.id.iv_option);
        ivOption.setOnClickListener(this);
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
        mUserPresenter = new UserPresenter(new UserRepositoryImpl());
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

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.iv_option:
                showOptionWindow();
                break;
        }
    }

    private void showOptionWindow(){
        if(mPopupWindow == null){
            View view = getLayoutInflater().inflate(R.layout.pop_window_option, null);
            mPopupWindow = new PopupWindow(view, (int) CommonUtil.dp2px(130), (int) CommonUtil.dp2px(150));
        }
        mPopupWindow.showAsDropDown(ivOption);
    }
}
