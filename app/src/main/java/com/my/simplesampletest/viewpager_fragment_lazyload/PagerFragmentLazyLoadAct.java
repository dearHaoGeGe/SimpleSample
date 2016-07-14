package com.my.simplesampletest.viewpager_fragment_lazyload;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/7/14.
 */
public class PagerFragmentLazyLoadAct extends BaseActivity {

    private PagerSlidingTabStrip pagerSlidingTabStrip_VPFLLAct;
    private ViewPager viewPager_VPFLLAct;
    private List<Fragment> fragmentList;
    private List<String> titles;
    private VPFLLPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_fragment_lazyload);

        getSupportActionBar().hide();

        initView();
        initData();
    }

    @Override
    public void initView() {
        pagerSlidingTabStrip_VPFLLAct = (PagerSlidingTabStrip) findViewById(R.id.pagerSlidingTabStrip_VPFLLAct);
        viewPager_VPFLLAct = (ViewPager) findViewById(R.id.viewPager_VPFLLAct);
    }

    @Override
    public void initData() {
        //添加Fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(VPFLLOneFragment.newInstance());
        fragmentList.add(VPFLLTwoFragment.newInstance());
        fragmentList.add(VPFLLThreeFragment.newInstance());
        fragmentList.add(VPFLLFourFragment.newInstance());
        fragmentList.add(VPFLLFiveFragment.newInstance());

        //添加Title
        titles=new ArrayList<>();
        titles.add("第一个Fragment");
        titles.add("第二个Fragment");
        titles.add("第三个Fragment");
        titles.add("第四个Fragment");
        titles.add("第五个Fragment");

        adapter=new VPFLLPagerAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager_VPFLLAct.setAdapter(adapter);

        pagerSlidingTabStrip_VPFLLAct.setViewPager(viewPager_VPFLLAct);
    }
}
