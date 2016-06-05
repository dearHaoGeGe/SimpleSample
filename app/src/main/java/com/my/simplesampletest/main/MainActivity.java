package com.my.simplesampletest.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.my.simplesampletest.coupondisplayview.CouponDisplayActivity;
import com.my.simplesampletest.autohideime.AutoHideIMEActivity;
import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.eventbus.EventBusActivity;
import com.my.simplesampletest.jsonobjecttostring.JSONObjectActivity;

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
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(MainActivity.this, "点击事件！"+position, Toast.LENGTH_SHORT).show();
        clickJumpActivity(view, position);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        //Toast.makeText(MainActivity.this, "长按事件！"+position, Toast.LENGTH_SHORT).show();
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
            case 0:
                startActivity(new Intent(this, EventBusActivity.class));
                break;

            case 1:
                startActivity(new Intent(this, JSONObjectActivity.class));
                break;

            case 2:
                startActivity(new Intent(this, AutoHideIMEActivity.class));
                break;

            case 3:
                startActivity(new Intent(this, CouponDisplayActivity.class));
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
