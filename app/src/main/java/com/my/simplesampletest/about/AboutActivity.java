package com.my.simplesampletest.about;

import android.os.Bundle;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 从MainActivity点击菜单关于
 *
 * Created by YJH on 2016/6/28.
 */
public class AboutActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("关于SimpleDemo(V0.1)");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
