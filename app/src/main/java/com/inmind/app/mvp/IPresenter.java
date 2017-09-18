package com.inmind.app.mvp;

/**
 * Created by lixiang on 2017/8/19.
 */
public interface IPresenter<V extends IView>{

    void attachView(V view);

    boolean isViewInactive();

    void detachView();
}