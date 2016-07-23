package com.my.simplesampletest.girdview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学习使用GirdView
 *
 * Created by YJH on 2016/7/23.
 */
public class GridViewActivity extends BaseActivity implements View.OnClickListener {

    private GridView gv_GridViewAct;
    private Button btn_Add_GridViewAct,btn_Edit_GridViewAct;
    private GVBaseAdapter adapter;
    private List<GVItemEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        initView();
        initData();
    }

    @Override
    public void initView() {
        gv_GridViewAct= (GridView) findViewById(R.id.gv_GridViewAct);
        btn_Add_GridViewAct= (Button) findViewById(R.id.btn_Add_GridViewAct);
        btn_Edit_GridViewAct= (Button) findViewById(R.id.btn_Edit_GridViewAct);

        btn_Add_GridViewAct.setOnClickListener(this);
        btn_Edit_GridViewAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        data=new ArrayList<>();
        addData(data);

        adapter=new GVBaseAdapter(data,this,false);
        gv_GridViewAct.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Add_GridViewAct:
                break;

            case R.id.btn_Edit_GridViewAct:
                if (adapter.isShowDelEnable()){
                    adapter.setShowDelEnable(false);
                    btn_Edit_GridViewAct.setText("编辑");
                }else {
                    adapter.setShowDelEnable(true);
                    btn_Edit_GridViewAct.setText("取消");
                }
                break;
        }
    }

    /**
     * 添加假数据
     *
     * @param data
     */
    private void addData(List<GVItemEntity> data){
        data.add(new GVItemEntity("通讯录"));
        data.add(new GVItemEntity("日历"));
        data.add(new GVItemEntity("照相机"));
        data.add(new GVItemEntity("时钟"));
        data.add(new GVItemEntity("游戏"));
        data.add(new GVItemEntity("短信"));
        data.add(new GVItemEntity("铃声"));
        data.add(new GVItemEntity("设置"));
        data.add(new GVItemEntity("语音"));
        data.add(new GVItemEntity("天气"));
        data.add(new GVItemEntity("浏览器"));
        data.add(new GVItemEntity("视频"));
    }
}
