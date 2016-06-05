package com.my.simplesampletest.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * EventBus展示Activity
 *
 * @Subscribe(threadMode = ThreadMode.MainThread)不要在eventbus主线程接收方法中做太多耗时的操作
 * @Subscribe(threadMode = ThreadMode.Async)    耗时的操作要放在异步的线程中
 * <p/>
 * Created by YJH on 2016/6/4.
 */
public class EventBusActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_EventBusAct;
    private Button btn_EventBusAct;
    private static final String TAG = "EventBusActivity--->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);


        initView();
        initData();
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        tv_EventBusAct = (TextView) findViewById(R.id.tv_EventBusAct);
        btn_EventBusAct = (Button) findViewById(R.id.btn_EventBusAct);

        btn_EventBusAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tv_EventBusAct.setText("38");
    }

    @Subscribe(threadMode = ThreadMode.Async)
    public void EventBusReceiveMethod(String str) {
        if (str.equals("EventBus2Activity创建成功")) {
            Log.d(TAG, "EventBus2Activity创建成功");
            longTime(); //模仿耗时操作
            EventBus.getDefault().post(new EventStr(tv_EventBusAct.getText() + ""));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 模仿耗时操作
     */
    private void longTime() {
        int sum = 0;
        for (int i = 0; i < 1000000000; i++) {
            sum = i + sum;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_EventBusAct:
                startActivity(new Intent(this, EventBus2Activity.class));
                break;
        }
    }

    /**
     * EventBus要发送的类
     */
    public class EventStr {
        private String str;

        public EventStr(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }
}
