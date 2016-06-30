package com.my.simplesampletest.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.my.simplesampletest.about.AboutActivity;
import com.my.simplesampletest.act_life_cycle.LifeCycleActivity;
import com.my.simplesampletest.activitypostobject.PostActivity;
import com.my.simplesampletest.broadcast.BroadCastActivity;
import com.my.simplesampletest.coupondisplayview.CouponDisplayActivity;
import com.my.simplesampletest.autohideime.AutoHideIMEActivity;
import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.eventbus.EventBusActivity;
import com.my.simplesampletest.imagecache.DiskLruCacheAct;
import com.my.simplesampletest.imageloader.ImageLoaderActivity;
import com.my.simplesampletest.imageswitchview.Image3DSwitchViewAct;
import com.my.simplesampletest.jsonobjecttostring.JSONObjectActivity;
import com.my.simplesampletest.logger.LoggerActivity;
import com.my.simplesampletest.lvupdatesingledata.LVUpdateSingleAct;
import com.my.simplesampletest.marqueeview.MarqueeView;
import com.my.simplesampletest.marqueeview.MarqueeViewActivity;
import com.my.simplesampletest.okhttp.OKHttpActivity;
import com.my.simplesampletest.ormlitedemo.ORMLiteActivity;
import com.my.simplesampletest.picasso.PicassoActivity;
import com.my.simplesampletest.picasso.PicassoDemoActivity;
import com.my.simplesampletest.read_config_properties.ConfigPropertiesAct;
import com.my.simplesampletest.tablayout.TLTrunkActivity;
import com.my.simplesampletest.zxing.QRCodeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity
 */
public class MainActivity extends BaseActivity implements MainActAdapter.MyItemOnClickListener, MainActAdapter.MyItemOnLongClickListener {

    private RecyclerView rcView_main;
    private List<String> data;
    private MainActAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    public void initView() {
        rcView_main = (RecyclerView) findViewById(R.id.rcView_main);
    }

    @Override
    public void initData() {
        //需要指定recycler的布局管理
        rcView_main.setLayoutManager(new LinearLayoutManager(this));
        //添加分割线
        rcView_main.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        addDataToList();

        adapter = new MainActAdapter(this, data);
        rcView_main.setAdapter(adapter);
        adapter.setOnClickListener(this);
        adapter.setOnLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_app:
                startActivity(new Intent(this, AboutActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 往List中添加数据
     */
    private void addDataToList() {
        data = new ArrayList<>();
        data.add("EventBus传递事件");
        data.add("测试string装换成JSONObject");
        data.add("Android快速实现点击任意位置收缩键盘");
        data.add("android自定义控件—边缘凹凸的View");
        data.add("activity之间传递对象");
        data.add("TabLayout配合ViewPager可以滑动");
        data.add("广播BroadCast");
        data.add("PicassoDemo");
        data.add("Picasso的简单使用");
        data.add("Logger工具");
        data.add("ListView更新单条数据，刷新");
        data.add("Android DiskLruCache完全解析，硬盘缓存的最佳方案");
        data.add("3D图片滚动");
        data.add("学习ORMLite数据库");
        data.add("Activity的生命周期");
        data.add("用zxing做的生成二维码，并且扫码");
        data.add("自定义跑马灯效果(广告)");
        data.add("学习使用universal-image-loader-1.9.5");
        data.add("读取assets文件夹下的config.properties文件");
        data.add("学习使用OKHttp");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
        data.add("5s1d6a165a1ds515c15d1c5d");
    }

    @Override
    public void onItemClick(View view, int position) {
        clickJumpActivity(view, position);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        clickLongJumpActivity(view, position);
    }

    /**
     * 点击跳转
     *
     * @param view
     * @param position
     */
    private void clickJumpActivity(View view, int position) {
        switch (position) {
            case 0: //EventBus传递事件
                startActivity(new Intent(this, EventBusActivity.class));
                break;

            case 1: //测试string装换成JSONObject
                startActivity(new Intent(this, JSONObjectActivity.class));
                break;

            case 2: //Android快速实现点击任意位置收缩键盘
                startActivity(new Intent(this, AutoHideIMEActivity.class));
                break;

            case 3: //android自定义控件—边缘凹凸的View
                startActivity(new Intent(this, CouponDisplayActivity.class));
                break;

            case 4: //activity之间传递对象
                startActivity(new Intent(this, PostActivity.class));
                break;

            case 5: //TabLayout配合ViewPager可以滑动
                startActivity(new Intent(this, TLTrunkActivity.class));
                break;

            case 6: //广播BroadCast
                startActivity(new Intent(this, BroadCastActivity.class));
                break;

            case 7: //Picasso
                startActivity(new Intent(this, PicassoDemoActivity.class));
                break;

            case 8: //Picasso的简单使用
                startActivity(new Intent(this, PicassoActivity.class));
                break;

            case 9: //Logger工具
                startActivity(new Intent(this, LoggerActivity.class));
                break;

            case 10://ListView更新单条数据，刷新
                startActivity(new Intent(this,LVUpdateSingleAct.class));
                break;

            case 11://Android DiskLruCache完全解析，硬盘缓存的最佳方案
                startActivity(new Intent(this, DiskLruCacheAct.class));
                break;

            case 12://3D图片滚动
                startActivity(new Intent(this, Image3DSwitchViewAct.class));
                break;

            case 13://学习ORMLite数据库
                startActivity(new Intent(this, ORMLiteActivity.class));
                break;

            case 14://Activity的生命周期
                startActivity(new Intent(this, LifeCycleActivity.class));
                break;

            case 15://用zxing做的生成二维码，并且扫码
                startActivity(new Intent(this, QRCodeActivity.class));
                break;

            case 16://自定义跑马灯效果(广告)
                startActivity(new Intent(this, MarqueeViewActivity.class));
                break;

            case 17://学习使用universal-image-loader-1.9.5
                startActivity(new Intent(this, ImageLoaderActivity.class));
                break;

            case 18://读取assets文件夹下的config.properties文件
                startActivity(new Intent(this, ConfigPropertiesAct.class));
                break;

            case 19://学习使用OKHttp
                startActivity(new Intent(this, OKHttpActivity.class));
                break;
        }
    }

    /**
     * 长按跳转
     *
     * @param view
     * @param position
     */
    private void clickLongJumpActivity(View view, int position) {
        switch (position) {
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;
        }
    }
}
