package com.my.simplesampletest.lvupdatesingledata;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView更新单条数据，刷新，禁止截屏
 * <p/>
 * Created by YJH on 2016/6/15.
 */
public class LVUpdateSingleAct extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv_LVUpdateSingleAct;
    private LVUpdateSingleAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv_update_single);

        //此方法是禁止截屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);

        initView();
        initData();
    }

    @Override
    public void initView() {
        lv_LVUpdateSingleAct = (ListView) findViewById(R.id.lv_LVUpdateSingleAct);
        lv_LVUpdateSingleAct.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        data = new ArrayList<>();
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        data.add("596868");
        adapter = new LVUpdateSingleAdapter(this, data);
        lv_LVUpdateSingleAct.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(LVUpdateSingleAct.this, "" + position, Toast.LENGTH_SHORT).show();
        data.set(position,"数据改变了");
        adapter.updataView(position,lv_LVUpdateSingleAct);
    }

}
