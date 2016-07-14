package com.my.simplesampletest.viewpager_fragment_lazyload;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.simplesampletest.R;

/**
 * Created by Administrator on 2016/7/14.
 */
public class VPFLLFourFragment extends BasePageFragment {

    private static final String TAG = "Four";

    public static VPFLLFourFragment newInstance() {
        VPFLLFourFragment fragment = new VPFLLFourFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
        View view=inflater.inflate(R.layout.fragment_vpfll_four,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"onActivityCreated");
    }

    @Override
    public void fetchData() {
        Log.e(TAG,"fetchData");
    }

}
