package com.my.simplesampletest.lv_nesting_lv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.lv_nesting_lv.linearlistview.LinearListView;

import java.util.ArrayList;
import java.util.List;

/**
 * LinearListView嵌套LinearListView，ListView嵌套滑动太卡
 * 参考：
 * <a href="https://github.com/frankiesardo/LinearListView.git"/>
 */
public class NestOneActivity extends AppCompatActivity {

    private LinearListView linearListView;
    private NestOneAdapter adapter;
    private List<List<OrderEntity>> list;
    private List<OrderEntity> l1, l2, l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_one);

        linearListView = (LinearListView) findViewById(R.id.linearListView);
        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        l3 = new ArrayList<>();
        list = new ArrayList<>();
        falseDate();
        list.add(l1);
        list.add(l2);
        list.add(l3);

        adapter = new NestOneAdapter(this, list);
        linearListView.setAdapter(adapter);
    }

    /**
     * 假数据
     */
    private void falseDate() {
        l1.add(new OrderEntity("蒙牛纯牛奶300ml", "本品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));
        l1.add(new OrderEntity("蒙牛早餐核桃奶250ml", "本品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));
        l1.add(new OrderEntity("蒙牛低脂高钙牛奶240ml", "本品", 3.19f, "T001", 2.99f, 12, 35.88f, "1006-1"));
        l1.add(new OrderEntity("蒙牛纯牛奶300ml", "本品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));
        l1.add(new OrderEntity("蒙牛早餐核桃奶250ml", "本品", 3.19f, "T001", 2.99f, 12, 35.88f, "1001-1"));
        l1.add(new OrderEntity("蒙牛低脂高钙牛奶240ml", "本品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));
        l1.add(new OrderEntity("蒙牛纯牛奶300ml", "本品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));

        l2.add(new OrderEntity("蒙牛早餐核桃奶250ml", "赠品", 3.19f, "T001", 2.99f, 12, 35.88f, "1001-1"));
        l2.add(new OrderEntity("蒙牛低脂高钙牛奶240ml", "赠品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));
        l2.add(new OrderEntity("蒙牛纯牛奶300ml", "赠品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));

        l3.add(new OrderEntity("蒙牛早餐核桃奶250ml", "单品", 3.19f, "T001", 2.99f, 12, 35.88f, "1001-1"));
        l3.add(new OrderEntity("蒙牛低脂高钙牛奶240ml", "单品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));
        l3.add(new OrderEntity("蒙牛纯牛奶300ml", "单品", 3.19f, "T001", 2.99f, 12, 35.88f, "1002-1"));
    }
}
