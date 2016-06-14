package com.my.simplesampletest.picasso.universal_image_loader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * Created by YJH on 2016/6/14.
 */
public class UniversalImgLoaderAct extends BaseActivity implements View.OnClickListener {

    private ImageView iv_UniversalImgLoaderAct;
    private Button btn_UniversalImgLoaderAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.layout.activity_universal_img_loader);

        initView();
        initData();
    }

    @Override
    public void initView() {
        iv_UniversalImgLoaderAct= (ImageView) findViewById(R.id.iv_UniversalImgLoaderAct);
        btn_UniversalImgLoaderAct= (Button) findViewById(R.id.btn_UniversalImgLoaderAct);

        btn_UniversalImgLoaderAct.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_UniversalImgLoaderAct:

                break;
        }
    }

    private void loadImg(){

    }
}
