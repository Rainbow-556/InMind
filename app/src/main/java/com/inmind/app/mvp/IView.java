package com.inmind.app.mvp;

/**
 * Created by lixiang on 2017/8/19.
 */
public interface IView{

    void showLoading(boolean cancelable);

    void hideLoading();

    void showToast(String msg);
}
