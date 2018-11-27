package com.inmind.app.mvp;

/**
 * Created by lixiang on 2017/8/19.
 */
public interface IPresenter {
    void attachView(IView view);

    void onDestroy();
}