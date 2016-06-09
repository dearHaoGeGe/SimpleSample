package com.my.simplesampletest.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 练习BroadCast的activity
 * <p>
 * Created by YJH on 2016/6/9.
 */
public class BroadCastActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_send_BroadCastAct;
    private DynamicReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_send_BroadCastAct = (Button) findViewById(R.id.btn_send_BroadCastAct);

        btn_send_BroadCastAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        //注册广播接收者
        IntentFilter filter = new IntentFilter();
        filter.addAction("动态注册的广播");
        receiver = new DynamicReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_BroadCastAct:
                Intent intent = new Intent();
                intent.setAction("动态注册的广播");
                intent.putExtra("传值", 666);     //intent可传值
                sendBroadcast(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);   //取消注册
    }
}
