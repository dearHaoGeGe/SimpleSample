package com.my.simplesampletest.blurred_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 把图片作为背景模糊处理，动态模糊效果
 *
 * 参考：http://blog.csdn.net/wl9739/article/details/51955598
 *
 * Created by YJH on 2016/8/3.
 */
public class BlurredViewAct extends BaseActivity implements View.OnClickListener {

    private Button btn1_BlurredViewAct,btn2_BlurredViewAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurred_view);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn1_BlurredViewAct= (Button) findViewById(R.id.btn1_BlurredViewAct);
        btn2_BlurredViewAct= (Button) findViewById(R.id.btn2_BlurredViewAct);

    }

    @Override
    public void initData() {
        btn1_BlurredViewAct.setOnClickListener(this);
        btn2_BlurredViewAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1_BlurredViewAct:
                startActivity(new Intent(this,BlurredViewBasicAct.class));
                break;

            case R.id.btn2_BlurredViewAct:
                startActivity(new Intent(this,WeatherActivity.class));
                break;
        }
    }
}
