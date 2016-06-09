package com.my.simplesampletest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 静态注册的广播接收者，在AndroidManifest中注册
 * 一定要加
 * <intent-filter>
 *     我们要监听的广播名字，注意action里的内容不要写错 包括大小写
 *     <action android:name="android.intent.action.AIRPLANE_MODE"/>
 * </intent-filter>
 *
 * Created by YJH on 2016/6/9.
 */
public class AirPlaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "飞行模式已经打开~", Toast.LENGTH_SHORT).show();
    }
}
