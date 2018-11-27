package com.inmind.app.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class RunUtil {
    private static final Executor THREAD_POOL = AsyncTask.THREAD_POOL_EXECUTOR;
    private static final Handler UI_HANDLER = new Handler(Looper.getMainLooper());

    public static <T> void runOnWorkThread(final Work<T> work, final ExecutionCallback<T> callback) {
        if (work == null) {
            if (callback != null) {
                callback.onExecuted(null, "work is null.");
            }
            return;
        }
        AsyncTask<Void, Void, T> task = new AsyncTask() {
            @Override
            protected T doInBackground(Object[] objects) {
                T data = null;
                try {
                    data = work.execute();
                } catch (Exception e) {
                    work.errMsg = e.getMessage();
                }
                return data;
            }

            @Override
            protected void onPostExecute(Object o) {
                if (callback != null) {
                    callback.onExecuted((T) o, work.errMsg);
                }
            }
        };
        task.executeOnExecutor(THREAD_POOL);
    }

    public static void runOnUIThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        UI_HANDLER.post(runnable);
    }

    public static abstract class Work<T> {
        public String errMsg;

        public abstract T execute() throws Exception;
    }
}
