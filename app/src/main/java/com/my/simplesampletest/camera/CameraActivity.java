package com.my.simplesampletest.camera;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 自定义相机
 * 参考：http://blog.csdn.net/qq_17250009/article/details/52795530
 * <p>
 * Created by YJH on 2017/2/19 18:46.
 */
public class CameraActivity extends BaseActivity implements RectOnCamera.IAutoFocus, View.OnClickListener {

    private CameraSurfaceView mCameraSurfaceView;
    private RectOnCamera mRectOnCamera;
    private Button btn_takePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initView();
        initData();
    }

    @Override
    public void initView() {
        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.cameraSurfaceView);
        mRectOnCamera = (RectOnCamera) findViewById(R.id.rectOnCamera);
        btn_takePic = (Button) findViewById(R.id.btn_takePic);

        btn_takePic.setOnClickListener(this);
        mRectOnCamera.setIAutoFocus(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void autoFocus() {
        mCameraSurfaceView.setAutoFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_takePic:
                mCameraSurfaceView.takePicture();
                break;
        }
    }
}
