package com.my.simplesampletest.guide_pager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * app带圆点指示的引导页
 *
 * Created by YJH on 2016/7/10.
 */
public class GuidePagerAdapter extends PagerAdapter {

    private List<View> viewList;

    public GuidePagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    /**
     * 返回页面的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        if (viewList != null) {
            return viewList.size();
        }
        return 0;
    }

    /**
     * 判断是否由对象生成界面
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化position位置的界面
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position), 0);
        return viewList.get(position);
    }

    /**
     * 移除页面
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(viewList.get(position));
    }
}
