package com.my.simplesampletest.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * <p>
 * Created by YJH on 2016/6/8.
 */
public class To {

    /**
     * 控制是否显示
     */
    private static boolean isShow = true;

    public static void showShort(Context context, CharSequence text) {
        if (isShow) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showLong(Context context, CharSequence text) {
        if (isShow) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }
}
