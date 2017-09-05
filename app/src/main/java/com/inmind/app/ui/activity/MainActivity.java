package com.inmind.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inmind.app.R;
import com.inmind.app.common.ViewUtil;
import com.inmind.app.common.entity.Person;
import com.inmind.app.model.PersonRepositoryImpl;
import com.inmind.app.ui.base.BaseActivity;
import com.inmind.app.ui.mvp.contract.HomeContract;
import com.inmind.app.ui.mvp.presenter.HomePresenter;

import java.util.List;

public final class MainActivity extends BaseActivity implements HomeContract.IHomeView, View.OnClickListener{
    private HomeContract.IHomePresenter mHomePresenter;
    private ImageView ivOption;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mHomePresenter.fetchPerson();
    }

    @Override
    protected int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    protected void initView(){
        TextView tvTitle = ViewUtil.findView(this, R.id.tv_title);
        tvTitle.setText("In Mind");
        ivOption = ViewUtil.findView(this, R.id.iv_option);
        ivOption.setVisibility(View.VISIBLE);
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
        mHomePresenter = new HomePresenter(new PersonRepositoryImpl());
        mHomePresenter.attachView(this);
    }

    @Override
    protected void destroyPresenter(){
        mHomePresenter.detachView();
    }

    @Override
    public void showUserList(List<Person> users){

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.iv_option:
                goAddPerson();
                break;
        }
    }

    private void goAddPerson(){
        Intent intent = new Intent(mThisActivity, AddPersonActivity.class);
        startActivity(intent);
    }
}
