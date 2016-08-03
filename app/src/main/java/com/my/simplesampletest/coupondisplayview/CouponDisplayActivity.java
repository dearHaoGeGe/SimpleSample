package com.my.simplesampletest.coupondisplayview;

import android.os.Bundle;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * android自定义控件—边缘凹凸的View
 * <p/>
 * Created by YJH on 2016/6/5.
 */
public class CouponDisplayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_display);

        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        /** Android 项目中图片压缩看我的blog就够了 图片处理 http://blog.csdn.net/chivalrousman/article/details/49743361*/
    }
}
