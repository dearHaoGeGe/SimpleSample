package com.my.simplesampletest.lv_diff_type;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * listview分不同的type
 * <p/>
 * Created by YJH on 2016/10/10.
 */
public class LvDifferentTypeAct extends BaseActivity {

    private ListView lv_different_type_Act;
    private LvDifferentTypeAdapter adapter;
    private List<DiffTypeBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv_difference_type);

        initView();
        initData();
    }

    @Override
    public void initView() {
        lv_different_type_Act = (ListView) findViewById(R.id.lv_different_type_Act);
    }

    @Override
    public void initData() {
        addData();
        adapter = new LvDifferentTypeAdapter(this, data);
        lv_different_type_Act.setAdapter(adapter);
    }

    /**
     * 添加数据
     */
    private void addData() {
        data = new ArrayList<>();
        DiffTypeBean bean1 = new DiffTypeBean(3, null, null, new TypeThree(BitmapFactory.decodeResource(getResources(), R.mipmap.hj_share_friend), "第一张图片"));
        DiffTypeBean bean2 = new DiffTypeBean(1, new TypeOne(BitmapFactory.decodeResource(getResources(), R.mipmap.github), "购物优惠券", "0003", "2016-6-30"), null, null);
        DiffTypeBean bean3 = new DiffTypeBean(2, null, new TypeTwo(BitmapFactory.decodeResource(getResources(), R.mipmap.btn_posts_camera_l), "第一个GridView"), null);
        DiffTypeBean bean4 = new DiffTypeBean(3, null, null, new TypeThree(BitmapFactory.decodeResource(getResources(), R.mipmap.hj_share_weibo_sel), "type=3第二张图片"));
        DiffTypeBean bean5 = new DiffTypeBean(3, null, null, new TypeThree(BitmapFactory.decodeResource(getResources(), R.mipmap.hj_share_qzone), "空间分享"));
        DiffTypeBean bean6 = new DiffTypeBean(2, null, new TypeTwo(BitmapFactory.decodeResource(getResources(), R.mipmap.albumset_selected), "彭于晏"), null);
        DiffTypeBean bean7 = new DiffTypeBean(1, new TypeOne(BitmapFactory.decodeResource(getResources(), R.mipmap.github), "购物优惠券_2", "0056", "2016-9-10"), null, null);
        DiffTypeBean bean8 = new DiffTypeBean(2, null, new TypeTwo(BitmapFactory.decodeResource(getResources(), R.mipmap.aa), "审判天使"), null);


        data.add(bean1);
        data.add(bean2);
        data.add(bean3);
        data.add(bean4);
        data.add(bean5);
        data.add(bean6);
        data.add(bean7);
        data.add(bean8);
    }
}
