package com.my.simplesampletest.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewPager+Fragment LazyLoad
 *
 * http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261806&idx=1&sn=d166110fe43b42b5ecfbe3a219f3b712&scene=0#wechat_redirect
 *
 * Created by Administrator on 2016/7/13.
 */
public class BasePageFragment extends Fragment {

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
