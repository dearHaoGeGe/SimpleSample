package com.my.simplesampletest.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity
 *
 * Created by YJH on 2016/6/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initView();
    public abstract void initData();
}
