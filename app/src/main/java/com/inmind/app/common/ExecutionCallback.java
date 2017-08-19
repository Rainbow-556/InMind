package com.inmind.app.common;

/**
 * Created by lixiang on 2017/8/19.
 */
public interface ExecutionCallback<T>{
    void onExecuted(T data, String err);
}
