package com.my.simplesampletest.base;

import android.app.Application;
import android.content.Context;

/**
 * 项目的Application
 *
 * Created by YJH on 2016/6/4.
 */
public class BaseApplication extends Application {

    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context=getApplicationContext();
    }
}
