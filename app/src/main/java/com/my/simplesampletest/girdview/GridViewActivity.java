package com.my.simplesampletest.girdview;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学习使用GirdView
 * <p/>
 * Created by YJH on 2016/7/23.
 */
public class GridViewActivity extends BaseActivity implements View.OnClickListener {

    private GridView gv_GridViewAct;
    private Button btn_Add_GridViewAct, btn_Edit_GridViewAct;
    private GVBaseAdapter adapter;
    private List<GVItemEntity> data;
    private static final int GETIMG_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        initView();
        initData();
    }

    @Override
    public void initView() {
        gv_GridViewAct = (GridView) findViewById(R.id.gv_GridViewAct);
        btn_Add_GridViewAct = (Button) findViewById(R.id.btn_Add_GridViewAct);
        btn_Edit_GridViewAct = (Button) findViewById(R.id.btn_Edit_GridViewAct);

        btn_Add_GridViewAct.setOnClickListener(this);
        btn_Edit_GridViewAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        data = new ArrayList<>();
        addData(data);

        adapter = new GVBaseAdapter(data, this, false);
        gv_GridViewAct.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Add_GridViewAct:  //打开本地相册
                openLocalGallery();
                break;

            case R.id.btn_Edit_GridViewAct:
                if (adapter.isShowDelEnable()) {
                    adapter.setShowDelEnable(false);
                    btn_Edit_GridViewAct.setText("编辑");
                } else {
                    adapter.setShowDelEnable(true);
                    btn_Edit_GridViewAct.setText("取消");
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GETIMG_OK) {
            Uri uri;
            //当跳到相册的之后不选择照片直接返回，这个时候data.getData()一直报空指针异常
            //判断为null==data.getData()没有用，所以就把这个异常给抛出了
            try {
                uri = data.getData();
            } catch (Exception e) {
                e.printStackTrace();
                Logger.d("学习使用GirdView", "没有选取照片");
                return;
            }

            ContentResolver cr = getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                GVItemEntity entity = new GVItemEntity("本地图片");
                entity.setLocalPic(bitmap);
                this.adapter.addData(entity);
                Toast.makeText(GridViewActivity.this, "添加照片成功!", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(GridViewActivity.this, "添加照片异常!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 打开本地相册的方法
     */
    private void openLocalGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, GETIMG_OK);
    }

    /**
     * 添加假数据
     *
     * @param data
     */
    private void addData(List<GVItemEntity> data) {
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
