package com.my.simplesampletest.snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 学习使用Snackbar
 *
 * 参考：http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261804&idx=1&sn=384dd2a1b45f8651f084dc4e6942d8ac&scene=0#wechat_redirect
 *
 * 一下是自动轮播图
 * http://mp.weixin.qq.com/s?__biz=MzAwOTUyNzI3Ng==&mid=2652071391&idx=1&sn=2f67c658ccb2f1efaddff4f6c4225844&scene=0#wechat_redirect
 *
 * https://github.com/imbryk/LoopingViewPager
 * https://github.com/wanliyang1990/AdViewPager
 * https://github.com/Trinea/android-auto-scroll-view-pager
 * https://github.com/Jude95/RollViewPager
 * https://github.com/FlyRecker/FlyBanner
 *
 * Created by YJH on 2016/7/12.
 */
public class SnackbarActivity extends BaseActivity implements View.OnClickListener {

    private Button btn1_SnackbarAct, but2_SnackbarAct, but3_SnackbarAct, but4_SnackbarAct, but5_SnackbarAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        initView();
        initData();
    }

    @Override
    public void initView() {
        setTitle("Snackbar");

        btn1_SnackbarAct = (Button) findViewById(R.id.btn1_SnackbarAct);
        but2_SnackbarAct = (Button) findViewById(R.id.but2_SnackbarAct);
        but3_SnackbarAct = (Button) findViewById(R.id.but3_SnackbarAct);
        but4_SnackbarAct = (Button) findViewById(R.id.but4_SnackbarAct);
        but5_SnackbarAct = (Button) findViewById(R.id.but5_SnackbarAct);

        btn1_SnackbarAct.setOnClickListener(this);
        but2_SnackbarAct.setOnClickListener(this);
        but3_SnackbarAct.setOnClickListener(this);
        but4_SnackbarAct.setOnClickListener(this);
        but5_SnackbarAct.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1_SnackbarAct:
                Snackbar.make(btn1_SnackbarAct, "这是没有点击事件的Snackbar", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.but2_SnackbarAct:
                Snackbar.make(but2_SnackbarAct, "这是有点击事件的Snackbar", Snackbar.LENGTH_INDEFINITE)
                        .setActionTextColor(Color.parseColor("#AB82FF"))    //设置点击按钮的颜色
                        .setAction("点我", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SnackbarActivity.this, "点击Snackbar了~", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;

            case R.id.but3_SnackbarAct:
                Snackbar.make(but3_SnackbarAct, "show和dismiss的回调", Snackbar.LENGTH_INDEFINITE)
                        .setActionTextColor(Color.parseColor("#ff33b5e5"))
                        .setCallback(new Snackbar.Callback() {

                            /**
                             * 显示的时候回调
                             * @param snackbar
                             */
                            @Override
                            public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                                Toast.makeText(SnackbarActivity.this, "onShown回调", Toast.LENGTH_SHORT).show();
                            }

                            /**
                             * 消失的时候回调
                             * @param snackbar
                             * @param event
                             */
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                                Toast.makeText(SnackbarActivity.this, "onDismissed回调", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setAction("点我", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SnackbarActivity.this, "点击Snackbar了~", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;

            case R.id.but4_SnackbarAct:
                Snackbar bar = Snackbar.make(but4_SnackbarAct, "自定义Snackbar背景色", Snackbar.LENGTH_LONG);
                v = bar.getView();
                v.setBackgroundColor(Color.BLUE);
                bar.show();
                break;

            case R.id.but5_SnackbarAct:
                Snackbar mSnackbar = Snackbar.make(but5_SnackbarAct, "it is Snackbar", Snackbar.LENGTH_SHORT);
                v = mSnackbar.getView();
                ViewGroup.LayoutParams vl = v.getLayoutParams();
                CoordinatorLayout.LayoutParams cl = new CoordinatorLayout.LayoutParams(vl.width, vl.height);
                //设置字体为红色
                ((TextView) v.findViewById(R.id.snackbar_text)).setTextColor(Color.RED);
                //设置显示位置居中
                cl.gravity = Gravity.CENTER;
                v.setLayoutParams(cl);
                //设置背景色为绿色
                v.setBackgroundColor(Color.GREEN);
                //自定义动画
                //v.setAnimation();
                //设置icon
                ImageView iconImage = new ImageView(this);
                iconImage.setImageResource(R.mipmap.ic_launcher);
                //icon插入布局
                Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) v;
                snackbarLayout.addView(iconImage, 0);
                //设置按钮为蓝色
                mSnackbar.setActionTextColor(Color.BLUE).setAction("点我", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                break;
        }
    }
}
