package com.my.simplesampletest.activitypostobject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText ed_ReceiveAct;
    private static final String TAG="ReceiveActivity--->";

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
        ed_ReceiveAct= (EditText) findViewById(R.id.ed_ReceiveAct);

        btn_return_ReceiveAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Person p = (Person) getIntent().getSerializableExtra("person");
        if (p!=null){
            tv_ReceiveAct.setText("name=" + p.getName() + "\nage=" + p.getAge() +
                    "\nsex=" + p.getSex() + "\nheight=" + p.getHeight() +
                    "\nhobby=" + p.getHobby() + "\naddress=" + p.getAddress());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_return_ReceiveAct:
                break;
        }
    }

    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("result",ed_ReceiveAct.getText().toString().trim());    //trim()去两边空格的方法
        Log.d(TAG,""+ed_ReceiveAct.getText());
        setResult(1001,intent);
        finish();
        super.onBackPressed();
    }
}
