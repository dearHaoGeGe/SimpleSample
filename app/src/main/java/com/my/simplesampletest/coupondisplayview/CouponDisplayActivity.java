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
 * android自定义控件—边缘凹凸的View、CardView的使用方法、分享弹窗ShareView
 *
 * CardView参考：http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261863&idx=1&sn=897060441bd3c7a1feb0fb18559538fa&scene=0#wechat_redirect
 *
 * ShareView参考：http://mp.weixin.qq.com/s?__biz=MzIxODM1NjEwOA==&mid=2247483713&idx=1&sn=cea4480e5a303585740618d23006356b&scene=0#wechat_redirect
 * <p/>
 * Created by YJH on 2016/6/5.
 */
public class CouponDisplayActivity extends BaseActivity {

    private CardView cardView_Demo;
    private ShareView mShareView; //分享视图

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
                //ToastUtil.showToast(CouponDisplayActivity.this,"This is a CardView ···");
                mShareView = new ShareView(CouponDisplayActivity.this);
                mShareView.show();
            }
        });
    }

    @Override
    public void initData() {
        /** Android 项目中图片压缩看我的blog就够了 图片处理 http://blog.csdn.net/chivalrousman/article/details/49743361*/
    }
}
