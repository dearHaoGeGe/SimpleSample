package com.my.simplesampletest.ormlitedemo;

import android.os.Bundle;
import android.widget.Button;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * Created by YJH on 2016/6/17.
 */
public class ORMLiteActivity extends BaseActivity {

    private Button btn_add_ORMLiteAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ormlite);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_add_ORMLiteAct= (Button) findViewById(R.id.btn_add_ORMLiteAct);
    }

    @Override
    public void initData() {

    }
}
