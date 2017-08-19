package com.inmind.app.common;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class RunUtil{
    private static final Executor THREAD_POOL = AsyncTask.THREAD_POOL_EXECUTOR;
    private static final Handler UI_HANDLER = new Handler(Looper.getMainLooper());

    public static <T> void runOnWorkThread(final Work<T> work, final ExecutionCallback<T> callback){
        if(work == null){
            if(callback != null){
                callback.onExecuted(null, "work is null.");
            }
            return;
        }
        AsyncTask<Void, Void, T> task = new AsyncTask(){
            @Override
            protected T doInBackground(Object[] objects){
                return work.execute();
            }

            @Override
            protected void onPostExecute(Object o){
                if(callback != null){
                    callback.onExecuted((T) o, null);
                }
            }
        };
        task.executeOnExecutor(THREAD_POOL);
    }

    public static void runOnUIThread(Runnable runnable){
        if(runnable == null){
            return;
        }
        UI_HANDLER.post(runnable);
    }

    public interface Work<T>{
        T execute();
    }
}
