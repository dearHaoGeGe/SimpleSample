package com.my.simplesampletest.activitypostobject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 这个activity是往下一个activity发送object的
 * <p/>
 * Created by YJH on 2016/6/7.
 */
public class PostActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_sendObject_PostAct;
    private TextView tv_PostAct;
    private Person p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_sendObject_PostAct = (Button) findViewById(R.id.btn_sendObject_PostAct);
        tv_PostAct = (TextView) findViewById(R.id.tv_PostAct);

        btn_sendObject_PostAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        p = new Person();
        p.setName("狗熊");
        p.setAge(13);
        p.setHeight(190);
        p.setHobby("打猎");
        p.setSex("男");
        p.setAddress("弗雷尔卓德");

        tv_PostAct.setText("PostActivity"+"\nname=" + p.getName() + "\nage=" + p.getAge() + "\nheight=" +
                p.getHeight() + "\nhobby=" + p.getHobby() + "\nsex=" +
                p.getSex() + "\naddress=" + p.getAddress());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendObject_PostAct:
                postObject();
                break;
        }
    }

    private void postObject() {
        //intent传递对象
        Intent intent = new Intent(this, ReceiveActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("person", p);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
