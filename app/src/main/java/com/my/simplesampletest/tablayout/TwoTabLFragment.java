package com.my.simplesampletest.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.logger.entity.ViewTypeBean;
import com.my.simplesampletest.utils.L;

/**
 * Fragment跳转传值
 *
 * Created by YJH on 2016/6/7.
 */
public class TwoTabLFragment extends Fragment {

    private static final String TAG = "Fragment>2<--->";
    private TextView tv_fragment_two_tabl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.e(TAG, "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_tabl, container, false);
        L.e(TAG, "onCreateView");

        ViewTypeBean data = null;
        try {
            data = (ViewTypeBean) getArguments().getSerializable("data1");
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, "data1没有获取到");
        }
        if (null != data) {
            tv_fragment_two_tabl = (TextView) view.findViewById(R.id.tv_fragment_two_tabl);
            tv_fragment_two_tabl.setText("content = " + data.getContent() + ",viewType = " + data.getViewType());
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
