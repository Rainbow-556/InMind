package com.inmind.app.mvp;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lixiang on 2018/11/27.<br/>
 */
public class BasePresenter<V extends IView> implements IPresenter {
    private WeakReference<IView> mViewRef;
    private V mProxyView;

    protected V getView() {
        return mProxyView;
    }

    @Override
    public void attachView(IView view) {
        if (mViewRef != null) {
            mViewRef.clear();
        }
        mViewRef = new WeakReference<>(view);
        // 使用动态代理实现在调用IView的方法时省略判断view是否为空的逻辑
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (mViewRef != null && mViewRef.get() != null) {
                    return method.invoke(mViewRef.get(), args);
                }
                return null;
            }
        });
    }

    @Override
    public void onDestroy() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
