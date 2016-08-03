package com.my.simplesampletest.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtil防止多次点击弹出多个
 * <p>
 * 先判断Toast对象是否为空，如果是空的情况下才会调用makeText()方法来去生成一个Toast对象，
 * 否则就直接调用setText()方法来设置显示的内容，
 * 最后再调用show()方法将Toast显示出来。
 * 由于不会每次调用的时候都生成新的Toast对象，
 * 因此刚才我们遇到的问题在这里就不会出现了
 * <p>
 * 参考：http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650236533&idx=1&sn=3bd24960b424f7a103daf4b15773a63f&scene=0#wechat_redirect
 * <p>
 * Created by YJH on 2016/8/3.
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
