package com.my.simplesampletest.blurred_view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.blurred_view.blurredview.BlurredView;

/**
 * Created by YJH on 2016/8/3.
 */
public class WeatherActivity extends BaseActivity {

    private BlurredView blurredView_WeatherAct;
    private RecyclerView recyclerView_WeatherAct;
    private int mScrollerY;
    private int mAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //继承AppCompatActivity(V7包下的Activity)之后要用这个方法去掉title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_weather);

        initView();
        initData();
    }

    @Override
    public void initView() {
        blurredView_WeatherAct = (BlurredView) findViewById(R.id.blurredView_WeatherAct);
        recyclerView_WeatherAct = (RecyclerView) findViewById(R.id.recyclerView_WeatherAct);

        recyclerView_WeatherAct.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_WeatherAct.setAdapter(new RecyclerViewAdapter(this));
    }

    @Override
    public void initData() {
        recyclerView_WeatherAct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mScrollerY += dy;
                if (Math.abs(mScrollerY) > 1000) {
                    blurredView_WeatherAct.setBlurredLevel(100);
                    mAlpha = 100;
                } else {
                    blurredView_WeatherAct.setBlurredTop(mScrollerY / 10);
                    mAlpha = Math.abs(mScrollerY) / 10;
                }
                blurredView_WeatherAct.setBlurredLevel(mAlpha);
            }
        });
    }
}
