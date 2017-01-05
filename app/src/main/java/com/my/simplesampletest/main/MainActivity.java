package com.my.simplesampletest.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.about.AboutActivity;
import com.my.simplesampletest.act_life_cycle.LifeCycleActivity;
import com.my.simplesampletest.activitypostobject.PostActivity;
import com.my.simplesampletest.add_local_pic.DynamicPostAct;
import com.my.simplesampletest.autohideime.AutoHideIMEActivity;
import com.my.simplesampletest.autohideime.UseEditTextAct;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.base.MPermissionListener;
import com.my.simplesampletest.blurred_view.BlurredViewAct;
import com.my.simplesampletest.broadcast.BroadCastActivity;
import com.my.simplesampletest.coordinator_layout.CoordinatorLayoutAct;
import com.my.simplesampletest.coupondisplayview.CouponDisplayActivity;
import com.my.simplesampletest.cylinderImage_view.CylinderImageViewAct;
import com.my.simplesampletest.download_apk.DownloadAPKAct;
import com.my.simplesampletest.eventbus.EventBusActivity;
import com.my.simplesampletest.girdview.GridViewActivity;
import com.my.simplesampletest.guide_pager.GuidePagerActivity;
import com.my.simplesampletest.imagecache.DiskLruCacheAct;
import com.my.simplesampletest.imageloader.ImageLoaderActivity;
import com.my.simplesampletest.imageswitchview.Image3DSwitchViewAct;
import com.my.simplesampletest.immersive_status_bar.ImmersiveStatusBarAct;
import com.my.simplesampletest.jsonobjecttostring.JSONObjectActivity;
import com.my.simplesampletest.loading.LoadActivity;
import com.my.simplesampletest.logger.LoggerActivity;
import com.my.simplesampletest.lv_diff_type.LvDifferentTypeAct;
import com.my.simplesampletest.lv_nesting_lv.NestOneActivity;
import com.my.simplesampletest.lvupdatesingledata.LVUpdateSingleAct;
import com.my.simplesampletest.marqueeview.MarqueeViewActivity;
import com.my.simplesampletest.material_text_field.MaterialTextFieldAct;
import com.my.simplesampletest.mvp.view.LoginMVPActivity;
import com.my.simplesampletest.observer_mode.ObserverActivity;
import com.my.simplesampletest.okhttp.OKHttpActivity;
import com.my.simplesampletest.orientation_rc_view.OrientationRCViewAct;
import com.my.simplesampletest.ormlitedemo.ORMLiteActivity;
import com.my.simplesampletest.pic_save_sd.PicSaveSDActivity;
import com.my.simplesampletest.picasso.PicassoActivity;
import com.my.simplesampletest.picasso.PicassoDemoActivity;
import com.my.simplesampletest.read_config_properties.ConfigPropertiesAct;
import com.my.simplesampletest.segment_view.SegmentViewActivity;
import com.my.simplesampletest.servicelife.ServiceActivity;
import com.my.simplesampletest.snackbar.SnackbarActivity;
import com.my.simplesampletest.swipe_refresh_layout.SwipeRefreshLayoutAct;
import com.my.simplesampletest.tablayout.FragmentToFragmentAct;
import com.my.simplesampletest.tablayout.TLTrunkActivity;
import com.my.simplesampletest.utils.ToastUtil;
import com.my.simplesampletest.viewpager_fragment_lazyload.PagerFragmentLazyLoadAct;
import com.my.simplesampletest.zxing.QRCodeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity
 */
public class MainActivity extends BaseActivity implements MainActAdapter.MyItemOnClickListener, MainActAdapter.MyItemOnLongClickListener {

