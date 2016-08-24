package com.my.simplesampletest.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.my.simplesampletest.utils.L;
import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.utils.To;

/**
 * TrunkActivity的上面有4个fragment
 *
 * <p>
 * Created by YJH on 2016/6/7.
 */
public class TLTrunkActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout_TLTrunkAct;
    private ViewPager viewPager_TLTrunkAct;
    private MyTBLFragmentPagerAdapter adapter;
    private TabLayout.Tab one, two, three, four;
    private String testValue = "接口传值";
    private IValues iValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tltrunk);

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

        if (iValues!=null){
            iValues.getValue(testValue);
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


        /**
         * 防止fragment被销毁
         * 参考：http://www.th7.cn/Program/Android/201412/333619.shtml
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }
    }


    public void setiValues(IValues iValues) {
        this.iValues = iValues;
    }
}
