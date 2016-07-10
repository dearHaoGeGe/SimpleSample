package com.my.simplesampletest.guide_pager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.material_text_field.MaterialTextFieldAct;

import java.util.ArrayList;
import java.util.List;

/**
 * app带圆点指示的引导页
 *
 * http://mp.weixin.qq.com/s?__biz=MzAwOTUyNzI3Ng==&mid=2652071384&idx=1&sn=eb604a3df9dd894a2cfda0317ac26e47&scene=0&ptlang=2052&ADUIN=1607609655&ADSESSION=1468157568&ADTAG=CLIENT.QQ.5473_.0&ADPUBNO=26569#wechat_redirect
 *
 * Created by YJH on 2016/7/10.
 */
public class GuidePagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager_GuidePagerAct;

    /**
     * 图片资源的数组
     */
    private int[] mImageIdArray;

    /**
     * 图片的集合
     */
    private List<View> mViewList;

    /**
     * 放置圆点
     */
    private ViewGroup mViewGroup;

    /**
     * 实例化圆点View
     */
    private ImageView mImageView;

    private ImageView[] mImageViewArray;

    /**
     * 最后一页的按钮
     */
    private Button btn_GuidePagerAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //继承AppCompatActivity(V7包下的Activity)之后要用这个方法去掉title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_guide_pager);

        initView();
        initData();
    }


    public void initView() {
        btn_GuidePagerAct= (Button) findViewById(R.id.btn_GuidePagerAct);

        btn_GuidePagerAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuidePagerActivity.this,MaterialTextFieldAct.class));
                finish();
            }
        });
    }


    public void initData() {
        initViewpager();    //加载ViewPager

        initViewPagerTag(); //加载底部圆点
    }

    /**
     * 加载ViewPager
     */
    private void initViewpager() {
        viewPager_GuidePagerAct = (ViewPager) findViewById(R.id.viewPager_GuidePagerAct);
        //实例化图片资源
        mImageIdArray = new int[]{R.mipmap.happiness, R.mipmap.happy, R.mipmap.health, R.mipmap.success, R.mipmap.marilyn_monroe};

        mViewList = new ArrayList<>();
        //获取一个layout参数，设置为match_parent
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建view并进入集合
        for (int i = 0; i < mImageIdArray.length; i++) {
            //new imageview并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(mImageIdArray[i]);

            //将imageview加入到View集合中
            mViewList.add(imageView);
        }
        //View集合数据初始化好，setAdapter就可以了
        viewPager_GuidePagerAct.setAdapter(new GuidePagerAdapter(mViewList));
        //添加ViewPager的滑动监听，注意是.add...以前是setOnPageChangeListener方法，已过时
        viewPager_GuidePagerAct.addOnPageChangeListener(this);
    }


    /**
     * 加载底部圆点
     */
    private void initViewPagerTag() {
        //这里实例化LinearLayout
        mViewGroup = (ViewGroup) findViewById(R.id.ll_GuidePagerAct);
        //根据ViewPager的item数量实例化数组
        mImageViewArray = new ImageView[mViewList.size()];
        //循环新建底部圆点imageview，将生成的imageview保存到数组中
        for (int i = 0; i < mViewList.size(); i++) {
            mImageView = new ImageView(this);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            mImageView.setPadding(30, 0, 30, 0);
            mImageViewArray[i] = mImageView;
            //第一个页面需要设为选中状态，这里要使用两张不同的图片（选中与未选中）
            if (i == 0) {
                mImageView.setBackgroundResource(R.mipmap.ic_red_loudspeaker);
            } else {
                mImageView.setBackgroundResource(R.mipmap.ic_loudspeaker);
            }
            //将数组中的imageview加入到viewgroup
            mViewGroup.addView(mImageViewArray[i]);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //滑动后的监听
    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        for (int i = 0; i < mImageViewArray.length; i++) {
            mImageViewArray[position].setBackgroundResource(R.mipmap.ic_loudspeaker);
            if (position != i) {
                mImageViewArray[i].setBackgroundResource(R.mipmap.ic_red_loudspeaker);
            }
        }
        //判断是否最后一页，是则显示button
        if (position == mImageViewArray.length - 1) {
            btn_GuidePagerAct.setVisibility(View.VISIBLE);
        } else {
            btn_GuidePagerAct.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
