package com.my.simplesampletest.act_life_cycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.my.simplesampletest.R;

/**
 * 跳到这个activity
 *
 * Created by YJH on 2016/6/19.
 */
public class LifeCycle2Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
    }
}
