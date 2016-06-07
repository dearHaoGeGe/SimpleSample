package com.my.simplesampletest.activitypostobject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private Button btn_sendObject_PostAct, btn_returnValues_PostAct;
    private TextView tv_PostAct, tv2_PostAct;
    private Person p;
    private static final String TAG="PostActivity--->";

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
        btn_returnValues_PostAct = (Button) findViewById(R.id.btn_returnValues_PostAct);
        tv_PostAct = (TextView) findViewById(R.id.tv_PostAct);
        tv2_PostAct = (TextView) findViewById(R.id.tv2_PostAct);

        btn_sendObject_PostAct.setOnClickListener(this);
        btn_returnValues_PostAct.setOnClickListener(this);
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

        tv_PostAct.setText("PostActivity" + "\nname=" + p.getName() + "\nage=" + p.getAge() + "\nheight=" +
                p.getHeight() + "\nhobby=" + p.getHobby() + "\nsex=" +
                p.getSex() + "\naddress=" + p.getAddress());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendObject_PostAct:
                postObject();
                break;

            case R.id.btn_returnValues_PostAct:
                returnValues();
                break;
        }
    }

    /**
     * intent跳转传递对象
     */
    private void postObject() {
        Intent intent = new Intent(this, ReceiveActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("person", p);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转回传值
     */
    private void returnValues() {
        Intent intent = new Intent(this, ReceiveActivity.class);
        /**
         * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
         * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
         */
        startActivityForResult(intent, 1000);
    }

    /**
     * 所有的Activity对象的返回值都是由这个方法来接收
     * requestCode:    表示的是启动一个Activity时传过去的requestCode值
     * resultCode：表示的是启动后的Activity回传值时的resultCode值
     * data：表示的是启动后的Activity回传过来的Intent对象
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            String result = data.getStringExtra("result");
            Log.d(TAG,result);
            tv2_PostAct.setText(result);
        }
    }
}
