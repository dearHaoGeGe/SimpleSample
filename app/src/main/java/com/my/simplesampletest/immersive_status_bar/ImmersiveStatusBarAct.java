package com.my.simplesampletest.immersive_status_bar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 学习沉浸式状态栏
 * 参考：http://blog.csdn.net/guolin_blog/article/details/51763825
 * <p/>
 * Created by YJH on 2016/8/23.
 */
public class ImmersiveStatusBarAct extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive_status_bar);

        //FULLSCREEN();     //全屏
        transParentStatusBar();   //透明状态栏效果
        //hideBottomNavigationBar();//隐藏底部导航栏(在这种模式下，触摸屏幕的任意位置都会退出全屏)
        //transParentStatusBarAndBottomNavigationBar(); //透明状态栏和底部导航栏

        initView();
        initView();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    /**
     * 真正的沉浸式状态栏    (如果用其他的模式把这个方法给注掉)
     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }

    /**
     * 全屏
     */
    private void FULLSCREEN() {
        View decorView = getWindow().getDecorView();  //获取到了当前界面的DecorView
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;  //SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏
        decorView.setSystemUiVisibility(option);    //设置系统UI元素的可见性
        hideActionBar();
    }

    /**
     * 透明状态栏效果
     */
    private void transParentStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();  //获取到了当前界面的DecorView
            //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN和SYSTEM_UI_FLAG_LAYOUT_STABLE，
            //注意两个Flag必须要结合在一起使用，表示会让应用的主体内容占用系统状态栏的空间
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);   //将状态栏设置成透明色
        }
        hideActionBar();
    }

    /**
     * 隐藏底部导航栏
     * <p/>
     * 在这种模式下，触摸屏幕的任意位置都会退出全屏
     */
    private void hideBottomNavigationBar() {
        View decorView = getWindow().getDecorView();  //获取到了当前界面的DecorView
        //将状态栏和导航栏同时隐藏
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        hideActionBar();
    }

    /**
     * 透明状态栏和底部导航栏
     */
    private void transParentStatusBarAndBottomNavigationBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();  //获取到了当前界面的DecorView
            //SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION，表示会让应用的主体内容占用系统导航栏的空间
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        hideActionBar();
    }



    /**
     * 隐藏ActionBar效果
     */
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
