package com.my.simplesampletest.segment_view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * RadioGroup实现类似ios的分段选择(UISegmentedControl)控件
 *
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/0512/1615.html
 * https://github.com/Kaopiz/android-segmented-control
 *
 * Android开发的精华包括工具、中文API
 * http://www.androiddevtools.cn/
 *
 * Created by YJH on 2016/7/9.
 */
public class SegmentViewActivity extends BaseActivity implements View.OnClickListener {

    private TextView approveAgreeTV,approveDisagreeTV,defaultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_iew);

        initView();
        initData();
    }

    @Override
    public void initView() {
        approveAgreeTV= (TextView) findViewById(R.id.approveAgreeTV);
        approveDisagreeTV= (TextView) findViewById(R.id.approveDisagreeTV);
        defaultTV= (TextView) findViewById(R.id.defaultTV);

        approveAgreeTV.setOnClickListener(this);
        approveDisagreeTV.setOnClickListener(this);
        defaultTV.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.approveAgreeTV:
                initButtonState();
                approveAgreeTV.setBackgroundResource(R.drawable.button_left_press);
                approveAgreeTV.setTextColor(Color.parseColor("#ffffff"));

                Snackbar.make(approveAgreeTV,"同意",Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.approveDisagreeTV:
                initButtonState();
                approveDisagreeTV.setBackgroundResource(R.drawable.button_right_press);
                approveDisagreeTV.setTextColor(Color.parseColor("#ffffff"));

                Snackbar.make(approveAgreeTV,"不同意",Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.defaultTV:
                initButtonState();
                defaultTV.setBackgroundResource(R.drawable.button_default_press);
                defaultTV.setTextColor(Color.parseColor("#ffffff"));
                Snackbar.make(approveAgreeTV,"默认",Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 初始化默认的按钮的状态
     */
    private void initButtonState(){
        approveAgreeTV.setBackgroundResource(R.drawable.button_left_release);
        approveDisagreeTV.setBackgroundResource(R.drawable.button_right_release);
        defaultTV.setBackgroundResource(R.drawable.button_default_release);

        approveAgreeTV.setTextColor(Color.parseColor("#6FD5FC"));
        approveDisagreeTV.setTextColor(Color.parseColor("#6FD5FC"));
        defaultTV.setTextColor(Color.parseColor("#6FD5FC"));
    }
}
