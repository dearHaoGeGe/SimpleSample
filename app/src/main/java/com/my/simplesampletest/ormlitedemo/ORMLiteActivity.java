package com.my.simplesampletest.ormlitedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * Created by YJH on 2016/6/17.
 */
public class ORMLiteActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_add_ORMLiteAct,btn_update_ORMLiteAct;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ormlite);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_add_ORMLiteAct = (Button) findViewById(R.id.btn_add_ORMLiteAct);
        btn_update_ORMLiteAct= (Button) findViewById(R.id.btn_update_ORMLiteAct);

        btn_update_ORMLiteAct.setOnClickListener(this);
        btn_add_ORMLiteAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        userDao = new UserDao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_ORMLiteAct:
                User user = new User("张三","大连人");
                User user2 = new User("李四","湖北人");
                User user3 = new User("Tom","加州人");
                User user4 = new User("张三","大连人");
                User user5 = new User("孙先生","台湾人");
                User user6 = new User("周永康","。。。");

                userDao.addUser(user);
                userDao.addUser(user2);
                userDao.addUser(user3);
                userDao.addUser(user4);
                userDao.addUser(user5);
                userDao.addUser(user6);
                break;

            case R.id.btn_update_ORMLiteAct:
                User user7 = new User("张学友","香港");
                user7.setId(1);
                userDao.updateUser(user7);
                break;
        }
    }
}
