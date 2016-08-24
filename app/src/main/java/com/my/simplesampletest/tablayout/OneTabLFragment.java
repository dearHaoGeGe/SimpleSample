package com.my.simplesampletest.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.utils.L;
import com.my.simplesampletest.utils.To;

/**
 *
 * Created by YJH on 2016/6/7.
 */
public class OneTabLFragment extends Fragment implements IValues{

    private static final String TAG="Fragment>1<--->";
    private TLTrunkActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (TLTrunkActivity) context;
        L.e(TAG,"onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one_tabl,container,false);
        L.e(TAG,"onCreateView");

        activity.setiValues(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void getValue(String str) {
        Toast.makeText(getActivity(), ""+str, Toast.LENGTH_SHORT).show();
    }
}
