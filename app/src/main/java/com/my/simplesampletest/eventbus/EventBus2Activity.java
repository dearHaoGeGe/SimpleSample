package com.my.simplesampletest.eventbus;

import android.os.Bundle;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 往这个页面传值
 *
 * @Subscribe(threadMode = ThreadMode.MainThread)   下面的方法修饰不要用private,否则的话会有异常
 * <p/>
 * Created by YJH on 2016/6/5.
 */
public class EventBus2Activity extends BaseActivity {

    private TextView tv2_EventBus2Act;
    private static final String TAG = "EventBus2Activity--->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);

        initView();
        initData();
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(EventBus2Activity.this);
        EventBus.getDefault().post("EventBus2Activity创建成功");

        tv2_EventBus2Act = (TextView) findViewById(R.id.tv2_EventBus2Act);
    }

    @Override
    public void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void EventBusReceiveMethod(EventBusActivity.EventStr str) {
        tv2_EventBus2Act.setText(str.getStr());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
