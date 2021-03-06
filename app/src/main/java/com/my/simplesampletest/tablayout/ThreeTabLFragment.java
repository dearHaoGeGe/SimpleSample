package com.my.simplesampletest.tablayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.utils.L;
import com.my.simplesampletest.utils.StringUtil;
import com.my.simplesampletest.utils.ToastUtil;

/**
 * Android基础控件——SeekBar的使用，仿淘宝滑动验证、复制文本
 * 参考：http://blog.csdn.net/qq_30379689/article/details/53284378
 * <p>
 * Created by YJH on 2016/6/7.
 */
public class ThreeTabLFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private static final String TAG = "Fragment>3<--->";
    private SeekBar sb_fragment_three_tabl;
    private TextView tv_fragment_three_tabl;        //请按住滑块，拖动到最右边
    private TextView tv_copy_fragment_three_tabl;   //复制
    private EditText et_copy_fragment_three_tabl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.e(TAG, "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three_tabl, container, false);
        L.e(TAG, "onCreateView");

        sb_fragment_three_tabl = (SeekBar) view.findViewById(R.id.sb_fragment_three_tabl);
        tv_fragment_three_tabl = (TextView) view.findViewById(R.id.tv_fragment_three_tabl);
        tv_copy_fragment_three_tabl = (TextView) view.findViewById(R.id.tv_copy_fragment_three_tabl);
        et_copy_fragment_three_tabl = (EditText) view.findViewById(R.id.et_copy_fragment_three_tabl);
        sb_fragment_three_tabl.setOnSeekBarChangeListener(this);
        tv_copy_fragment_three_tabl.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //----------------------------------- setOnSeekBarChangeListener开始 ------------------------------------------

    /**
     * seekBar进度变化时回调
     *
     * @param seekBar  seekBar
     * @param progress progress
     * @param fromUser fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (progress == seekBar.getMax()) {
            tv_fragment_three_tabl.setVisibility(View.VISIBLE);
            tv_fragment_three_tabl.setTextColor(Color.WHITE);
            tv_fragment_three_tabl.setText("完成验证");
        } else {
            tv_fragment_three_tabl.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * seekBar开始触摸时回调
     *
     * @param seekBar seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * seekBar停止触摸时回调
     *
     * @param seekBar seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != seekBar.getMax()) {
            seekBar.setProgress(0);
            tv_fragment_three_tabl.setVisibility(View.VISIBLE);
            tv_fragment_three_tabl.setTextColor(Color.GRAY);
            tv_fragment_three_tabl.setText("请按住滑块，拖动到最右边");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_copy_fragment_three_tabl:
                String text = et_copy_fragment_three_tabl.getText().toString();
                if (!StringUtil.isEmpty(text)) {
                    //复制文本
                    android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboardManager.setText(text);
                    ToastUtil.showToast(getActivity(), "复制成功");
                } else {
                    ToastUtil.showToast(getActivity(), "EditText中不能为空");
                }
                break;
        }
    }
    //----------------------------------- setOnSeekBarChangeListener结束 ------------------------------------------
}
