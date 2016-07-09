package com.my.simplesampletest.segment_view;

import android.os.Bundle;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * RadioGroup实现类似ios的分段选择(UISegmentedControl)控件
 *
 * Created by YJH on 2016/7/9.
 */
public class SegmentViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_iew);

        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
