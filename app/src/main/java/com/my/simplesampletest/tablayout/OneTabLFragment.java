package com.my.simplesampletest.tablayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.logger.entity.ViewTypeBean;
import com.my.simplesampletest.utils.L;
import com.my.simplesampletest.utils.To;

/**
 * Fragment跳转传值
 *
 * Created by YJH on 2016/6/7.
 */
public class OneTabLFragment extends Fragment implements IValues {

    private static final String TAG = "Fragment>1<--->";
    private TLTrunkActivity activity;
    private TextView tv_fragment_one_tabl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //activity = (TLTrunkActivity) context;
        L.e(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_tabl, container, false);
        L.e(TAG, "onCreateView");

        //activity.setiValues(this);

        ViewTypeBean data = null;
        try {
            data = (ViewTypeBean) getArguments().getSerializable("data0");
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, "data0没有获取到");
        }
        if (null != data) {
            tv_fragment_one_tabl = (TextView) view.findViewById(R.id.tv_fragment_one_tabl);
            tv_fragment_one_tabl.setText("content = " + data.getContent() + ",viewType = " + data.getViewType());
            tv_fragment_one_tabl.setBackgroundColor(Color.parseColor("#BBFFAA"));
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getValue(String str) {
        Toast.makeText(getActivity(), "" + str, Toast.LENGTH_SHORT).show();
    }
}
