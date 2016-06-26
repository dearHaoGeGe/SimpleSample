package com.my.simplesampletest.marqueeview;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义跑马灯效果
 *
 * Created by YJH on 2016/6/26.
 */
public class MarqueeViewActivity extends BaseActivity{

    private MarqueeView marqueeView1_MarqueeViewAct;
    private MarqueeView marqueeView2_MarqueeViewAct;
    private MarqueeView marqueeView3_MarqueeViewAct;
    private MarqueeView marqueeView4_MarqueeViewAct;
    private MarqueeView marqueeView5_MarqueeViewAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee_view);

        initView();
        initData();
    }

    @Override
    public void initView() {
        marqueeView1_MarqueeViewAct= (MarqueeView) findViewById(R.id.marqueeView1_MarqueeViewAct);
        marqueeView2_MarqueeViewAct= (MarqueeView) findViewById(R.id.marqueeView2_MarqueeViewAct);
        marqueeView3_MarqueeViewAct= (MarqueeView) findViewById(R.id.marqueeView3_MarqueeViewAct);
        marqueeView4_MarqueeViewAct= (MarqueeView) findViewById(R.id.marqueeView4_MarqueeViewAct);
        marqueeView5_MarqueeViewAct= (MarqueeView) findViewById(R.id.marqueeView5_MarqueeViewAct);

        marqueeView1_MarqueeViewAct.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(MarqueeViewActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        marqueeView3_MarqueeViewAct.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(MarqueeViewActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        List<String> info =new ArrayList<>();
        info.add("1、这是第一条广告");
        info.add("2、dear浩哥哥");
        info.add("3、Github");
        info.add("4、SimpleDemo");
        info.add("5、这个第五条广告");
        info.add("6、最后一条广告");
        marqueeView1_MarqueeViewAct.startWithList(info);

        marqueeView2_MarqueeViewAct.startWithText("心中有阳光，脚底有力量！");
        marqueeView3_MarqueeViewAct.startWithText("心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！");
        marqueeView4_MarqueeViewAct.startWithText("心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！");
        marqueeView5_MarqueeViewAct.startWithText("心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！");
    }
}
