package com.my.simplesampletest.coupondisplayview;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;

/**
 * 分享的弹窗自定义View
 *
 * ShareView参考：http://mp.weixin.qq.com/s?__biz=MzIxODM1NjEwOA==&mid=2247483713&idx=1&sn=cea4480e5a303585740618d23006356b&scene=0#wechat_redirect
 *
 * Created by YJH on 2016/8/7.
 */
public class ShareView {

    private ImageView iv_share_friend;
    private ImageView iv_share_wx;
    private ImageView iv_share_weibo;
    private ImageView iv_share_qq;
    private ImageView iv_share_qqzone;
    private TextView tv_share_cancel;

    private Activity mActivity;
    private View fullMaskView; // 蒙层视图
    private View contentLayout; // 分享视图

    private int panelHeight = 0; // 分享视图高度
    private int navigationBarHeight = 0; // 导航栏高度

    public ShareView(Activity activity) {
        this.mActivity = activity;
        initShareView(activity);
    }

    private void initShareView(Activity activity) {
        fullMaskView = View.inflate(activity, R.layout.ui_view_full_mask_layout, null);
        contentLayout = LayoutInflater.from(activity).inflate(R.layout.ui_share, null);
        //ButterKnife.bind(this, contentLayout);

        iv_share_friend= (ImageView) contentLayout.findViewById(R.id.iv_share_friend);
        iv_share_wx= (ImageView) contentLayout.findViewById(R.id.iv_share_wx);
        iv_share_weibo= (ImageView) contentLayout.findViewById(R.id.iv_share_weibo);
        iv_share_qq= (ImageView) contentLayout.findViewById(R.id.iv_share_qq);
        iv_share_qqzone= (ImageView) contentLayout.findViewById(R.id.iv_share_qqzone);
        tv_share_cancel= (TextView) contentLayout.findViewById(R.id.tv_share_cancel);

        attachView();
        initListener();
    }

    // 将该View添加到根布局
    private void attachView() {
        ((ViewGroup) mActivity.getWindow().getDecorView()).addView(fullMaskView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        ((ViewGroup) mActivity.getWindow().getDecorView()).addView(contentLayout, params);
        fullMaskView.setVisibility(View.GONE);
        contentLayout.setVisibility(View.GONE);

        if (NavigationBarUtil.hasNavigationBar(mActivity)) {
            navigationBarHeight = NavigationBarUtil.getNavigationBarHeight(mActivity);
        }
    }

    // 动画显示布局
    public void show() {
        fullMaskView.setVisibility(View.VISIBLE);
        contentLayout.setVisibility(View.VISIBLE);
        contentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                contentLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                ViewGroup parent = (ViewGroup) contentLayout.getParent();
                panelHeight = parent.getHeight() - contentLayout.getTop();
                ObjectAnimator.ofFloat(contentLayout, "translationY", panelHeight, -navigationBarHeight).setDuration(200).start();
            }
        });
    }

    // 动画隐藏布局
    public void hide() {
        fullMaskView.setVisibility(View.GONE);
        ObjectAnimator.ofFloat(contentLayout, "translationY", -navigationBarHeight, panelHeight).setDuration(200).start();
    }

    private void initListener() {
        fullMaskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        tv_share_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        iv_share_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "朋友圈", Toast.LENGTH_SHORT).show();
                hide();
            }
        });

        iv_share_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "微信", Toast.LENGTH_SHORT).show();
                hide();
            }
        });

        iv_share_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "微博", Toast.LENGTH_SHORT).show();
                hide();
            }
        });

        iv_share_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "QQ", Toast.LENGTH_SHORT).show();
                hide();
            }
        });

        iv_share_qqzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "QQ空间", Toast.LENGTH_SHORT).show();
                hide();
            }
        });
    }

}
