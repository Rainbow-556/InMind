package com.inmind.app.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by lixiang on 2018/11/27.<br/>
 */
public class BasePresenter<V extends IView> implements IPresenter {
    private WeakReference<IView> mViewRef;

    protected V getView() {
        return mViewRef != null ? (V) mViewRef.get() : null;
    }

    @Override
    public void attachView(IView view) {
        if (mViewRef != null) {
            mViewRef.clear();
        }
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void onDestroy() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
