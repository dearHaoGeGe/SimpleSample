package com.my.simplesampletest.imageswitchview;

import android.os.Bundle;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.orhanobut.logger.Logger;

/**
 * 3D图片轮播
 *
 * Created by YJH on 2016/6/16.
 */
public class Image3DSwitchViewAct extends BaseActivity{

    private Image3DSwitchView imageSwitchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switch_view);

        initView();
        initData();
    }

    @Override
    public void initView() {
        imageSwitchView = (Image3DSwitchView) findViewById(R.id.image_switch_view);

        imageSwitchView.setOnImageSwitchListener(new Image3DSwitchView.OnImageSwitchListener() {
            @Override
            public void onImageSwitch(int currentImage) {
                Logger.d("TAG", "current image is " + currentImage);
            }
        });
    }

    @Override
    public void initData() {
        imageSwitchView.setCurrentImage(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageSwitchView.clear();
    }
}
