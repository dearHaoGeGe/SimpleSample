package com.my.simplesampletest.segment_view;

import android.os.Bundle;

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
