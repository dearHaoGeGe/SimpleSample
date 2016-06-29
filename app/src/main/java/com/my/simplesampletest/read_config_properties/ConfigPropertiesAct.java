package com.my.simplesampletest.read_config_properties;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 读取assets文件夹下的config.properties文件
 *
 * Created by YJH on 2016/6/29.
 */
public class ConfigPropertiesAct extends BaseActivity implements View.OnClickListener {

    private Button btn_ConfigPropertiesAct;
    private TextView tv_ConfigPropertiesAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_properties);
        setTitle("读取config.properties文件");

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_ConfigPropertiesAct= (Button) findViewById(R.id.btn_ConfigPropertiesAct);
        tv_ConfigPropertiesAct= (TextView) findViewById(R.id.tv_ConfigPropertiesAct);

        btn_ConfigPropertiesAct.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ConfigPropertiesAct:

                break;
        }
    }
}
