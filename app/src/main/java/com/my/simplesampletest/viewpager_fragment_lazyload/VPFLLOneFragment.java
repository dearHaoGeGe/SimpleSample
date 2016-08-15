package com.my.simplesampletest.viewpager_fragment_lazyload;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;

/**
 * fragment各个生命周期详解:http://blog.csdn.net/wanghao200906/article/details/45561385
 * <p/>
 * Fragment生命周期（在不同的方法分别能做什么操作）:http://blog.csdn.net/s932974516/article/details/50528161
 * <p/>
 * android 三级缓存:http://blog.csdn.net/s932974516/article/details/50502080
 * <p/>
 * Created by YJH on 2016/7/14.
 */
public class VPFLLOneFragment extends BasePageFragment {

    private static final String TAG = "VPFLL___1";
    private TextView tv_VPFLLAct, tv_VPFLLOneFragment;
    private ViewPager viewPager_VPFLLAct;

    public static VPFLLOneFragment newInstance() {
        VPFLLOneFragment fragment = new VPFLLOneFragment();
        //Bundle args = new Bundle();
        //args.putString("key_fragment_title", title);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //一般findViewById还有监听的写在这个生命周期中
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_vpfll_one, container, false);
        tv_VPFLLOneFragment = (TextView) view.findViewById(R.id.tv_VPFLLOneFragment);
        return view;
    }

    //对fragment附着的Activity中组件绑定的时候写在这里
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");

        tv_VPFLLAct = (TextView) getActivity().findViewById(R.id.tv_VPFLLAct);   //获取activity中的组件,可以交互


        tv_VPFLLOneFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_VPFLLAct.setText("OK");
            }
        });
    }

    @Override
    public void fetchData() {
        Log.e(TAG, "fetchData");

        /** 在这里请求网络 */
    }
}
