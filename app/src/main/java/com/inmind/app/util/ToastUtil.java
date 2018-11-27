package com.inmind.app.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class ToastUtil {
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
