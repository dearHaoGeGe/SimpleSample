package com.my.simplesampletest.viewpager_fragment_lazyload;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.simplesampletest.R;

/**
 * Created by YJH on 2016/7/14.
 */
public class VPFLLTwoFragment extends BasePageFragment{

    private static final String TAG = "VPFLL___2";

    private TextView tv_VPFLLAct,tv_VPFLLTwoFragment;

    public static VPFLLTwoFragment newInstance(){
        VPFLLTwoFragment fragment = new VPFLLTwoFragment();
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
        View view=inflater.inflate(R.layout.fragment_vpfll_two,container,false);
        tv_VPFLLTwoFragment= (TextView) view.findViewById(R.id.tv_VPFLLTwoFragment);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"onActivityCreated");

        tv_VPFLLAct= (TextView) getActivity().findViewById(R.id.tv_VPFLLAct);

        tv_VPFLLTwoFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_VPFLLAct.setText("AK47");
            }
        });
    }

    @Override
    public void fetchData() {
        Log.e(TAG,"fetchData");
    }
}
