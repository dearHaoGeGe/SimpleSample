package com.my.simplesampletest.logger;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.utils.ToastUtil;
import com.orhanobut.logger.Logger;

/**
 * 了解Logger
 *
 * Created by YJH on 2016/6/15.
 */
public class LoggerActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_LoggerAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);

        initView();
        initData();
    }

    @Override
    public void initView() {
        tv_LoggerAct = (TextView) findViewById(R.id.tv_LoggerAct);

        tv_LoggerAct.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_LoggerAct:
                //Toast.makeText(LoggerActivity.this, "看Log", Toast.LENGTH_SHORT).show();
                ToastUtil.showToast(this,"看Log");
//                Logger.d("测试", "666~");
                Logger.json("999","{\"data\":{\"cases\":[{\"bankRead\":1,\"bankResult\":2,\"caseName\":\"测试案件01\",\"caseNo\":\"（2016）湘执测试01号\",\"gewRead\":0,\"gewResult\":0,\"id\":\"201606060001 \",\"policeRead\":1,\"policeResult\":2,\"registerDate\":\"2016-06-06 13:59:06\"},{\"bankRead\":0,\"bankResult\":0,\"caseName\":\"测试案件02\",\"caseNo\":\"（2016）湘执测试02号\",\"gewRead\":0,\"gewResult\":0,\"id\":\"201606060002 \",\"policeRead\":0,\"policeResult\":2,\"registerDate\":\"2016-05-08 16:00:55\"},{\"bankRead\":0,\"bankResult\":0,\"caseName\":\"测试案件03\",\"caseNo\":\"（2016）湘执测试03号\",\"gewRead\":0,\"gewResult\":0,\"id\":\"201606060003 \",\"policeRead\":0,\"policeResult\":2,\"registerDate\":\"2016-05-08 16:00:55\"},{\"bankRead\":0,\"bankResult\":0,\"caseName\":\"测试案件05\",\"caseNo\":\"（2016）湘执测试05号\",\"gewRead\":0,\"gewResult\":0,\"id\":\"201606060005 \",\"policeRead\":0,\"policeResult\":2,\"registerDate\":\"2016-05-08 16:00:55\"},{\"bankRead\":0,\"bankResult\":0,\"caseName\":\"测试案件04\",\"caseNo\":\"（2016）湘执测试04号\",\"gewRead\":0,\"gewResult\":0,\"id\":\"201606060004 \",\"policeRead\":0,\"policeResult\":2,\"registerDate\":\"2016-05-08 16:00:55\"}],\"pageInfo\":{\"pageNo\":1,\"pageSize\":20,\"total\":1088}},\"successful\":true}");
                break;
        }
    }
}
