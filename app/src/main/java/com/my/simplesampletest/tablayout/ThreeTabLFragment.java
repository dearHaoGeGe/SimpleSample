package com.my.simplesampletest.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.simplesampletest.R;

/**
 * Created by YJH on 2016/6/7.
 */
public class ThreeTabLFragment extends Fragment {

    private static final String TAG="Fragment--->";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_three_tabl,container,false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
