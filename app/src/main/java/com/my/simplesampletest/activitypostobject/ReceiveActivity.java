package com.my.simplesampletest.activitypostobject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 接收上一个activity传过来的对象
 * <p/>
 * Created by YJH on 2016/6/7.
 */
public class ReceiveActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_return_ReceiveAct;
    private TextView tv_ReceiveAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_return_ReceiveAct = (Button) findViewById(R.id.btn_return_ReceiveAct);
        tv_ReceiveAct = (TextView) findViewById(R.id.tv_ReceiveAct);

        btn_return_ReceiveAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Person p = (Person) getIntent().getSerializableExtra("person");
        tv_ReceiveAct.setText("name=" + p.getName() + "\nage=" + p.getAge() +
                "\nsex=" + p.getSex() + "\nheight=" + p.getHeight() +
                "\nhobby=" + p.getHobby() + "\naddress=" + p.getAddress());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_return_ReceiveAct:
                break;
        }
    }
}
