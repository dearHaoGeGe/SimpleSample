package com.my.simplesampletest.picasso;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.squareup.picasso.Picasso;

/**
 * 图片下载的步骤：
 * 1、使用异步任务或者 handler+thread 获取图片资源
 * 2、使用BitmapFactory 对图片进行解码
 * 3、显示图片
 *
 * Created by YJH on 2016/6/12.
 */
public class PicassoDemoActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_loadPic_PicassoDemoAct;
    private ImageView iv_PicassoDemoAct;
    private String iv_url="http://image.gamersky.com/error/404.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_picasso);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_loadPic_PicassoDemoAct= (Button) findViewById(R.id.btn_loadPic_PicassoDemoAct);
        iv_PicassoDemoAct= (ImageView) findViewById(R.id.iv_PicassoDemoAct);

        btn_loadPic_PicassoDemoAct.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_loadPic_PicassoDemoAct:
                Picasso.with(this).load(iv_url).fit().into(iv_PicassoDemoAct);
                break;
        }
    }
}
