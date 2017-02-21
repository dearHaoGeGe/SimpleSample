package com.my.simplesampletest.segment_view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * RadioGroup实现类似ios的分段选择(UISegmentedControl)控件
 * <p>
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/0512/1615.html
 * https://github.com/Kaopiz/android-segmented-control
 * <p>
 * Android开发的精华包括工具、中文API
 * http://www.androiddevtools.cn/
 * <p>
 * Created by YJH on 2016/7/9.
 */
public class SegmentViewActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, OnItemPagerClickListener {

    /**
     * 分段式按钮
     */
    private TextView approveAgreeTV, approveDisagreeTV, defaultTV;

    //******************* 自动轮播图 ******************
    private ViewPager vp_banner;
    private LinearLayout ll_point_group;
    private int mImages[] = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image5, R.mipmap.image6, R.mipmap.image7};
    private List<ImageView> mList;
    private Handler mHandler;
    private int lastPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_iew);

        initView();
        initData();
    }

    @Override
    public void initView() {
        approveAgreeTV = (TextView) findViewById(R.id.approveAgreeTV);
        approveDisagreeTV = (TextView) findViewById(R.id.approveDisagreeTV);
        defaultTV = (TextView) findViewById(R.id.defaultTV);

        approveAgreeTV.setOnClickListener(this);
        approveDisagreeTV.setOnClickListener(this);
        defaultTV.setOnClickListener(this);

        initBanner();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.approveAgreeTV:
                initButtonState();
                approveAgreeTV.setBackgroundResource(R.drawable.button_left_press);
                approveAgreeTV.setTextColor(Color.parseColor("#ffffff"));

                Snackbar.make(approveAgreeTV, "同意", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.approveDisagreeTV:
                initButtonState();
                approveDisagreeTV.setBackgroundResource(R.drawable.button_right_press);
                approveDisagreeTV.setTextColor(Color.parseColor("#ffffff"));

                Snackbar.make(approveAgreeTV, "不同意", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.defaultTV:
                initButtonState();
                defaultTV.setBackgroundResource(R.drawable.button_default_press);
                defaultTV.setTextColor(Color.parseColor("#ffffff"));
                Snackbar.make(approveAgreeTV, "默认", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 初始化默认的按钮的状态
     */
    private void initButtonState() {
        approveAgreeTV.setBackgroundResource(R.drawable.button_left_release);
        approveDisagreeTV.setBackgroundResource(R.drawable.button_right_release);
        defaultTV.setBackgroundResource(R.drawable.button_default_release);

        approveAgreeTV.setTextColor(Color.parseColor("#6FD5FC"));
        approveDisagreeTV.setTextColor(Color.parseColor("#6FD5FC"));
        defaultTV.setTextColor(Color.parseColor("#6FD5FC"));
    }

    /**
     * 自动轮播图
     */
    private void initBanner() {
        vp_banner = (ViewPager) findViewById(R.id.vp_banner);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);

        mList = new ArrayList<>();
        for (int i = 0; i < mImages.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(mImages[i]);     //将图片设置到ImageView控件上
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mList.add(imageView);                       //将ImageView控件添加到集合

            //制作底部小圆点
            ImageView pointImage = new ImageView(this);
            pointImage.setImageResource(R.drawable.shape_point_selector);
            //设置小圆点的布局参数
            int pointSize = getResources().getDimensionPixelSize(R.dimen.dp_6);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pointSize, pointSize);

            if (i > 0) {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dp_6);
                params.bottomMargin=getResources().getDimensionPixelSize(R.dimen.dp_6);
                pointImage.setSelected(false);
            } else {
                pointImage.setSelected(true);
            }
            pointImage.setLayoutParams(params);
            //添加到容器里
            ll_point_group.addView(pointImage);
        }

        final MyAdapter adapter = new MyAdapter(mList);
        vp_banner.setAdapter(adapter);

        vp_banner.addOnPageChangeListener(this);
        adapter.setOnItemPagerClickListener(this);

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = vp_banner.getCurrentItem();

                if (currentPosition == adapter.getCount() - 1) {
                    // 最后一页
                    vp_banner.setCurrentItem(0);
                } else {
                    vp_banner.setCurrentItem(currentPosition + 1);
                }

                //一直给自己发消息
                mHandler.postDelayed(this,3000);
            }
        }, 3000);
    }

    //********************************** addOnPageChangeListener(this)开始 **************************************
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //页面被选中

        //修改position
        position = position % mList.size();
        //设置当前页面选中
        ll_point_group.getChildAt(position).setSelected(true);
        //设置前一页不选中
        ll_point_group.getChildAt(lastPosition).setSelected(false);
        //替换位置
        lastPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //********************************** addOnPageChangeListener(this)结束 **************************************

    /**
     * ViewPager中item的点击事件
     *
     * @param position 位置
     */
    @Override
    public void onClickListener(int position) {
        ToastUtil.showToast(this, "" + position);
    }


    /**
     * 自动轮播ViewPager的适配器
     */
    private class MyAdapter extends PagerAdapter {

        private OnItemPagerClickListener onItemPagerClickListener;
        private List<ImageView> data;

        public MyAdapter(List<ImageView> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            // 返回整数的最大值
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // return super.instantiateItem(container, position);
            // 修改position
            position = position % data.size();
            // 将图片控件添加到容器
            container.addView(data.get(position));

            data.get(position).setOnClickListener(new OnItemClickListener(position));

            // 返回
            return data.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        private class OnItemClickListener implements View.OnClickListener {

            private int position = -1;

            public OnItemClickListener(int position) {
                this.position = position;
            }

            @Override
            public void onClick(View v) {
                if (null != onItemPagerClickListener) {
                    onItemPagerClickListener.onClickListener(position);
                }
            }
        }


        public void setOnItemPagerClickListener(OnItemPagerClickListener onItemPagerClickListener) {
            this.onItemPagerClickListener = onItemPagerClickListener;
        }


    }
}
