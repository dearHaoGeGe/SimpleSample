package com.my.simplesampletest.add_local_pic;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * 在网上看的Activity基类
 * <p>
 * Created by YJH on 2016/7/24.
 */
public class BaseActivity extends AppCompatActivity {
    //应用是否销毁标志
    protected boolean isDestroy;
    //防止重复点击设置的标志，涉及到点击打开其他的Activity时，将该标志设置为false，在onResume中设置为true
    private boolean clickable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDestroy = false;
        //在V7包下的AppCompatActivity设置无标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);    继承Activity的时候全屏
        //垂直显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //每次返回界面时，将电机标志设置为可点击
        clickable = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroy = true;
        //结束Activity & 从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 当前是否可以点击
     *
     * @return
     */
    protected boolean isClickable() {
        return clickable;
    }

    /**
     * 锁定点击
     */
    protected void lockClick() {
        clickable = true;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        if (isClickable()) {
            lockClick();
            super.startActivityForResult(intent, requestCode, options);
        }
    }
}
