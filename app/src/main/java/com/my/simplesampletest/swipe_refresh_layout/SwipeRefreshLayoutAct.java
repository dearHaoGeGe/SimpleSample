package com.my.simplesampletest.swipe_refresh_layout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/7/11.
 */
public class SwipeRefreshLayoutAct extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView lv_SwipeRefreshLayoutAct;
    private List<String> list=new ArrayList<>();
    private MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        setTitle("SwipeRefreshLayout");

        initView();
        initData();
    }

    @Override
    public void initView() {
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        lv_SwipeRefreshLayoutAct= (ListView) findViewById(R.id.lv_SwipeRefreshLayoutAct);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
       }

    @Override
    public void initData() {
        adapter= new MyArrayAdapter(this,android.R.layout.simple_list_item_1,getData());
        lv_SwipeRefreshLayoutAct.setAdapter(adapter);
    }

    private List<String> getData() {
        list.add("Hello");
        list.add("This is dearHaoGeGe");
        list.add("An Android Developer");
        list.add("Love Open Source");
        list.add("My GitHub: https://github.com/dearHaoGeGe");
        list.add("Quote From:https://github.com/stormzhang/SwipeRefreshLayoutDemo/");
        return list;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                adapter.add("这是下拉加载的新数据");
            }
        },3000);
    }


    /**
     * 适配器
     */
    private class MyArrayAdapter extends ArrayAdapter{
        public MyArrayAdapter(Context context, int resource,List objects) {
            super(context, resource, objects);
        }
    }
}
