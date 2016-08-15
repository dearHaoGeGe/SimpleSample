package com.my.simplesampletest.viewpager_fragment_lazyload;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by YJH on 2016/7/14.
 */
public class VPFLLPagerAdapter extends FragmentPagerAdapter {

    private List<BasePageFragment> fragmentList;
    private List<String> titles;

    public VPFLLPagerAdapter(FragmentManager fm, List<BasePageFragment> fragmentList, List<String> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        //return fragmentList.size();
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
