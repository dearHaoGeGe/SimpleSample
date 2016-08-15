package com.my.simplesampletest.viewpager_fragment_lazyload;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewPager+Fragment LazyLoad
 * <p/>
 * http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261806&idx=1&sn=d166110fe43b42b5ecfbe3a219f3b712&scene=0#wechat_redirect
 * <p/>
 * Created by YJH on 2016/7/13.
 */
public abstract class BasePageFragment extends Fragment {

    protected boolean isViewInitiated;  //视图是否启动
    protected boolean isVisibleToUser;  //是否对用户可见
    protected boolean isDataInitiated;  //数据是否启动

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    /**
     * 判断当前UI是否可见
     *
     * 设置一个系统提示是否这个片段的UI是目前用户可见的。这提示默认为true并持续在片段实例保存和恢复状态。
     * 应用程序可以设置为false,以表明片段的UI是滚动的可见性或另有用户不能直接看到。
     * 这可能是使用的系统优化操作,比如片段的生命周期更新或加载程序命令的行为。
     *
     * @param isVisibleToUser 真实如果这个片段的UI目前可见的用户(默认),如果它不是假的。
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }


    /**
     * 获取数据
     */
    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    /**
     * 准备获取数据
     *
     * @param forceUpdate
     * @return
     */
    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    //public int getCurrentItem
}
