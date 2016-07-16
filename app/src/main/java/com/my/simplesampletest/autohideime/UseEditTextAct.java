package com.my.simplesampletest.autohideime;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by YJH on 2016/7/15.
 */
public class UseEditTextAct extends BaseActivity implements View.OnClickListener, TextWatcher, View.OnKeyListener {

    private static final String TAG = "EditText需要注意的地方";
    private EditText ed_zh_cn, ed_en;                //设置默认输入法
    private EditText ed_open_input, ed_close_input;  //打开和关闭输入法
    private EditText ed_addTextChangedListener;     //监听EditText的输入状态
    private EditText ed_OnKeyListener;              //监听输入法中的回车按钮
    private EditText ed_InputTxtFilter;     //限制输入内容
    private TextView tv_open_input, tv_close_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_edit_text);

        initView();
        initData();
    }

    @Override
    public void initView() {
        ed_zh_cn = (EditText) findViewById(R.id.ed_zh_cn);
        ed_en = (EditText) findViewById(R.id.ed_en);

        ed_open_input = (EditText) findViewById(R.id.ed_open_input);
        ed_close_input = (EditText) findViewById(R.id.ed_close_input);
        tv_open_input = (TextView) findViewById(R.id.tv_open_input);
        tv_close_input = (TextView) findViewById(R.id.tv_close_input);
        ed_addTextChangedListener = (EditText) findViewById(R.id.ed_addTextChangedListener);
        ed_OnKeyListener = (EditText) findViewById(R.id.ed_OnKeyListener);
        ed_InputTxtFilter= (EditText) findViewById(R.id.ed_InputTxtFilter);

        tv_open_input.setOnClickListener(this);
        tv_close_input.setOnClickListener(this);

        ed_addTextChangedListener.addTextChangedListener(this);

        ed_OnKeyListener.setOnKeyListener(this);
    }

    @Override
    public void initData() {
        //ed_zh_cn.setInputType(EditorInfo.TYPE_CLASS_PHONE);   //默认电话号码
        ed_zh_cn.setInputType(EditorInfo.TYPE_CLASS_TEXT);  //默认中文
        ed_en.setInputType(EditorInfo.TYPE_TEXT_VARIATION_URI); //默认英文


        /**
         * 改变输入法中回车按钮的显示内容
         *
         * IME_ACTION_SEARCH 搜索
         * IME_ACTION_SEND 发送
         * IME_ACTION_NEXT 下一步
         * IME_ACTION_DONE 完成
         */
        ed_OnKeyListener.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        InputTxtFilter.inputFilter(this,ed_InputTxtFilter,InputTxtFilter.INPUT_TYPE_EN,5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_open_input:
                Toast.makeText(UseEditTextAct.this, "打开输入法~", Toast.LENGTH_SHORT).show();
                openInput(this, ed_open_input);
                break;

            case R.id.tv_close_input:
                Toast.makeText(UseEditTextAct.this, "关闭输入法~", Toast.LENGTH_SHORT).show();
                closeInput(this, ed_close_input);
                break;
        }
    }


    /**
     * 打开输入法
     *
     * @param context
     * @param editText
     */
    private void openInput(Context context, View editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, 0);
    }

    /**
     * 关闭输入法
     *
     * @param context
     * @param editText
     */
    private void closeInput(Context context, View editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    //********************************addTextChangedListener  开始**********************************//
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * 监听EditText输入内容的变化，在这里可以监听输入内容的长度
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Logger.d(TAG,s.toString());
    }

    /**
     * 这里可以实现所输即所得，用户输入的同时可以立即在这里根据输入内容执行操作，显示搜索结果
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {
        Logger.d(TAG, s.toString());
    }
    //********************************addTextChangedListener  结束*********************************//


    /**
     * 监听输入法按键
     *
     * @param v
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            Toast.makeText(UseEditTextAct.this, "手指弹起时执行确认功能", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

}
