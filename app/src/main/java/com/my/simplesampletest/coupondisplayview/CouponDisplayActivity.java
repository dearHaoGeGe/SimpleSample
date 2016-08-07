package com.my.simplesampletest.coupondisplayview;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.utils.To;
import com.my.simplesampletest.utils.ToastUtil;

/**
 * android自定义控件—边缘凹凸的View
 * <p/>
 * Created by YJH on 2016/6/5.
 */
public class CouponDisplayActivity extends BaseActivity {

    private CardView cardView_Demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_display);

        initView();
        initData();
    }

    @Override
    public void initView() {
        cardView_Demo= (CardView) findViewById(R.id.cardView_Demo);

        cardView_Demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(CouponDisplayActivity.this,"This is a CardView ···");
            }
        });
    }

    @Override
    public void initData() {
        /** Android 项目中图片压缩看我的blog就够了 图片处理 http://blog.csdn.net/chivalrousman/article/details/49743361*/
    }
}
