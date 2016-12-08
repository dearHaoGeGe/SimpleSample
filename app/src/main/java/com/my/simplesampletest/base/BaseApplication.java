package com.my.simplesampletest.base;

import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.my.simplesampletest.add_local_pic.common.LocalImageHelper;
import com.my.simplesampletest.commonutils.CrashHandler;
import com.zgh.stylelib.style.StyleHelper;

import java.io.File;

/**
 * 项目的Application
 * <p/>
 * Created by YJH on 2016/6/4.
 */
public class BaseApplication extends Application {

    public Context context;
    private static BaseApplication app = null;

    private Display display;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        app = this;

        initData();
    }

    public static BaseApplication getInstance() {
        return app;
    }

    private void initData() {
        initDisplay();
        /** 本地图片辅助类初始化  (发说说读取照片时候添加的) */
        LocalImageHelper.init(this);

        /** 夜间模式在BaseApplication中初始化，默认的有网易、百度、白天的模式 */
        StyleHelper.init(this, "wangyi", "baidu", "day");

        /** 初始化捕获程序异常 */
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
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

    private void initDisplay() {
        if (display == null) {
            WindowManager windowManager = (WindowManager)
                    getSystemService(Context.WINDOW_SERVICE);
            display = windowManager.getDefaultDisplay();
        }
    }

    /**
     * 获取当前屏幕的宽度
     *
     * @return
     */
    public int getWindowWidth() {
        return display.getWidth();
    }

    /**
     * 获取当前屏幕的高度
     *
     * @return
     */
    public int getWindowHeight() {
        return display.getHeight();
    }

    /**
     * 获取当前屏幕一半宽度
     *
     * @return
     */
    public int getHalfWidth() {
        return display.getWidth() / 2;
    }

    /**
     * 获取当前屏幕1/4宽度
     *
     * @return
     */
    public int getQuarterWidth() {
        return display.getWidth() / 4;
    }
}