    private final String TAG = getClass().getSimpleName() + "--->";
    private RecyclerView rcView_main;
    private List<String> data;
    private MainActAdapter adapter;
    private long fitstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        requestPermission();
    }

    private void requestPermission() {
        requestRuntimePermission(new MPermissionListener() {
                                     @Override
                                     public void onGranted() {
                                         Toast.makeText(MainActivity.this, "所有权限都已经授权", Toast.LENGTH_SHORT).show();
                                     }

                                     @Override
                                     public void onDenied(List<String> deniedPermission) {
                                         for (String permissions : deniedPermission) {
                                             Log.e(TAG, "被拒绝的权限：" + permissions);
                                         }
                                         ToastUtil.showToast(MainActivity.this, "有" + deniedPermission.size() + "权限没有被允许，3S后自动退出程序");
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(3000);
                                                    killSelf();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }).start();
                                     }
                                 },
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_app:
                startActivity(new Intent(this, AboutActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (fitstTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            System.exit(0);
        } else {
            ToastUtil.showToast(this, "再按一次退出程序~");
        }
        fitstTime = System.currentTimeMillis();
    }

    /**
     * 杀死自己
     */
    private void killSelf(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 往List中添加数据
     */
    private void addDataToList() {
        data = new ArrayList<>();
        data.add("EventBus传递事件");
        data.add("测试string装换成JSONObject");
        data.add("Android快速实现点击任意位置收缩键盘、和侧滑菜单");
        data.add("android自定义控件—边缘凹凸的View、CardView的使用方法、分享弹窗ShareView");
        data.add("activity之间传递对象");
        data.add("TabLayout配合ViewPager可以滑动、SeekBar的使用，仿淘宝滑动验证、复制文本");
        data.add("广播BroadCast");
        data.add("PicassoDemo");
        data.add("Picasso的简单使用");
        data.add("了解Logger、RecyclerView侧滑菜单，长按拖拽，滑动删除，自动加载更多，和ViewPager结合使用，和任何下拉刷新框架结合使用。");
        data.add("ListView更新单条数据，刷新，禁止截屏");
        data.add("Android DiskLruCache完全解析，硬盘缓存的最佳方案");
        data.add("3D图片滚动、仿美团网，使用ViewPager+GridView实现左右滑动查看更多分类的功能");
        data.add("学习ORMLite数据库");
        data.add("Activity的生命周期");
        data.add("用zxing做的生成二维码，并且扫码");
        data.add("自定义跑马灯效果(广告)");
        data.add("学习使用universal-image-loader-1.9.5");
        data.add("读取assets文件夹下的config.properties文件、夜间模式");
        data.add("学习使用OKHttp");
        data.add("Android 快速实现文件下载（只有4行代码）");
        data.add("登录动画效果");
        data.add("RadioGroup实现类似ios的分段选择(UISegmentedControl)控件");
        data.add("app带圆点指示的引导页");
        data.add("SwipeRefreshLayout Google官方下拉刷新组件");
        data.add("学习使用Snackbar");
        data.add("循环显示超长图片");
        data.add("学习ViewPager+Fragment LazyLoad");
        data.add("Android EditText 的使用及值得注意的地方");
        data.add("学习使用GridView");
        data.add("读取本地照片发说说的Demo");
        data.add("模糊背景图片");
        data.add("Service的生命周期");
        data.add("一些加载动画");
        data.add("MVP写的一个登录的Demo");
        data.add("学习沉浸式状态栏");
        data.add("分Type的ListView");
        data.add("ListView嵌套ListView");
        data.add("调取相机拍照保存在本地，并且显示出来");
        data.add("横向的RecyclerView，动态加载设置组件的宽高，自己封装的网络请求");
        data.add("CoordinatorLayout布局的使用方式");
        data.add("Fragment跳转传值、Fragment覆盖Fragment");
        data.add("观察者模式");
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

            case 2: //Android快速实现点击任意位置收缩键盘、和侧滑菜单
                startActivity(new Intent(this, AutoHideIMEActivity.class));
                break;

            case 3: //android自定义控件—边缘凹凸的View、CardView的使用方法、分享弹窗ShareView
                startActivity(new Intent(this, CouponDisplayActivity.class));
                break;

            case 4: //activity之间传递对象
                startActivity(new Intent(this, PostActivity.class));
                break;

            case 5: //TabLayout配合ViewPager可以滑动、SeekBar的使用，仿淘宝滑动验证、复制文本
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

            case 10://ListView更新单条数据，刷新，禁止截屏
                startActivity(new Intent(this, LVUpdateSingleAct.class));
                break;

            case 11://Android DiskLruCache完全解析，硬盘缓存的最佳方案
                startActivity(new Intent(this, DiskLruCacheAct.class));
                break;

            case 12://3D图片滚动、仿美团网，使用ViewPager+GridView实现左右滑动查看更多分类的功能
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

            case 18://读取assets文件夹下的config.properties文件、夜间模式
                startActivity(new Intent(this, ConfigPropertiesAct.class));
                break;

            case 19://学习使用OKHttp
                startActivity(new Intent(this, OKHttpActivity.class));
                break;

            case 20://Android 快速实现文件下载（只有4行代码）
                startActivity(new Intent(this, DownloadAPKAct.class));
                break;

            case 21://登录动画效果
                startActivity(new Intent(this, MaterialTextFieldAct.class));
                break;

            case 22://RadioGroup实现类似ios的分段选择(UISegmentedControl)控件
                startActivity(new Intent(this, SegmentViewActivity.class));
                break;

            case 23://app带圆点指示的引导页
                startActivity(new Intent(this, GuidePagerActivity.class));
                break;

            case 24://SwipeRefreshLayout   Google官方下拉刷新组件
                startActivity(new Intent(this, SwipeRefreshLayoutAct.class));
                break;

            case 25://学习使用Snackbar
                startActivity(new Intent(this, SnackbarActivity.class));
                break;

            case 26://循环显示超长图片
                startActivity(new Intent(this, CylinderImageViewAct.class));
                break;

            case 27://学习ViewPager+Fragment LazyLoad
                startActivity(new Intent(this, PagerFragmentLazyLoadAct.class));
                break;

            case 28://Android EditText 的使用及值得注意的地方
                startActivity(new Intent(this, UseEditTextAct.class));
                break;

            case 29://学习使用GridView
                startActivity(new Intent(this, GridViewActivity.class));
                break;

            case 30://读取本地照片发说说的Demo
                startActivity(new Intent(this, DynamicPostAct.class));
                break;

            case 31://模糊背景图片
                startActivity(new Intent(this, BlurredViewAct.class));
                break;

            case 32://Service的生命周期
                startActivity(new Intent(this, ServiceActivity.class));
                break;

            case 33://一些加载动画
                startActivity(new Intent(this, LoadActivity.class));
                break;

            case 34://MVP写的一个登录的Demo
                startActivity(new Intent(this, LoginMVPActivity.class));
                break;

            case 35://学习沉浸式状态栏
                startActivity(new Intent(this, ImmersiveStatusBarAct.class));
                break;

            case 36://分Type的ListView
                startActivity(new Intent(this, LvDifferentTypeAct.class));
                break;

            case 37://ListView嵌套ListView
                startActivity(new Intent(this, NestOneActivity.class));
                break;

            case 38://调取相机拍照保存在本地，并且显示出来
                startActivity(new Intent(this, PicSaveSDActivity.class));
                break;

            case 39://横向的RecyclerView，动态加载设置组件的宽高，自己封装的网络请求
                startActivity(new Intent(this, OrientationRCViewAct.class));
                break;

            case 40://CoordinatorLayout布局的使用方式
                startActivity(new Intent(this, CoordinatorLayoutAct.class));
                break;

            case 41://Fragment跳转传值、Fragment覆盖Fragment
                startActivity(new Intent(this, FragmentToFragmentAct.class));
                break;

            case 42://观察者模式
                startActivity(new Intent(this, ObserverActivity.class));
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
