package com.inmind.app.util;

/**
 * Created by lixiang on 2017/8/19.
 */
public interface ExecutionCallback<T> {
    void onExecuted(T data, String err);
}
