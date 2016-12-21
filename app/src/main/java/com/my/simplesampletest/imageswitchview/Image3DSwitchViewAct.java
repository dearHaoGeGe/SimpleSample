package com.my.simplesampletest.imageswitchview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.imageswitchview.mei_tuan.GridViewAdapter;
import com.my.simplesampletest.imageswitchview.mei_tuan.Model;
import com.my.simplesampletest.imageswitchview.mei_tuan.ViewPagerAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 3D图片滚动、仿美团网，使用ViewPager+GridView实现左右滑动查看更多分类的功能
 * <p/>
 * 美团：http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650821215&idx=1&sn=fc4acc7167e2ce3935a7cfb04272650e&chksm=80b786c1b7c00fd75b03d050a73b617716782c1ff96b448ecc85595959aabf01d6d77321e858&scene=0#wechat_redirect
 * <p/>
 * Created by YJH on 2016/6/16.
 */
public class Image3DSwitchViewAct extends BaseActivity {

    /**
     * 滑动3D图片
     */
    private Image3DSwitchView imageSwitchView;

    //***************** 下面是美团ViewPager+GridView滑动 ***************//
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数(必须是偶数)
     */
    private int pageSize = 10;
    /**
     * 列的数量（一行显示的数量）
     */
    private int columnsNum = pageSize / 2;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;

    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV", "机票/火车票", "周边游", "美甲美睫",
            "火锅", "生日蛋糕", "甜品饮品", "水上乐园", "汽车服务", "美发", "丽人", "景点", "足疗按摩", "运动健身", "健身", "超市", "买菜",
            "今日新单", "小吃快餐", "面膜", "洗浴/汗蒸", "母婴亲子", "生活服务", "婚纱摄影", "学习培训", "家装", "结婚", "全部分配"};
    private ViewPager mPager;
    private List<View> mPagerList;
    private List<Model> mDatas;
    private LinearLayout mLlDot;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switch_view);

        initView();
        initData();
    }

    @Override
    public void initView() {
        init3DImageView();  //初始化3D滑动图片
        initMeiTuan();
    }

    @Override
    public void initData() {
    }

    /**
     * 初始化3D滑动图片
     */
    private void init3DImageView() {
        imageSwitchView = (Image3DSwitchView) findViewById(R.id.image_switch_view);
        imageSwitchView.setOnImageSwitchListener(new Image3DSwitchView.OnImageSwitchListener() {
            @Override
            public void onImageSwitch(int currentImage) {
                Logger.d("TAG", "current image is " + currentImage);
            }
        });
        imageSwitchView.setCurrentImage(1);
    }

    /**
     * 初始化美团ViewPager+GridView左右滑动
     */
    private void initMeiTuan() {
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mLlDot = (LinearLayout) findViewById(R.id.ll_dot);

        //初始化数据源
        initDatas();
        inflater = LayoutInflater.from(this);
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<View>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
            gridView.setNumColumns(columnsNum);
            gridView.setAdapter(new GridViewAdapter(this, mDatas, i, pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + curIndex * pageSize;
                    Toast.makeText(Image3DSwitchViewAct.this, mDatas.get(pos).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        //设置适配器
        mPager.setAdapter(new ViewPagerAdapter(mPagerList));
        //设置圆点
        setOvalLayout();
    }

    /**
     * 初始化数据源
     */
    private void initDatas() {
        mDatas = new ArrayList<Model>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());
            mDatas.add(new Model(titles[i], imageId));
        }
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageSwitchView.clear();
    }
}
