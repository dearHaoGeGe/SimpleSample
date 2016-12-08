package com.my.simplesampletest.logger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.my.simplesampletest.R;
import com.my.simplesampletest.logger.adapter.MenuPagerAdapter;
import com.my.simplesampletest.logger.fragment.MenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 和ViewPager嵌套
 *
 * Created by YJH on 2016/11/30 17:56.
 */
public class ViewPagerMenuActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private MenuPagerAdapter mMenuPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.btn_one).setOnClickListener(onClickListener);
        findViewById(R.id.btn_two).setOnClickListener(onClickListener);
        findViewById(R.id.btn_three).setOnClickListener(onClickListener);

        mViewPager = (ViewPager) findViewById(R.id.view_pager_menu);
        mViewPager.addOnPageChangeListener(simpleOnPageChangeListener);
        mViewPager.setOffscreenPageLimit(2);

        List<Fragment> fragments = new ArrayList<>(3);
        fragments.add(MenuFragment.newInstance());
        fragments.add(MenuFragment.newInstance());
        fragments.add(MenuFragment.newInstance());

        mMenuPagerAdapter = new MenuPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mMenuPagerAdapter);

        simpleOnPageChangeListener.onPageSelected(0);
    }

    /**
     * Btn点击监听。
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_one) {
                mViewPager.setCurrentItem(0, true);
            } else if (v.getId() == R.id.btn_two) {
                mViewPager.setCurrentItem(1, true);
            } else if (v.getId() == R.id.btn_three) {
                mViewPager.setCurrentItem(2, true);
            }
        }
    };

    private ViewPager.SimpleOnPageChangeListener simpleOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            getSupportActionBar().setSubtitle("第" + position + "个");
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
