package com.my.simplesampletest.blurred_view;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.blurred_view.blurredview.BlurredView;

/**
 * 手动设置模糊度
 *
 * 加载图片框架比较:http://blog.csdn.net/richiezhu/article/details/46968569
 *
 * Created by YJH on 2016/8/3.
 */
public class BlurredViewBasicAct extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView tv_BlurredViewBasicAct;
    private SeekBar seekBar_BlurredViewBasicAct;
    private BlurredView blurredView_BlurredViewBasicAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurred_view_basic);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
        initData();
    }

    @Override
    public void initView() {
        tv_BlurredViewBasicAct = (TextView) findViewById(R.id.tv_BlurredViewBasicAct);
        seekBar_BlurredViewBasicAct = (SeekBar) findViewById(R.id.seekBar_BlurredViewBasicAct);
        blurredView_BlurredViewBasicAct = (BlurredView) findViewById(R.id.blurredView_BlurredViewBasicAct);
    }

    @Override
    public void initData() {
        /** 可以在代码中使用setBlurredImg()方法指定需要模糊的图片 */
        blurredView_BlurredViewBasicAct.setBlurredImg(getResources().getDrawable(R.mipmap.dayu));

        /** 设置完成后需要调用showBlurredView方法显示要模糊的图片 */
        blurredView_BlurredViewBasicAct.showBlurredView();

        seekBar_BlurredViewBasicAct.setMax(100);
        seekBar_BlurredViewBasicAct.setOnSeekBarChangeListener(this);
    }


    //*********************** setOnSeekBarChangeListener 开始 ****************************//
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tv_BlurredViewBasicAct.setText(String.valueOf(progress));   //动态改变当前选中的值
        blurredView_BlurredViewBasicAct.setBlurredLevel(progress);  //设置模糊度
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    //*********************** setOnSeekBarChangeListener 结束 ****************************//
}
