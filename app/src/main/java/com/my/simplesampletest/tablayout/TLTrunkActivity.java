package com.my.simplesampletest.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.my.simplesampletest.utils.L;
import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.utils.To;

/**
 * TrunkActivity的上面有4个fragment
 * <p>
 * Created by YJH on 2016/6/7.
 */
public class TLTrunkActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout_TLTrunkAct;
    private ViewPager viewPager_TLTrunkAct;
    private MyTBLFragmentPagerAdapter adapter;
    private TabLayout.Tab one, two, three, four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tltrunk);

//        Toast.makeText(TLTrunkActivity.this, "", Toast.LENGTH_SHORT).show();
        To.showShort(this,"哈哈");
        L.v("vv没有TAG");
        L.v("测试L","vv有TAG");
        L.d("dd没有TAG");
        L.d("测试L","dd有TAG");
        L.i("ii没有TAG");
        L.i("测试L","ii有TAG");
        L.w("ww没有TAG");
        L.w("测试L","ww有TAG");
        L.e("ee没有TAG");
        L.e("测试L","ee有TAG");

        initView();
        initData();
    }

    @Override
    public void initView() {
        tabLayout_TLTrunkAct = (TabLayout) findViewById(R.id.tabLayout_TLTrunkAct);
        viewPager_TLTrunkAct = (ViewPager) findViewById(R.id.viewPager_TLTrunkAct);
    }

    @Override
    public void initData() {
        adapter = new MyTBLFragmentPagerAdapter(getSupportFragmentManager(), new String[]{"唐僧", "大师兄", "二师兄", "沙和尚"});
        viewPager_TLTrunkAct.setAdapter(adapter);
        tabLayout_TLTrunkAct.setupWithViewPager(viewPager_TLTrunkAct);

        one = tabLayout_TLTrunkAct.getTabAt(0);
        two = tabLayout_TLTrunkAct.getTabAt(1);
        three = tabLayout_TLTrunkAct.getTabAt(2);
        four = tabLayout_TLTrunkAct.getTabAt(3);

        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);
        three.setIcon(R.mipmap.ic_launcher);
        four.setIcon(R.mipmap.ic_launcher);

        tabLayout_TLTrunkAct.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab == tabLayout_TLTrunkAct.getTabAt(0)) {
            one.setIcon(R.mipmap.app_icon);
            viewPager_TLTrunkAct.setCurrentItem(0);
        } else if (tab == tabLayout_TLTrunkAct.getTabAt(1)) {
            two.setIcon(R.mipmap.app_icon);
            viewPager_TLTrunkAct.setCurrentItem(1);
        } else if (tab == tabLayout_TLTrunkAct.getTabAt(2)) {
            three.setIcon(R.mipmap.app_icon);
            viewPager_TLTrunkAct.setCurrentItem(2);
        } else if (tab == tabLayout_TLTrunkAct.getTabAt(3)) {
            four.setIcon(R.mipmap.app_icon);
            viewPager_TLTrunkAct.setCurrentItem(3);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (tab == tabLayout_TLTrunkAct.getTabAt(0)) {
            one.setIcon(R.mipmap.ic_launcher);
        } else if (tab == tabLayout_TLTrunkAct.getTabAt(1)) {
            two.setIcon(R.mipmap.ic_launcher);
        } else if (tab == tabLayout_TLTrunkAct.getTabAt(2)) {
            three.setIcon(R.mipmap.ic_launcher);
        } else if (tab == tabLayout_TLTrunkAct.getTabAt(3)) {
            four.setIcon(R.mipmap.ic_launcher);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 适配器
     */
    private class MyTBLFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitle;

        public MyTBLFragmentPagerAdapter(FragmentManager fm, String[] mTitle) {
            super(fm);
            this.mTitle = mTitle;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new OneTabLFragment();

                case 1:
                    return new TwoTabLFragment();

                case 2:
                    return new ThreeTabLFragment();

                case 3:
                    return new FourTabLFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }


}
