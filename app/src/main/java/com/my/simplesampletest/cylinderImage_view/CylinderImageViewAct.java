package com.my.simplesampletest.cylinderImage_view;

import android.os.Bundle;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 循环显示超长图片
 *
 * Created by YJH on 2016/7/12.
 */
public class CylinderImageViewAct extends BaseActivity {

    private CylinderImageView civ_CylinderImageViewAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cylinder_image_view);

        initView();
        initData();
    }

    @Override
    public void initView() {
        civ_CylinderImageViewAct = (CylinderImageView) findViewById(R.id.civ_CylinderImageViewAct);
    }

    @Override
    public void initData() {
        civ_CylinderImageViewAct.initVideoView(R.mipmap.android_m_hero);
    }
}
