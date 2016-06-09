package com.my.simplesampletest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 动态广播接收者
 *
 * Created by YJH on 2016/6/9.
 */
public class DynamicReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "动态注册，收到消息~"+intent.getIntExtra("传值",0), Toast.LENGTH_SHORT).show();
    }
}
