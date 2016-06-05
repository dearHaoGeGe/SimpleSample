package com.my.simplesampletest.autohideime;

import android.os.Bundle;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 自动隐藏键盘的第三方
 * 参考：http://www.tuicool.com/kans/1224087318
 * 参考：http://www.tuicool.com/articles/qmiiUfJ
 *
 * Created by YJH on 2016/6/5.
 */
public class AutoHideIMEActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_hide);
        HideIMEUtil.wrap(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
