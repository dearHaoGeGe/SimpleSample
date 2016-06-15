package com.my.simplesampletest.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.my.simplesampletest.activitypostobject.PostActivity;
import com.my.simplesampletest.broadcast.BroadCastActivity;
import com.my.simplesampletest.coupondisplayview.CouponDisplayActivity;
import com.my.simplesampletest.autohideime.AutoHideIMEActivity;
import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.eventbus.EventBusActivity;
import com.my.simplesampletest.imagecache.DiskLruCacheAct;
import com.my.simplesampletest.jsonobjecttostring.JSONObjectActivity;
import com.my.simplesampletest.logger.LoggerActivity;
import com.my.simplesampletest.lvupdatesingledata.LVUpdateSingleAct;
import com.my.simplesampletest.picasso.PicassoActivity;
import com.my.simplesampletest.picasso.PicassoDemoActivity;
import com.my.simplesampletest.tablayout.TLTrunkActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity
 */
public class MainActivity extends BaseActivity implements MainActAdapter.MyItemOnClickListener, MainActAdapter.MyItemOnLongClickListener {

    private RecyclerView rcView_main;
    private List<String> data;
    private MainActAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    @Override
    public void initView() {
        rcView_main = (RecyclerView) findViewById(R.id.rcView_main);
    }

    @Override
    public void initData() {
        //需要指定recycler的布局管理
        rcView_main.setLayoutManager(new LinearLayoutManager(this));
        //添加分割线
        rcView_main.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        addDataToList();

        adapter = new MainActAdapter(this, data);
        rcView_main.setAdapter(adapter);
        adapter.setOnClickListener(this);
        adapter.setOnLongClickListener(this);
    }

    /**
     * 往List中添加数据
     */
    private void addDataToList() {
        data = new ArrayList<>();
        data.add("EventBus传递事件");
        data.add("测试string装换成JSONObject");
        data.add("Android快速实现点击任意位置收缩键盘");
        data.add("android自定义控件—边缘凹凸的View");
        data.add("activity之间传递对象");
        data.add("TabLayout配合ViewPager可以滑动");
        data.add("广播BroadCast");
        data.add("PicassoDemo");
        data.add("Picasso的简单使用");
        data.add("Logger工具");
        data.add("ListView更新单条数据，刷新");
        data.add("Android DiskLruCache完全解析，硬盘缓存的最佳方案");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
    }

    @Override
    public void onItemClick(View view, int position) {
        clickJumpActivity(view, position);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        clickLongJumpActivity(view, position);
    }

    /**
     * 点击跳转
     *
     * @param view
     * @param position
     */
    private void clickJumpActivity(View view, int position) {
        switch (position) {
            case 0: //EventBus传递事件
                startActivity(new Intent(this, EventBusActivity.class));
                break;

            case 1: //测试string装换成JSONObject
                startActivity(new Intent(this, JSONObjectActivity.class));
                break;

            case 2: //Android快速实现点击任意位置收缩键盘
                startActivity(new Intent(this, AutoHideIMEActivity.class));
                break;

            case 3: //android自定义控件—边缘凹凸的View
                startActivity(new Intent(this, CouponDisplayActivity.class));
                break;

            case 4: //activity之间传递对象
                startActivity(new Intent(this, PostActivity.class));
                break;

            case 5: //TabLayout配合ViewPager可以滑动
                startActivity(new Intent(this, TLTrunkActivity.class));
                break;

            case 6: //广播BroadCast
                startActivity(new Intent(this, BroadCastActivity.class));
                break;

            case 7: //Picasso
                startActivity(new Intent(this, PicassoDemoActivity.class));
                break;

            case 8: //Picasso的简单使用
                startActivity(new Intent(this, PicassoActivity.class));
                break;

            case 9: //Logger工具
                startActivity(new Intent(this, LoggerActivity.class));
                break;

            case 10://ListView更新单条数据，刷新
                startActivity(new Intent(this,LVUpdateSingleAct.class));
                break;

            case 11://Android DiskLruCache完全解析，硬盘缓存的最佳方案
                startActivity(new Intent(this, DiskLruCacheAct.class));
                break;
        }
    }

    /**
     * 长按跳转
     *
     * @param view
     * @param position
     */
    private void clickLongJumpActivity(View view, int position) {
        switch (position) {
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;
        }
    }
}
