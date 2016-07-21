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

    private TextView approveAgreeTV,approveDisagreeTV;

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

        approveAgreeTV.setOnClickListener(this);
        approveDisagreeTV.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.approveAgreeTV:
                approveAgreeTV.setBackgroundResource(R.drawable.button_left_press);
                approveDisagreeTV.setBackgroundResource(R.drawable.button_right_release);
                approveAgreeTV.setTextColor(Color.parseColor("#ffffff"));
                approveDisagreeTV.setTextColor(Color.parseColor("#6FD5FC"));

                Snackbar.make(approveAgreeTV,"同意",Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.approveDisagreeTV:
                approveDisagreeTV.setBackgroundResource(R.drawable.button_right_press);
                approveAgreeTV.setBackgroundResource(R.drawable.button_left_release);
                approveDisagreeTV.setTextColor(Color.parseColor("#ffffff"));
                approveAgreeTV.setTextColor(Color.parseColor("#6FD5FC"));
                Snackbar.make(approveAgreeTV,"不同意",Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
