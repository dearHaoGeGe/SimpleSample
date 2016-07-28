package com.my.simplesampletest.base;

import android.app.Application;
import android.content.Context;

import java.io.File;

/**
 * 项目的Application
 *
 * Created by YJH on 2016/6/4.
 */
public class BaseApplication extends Application {

    public Context context;
    private static BaseApplication app=null;

    @Override
    public void onCreate() {
        super.onCreate();

        context=getApplicationContext();
        app=this;
    }

    public static BaseApplication getInstance(){
        return app;
    }

    public String getCachePath() {
        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = getExternalCacheDir();
        else
            cacheDir = getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        return cacheDir.getAbsolutePath();
    }
}
