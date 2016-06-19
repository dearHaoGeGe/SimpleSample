package com.my.simplesampletest.ormlitedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 官网：http://ormlite.com/
 *
 * Created by YJH on 2016/6/17.
 */
public class ORMLiteActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_add_ORMLiteAct,btn_update_ORMLiteAct,btn_updateById_ORMLiteAct,
            btn_updateID_1_ORMLiteAct,btn_del_one_ORMLiteAct,btn_del_List_ORMLiteAct,
            btn_del_ListID_ORMLiteAct,btn_QueryBuilder_ORMLiteAct,btn_QueryBuilder2_ORMLiteAct,
            btn_orderBy_ORMLiteAct;
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
        btn_updateById_ORMLiteAct= (Button) findViewById(R.id.btn_updateById_ORMLiteAct);
        btn_updateID_1_ORMLiteAct= (Button) findViewById(R.id.btn_updateID_1_ORMLiteAct);
        btn_del_one_ORMLiteAct= (Button) findViewById(R.id.btn_del_one_ORMLiteAct);
        btn_del_List_ORMLiteAct= (Button) findViewById(R.id.btn_del_List_ORMLiteAct);
        btn_del_ListID_ORMLiteAct= (Button) findViewById(R.id.btn_del_ListID_ORMLiteAct);
        btn_QueryBuilder_ORMLiteAct= (Button) findViewById(R.id.btn_QueryBuilder_ORMLiteAct);
        btn_QueryBuilder2_ORMLiteAct= (Button) findViewById(R.id.btn_QueryBuilder2_ORMLiteAct);
        btn_orderBy_ORMLiteAct= (Button) findViewById(R.id.btn_orderBy_ORMLiteAct);

        btn_update_ORMLiteAct.setOnClickListener(this);
        btn_add_ORMLiteAct.setOnClickListener(this);
        btn_updateById_ORMLiteAct.setOnClickListener(this);
        btn_updateID_1_ORMLiteAct.setOnClickListener(this);
        btn_del_one_ORMLiteAct.setOnClickListener(this);
        btn_del_List_ORMLiteAct.setOnClickListener(this);
        btn_del_ListID_ORMLiteAct.setOnClickListener(this);
        btn_QueryBuilder_ORMLiteAct.setOnClickListener(this);
        btn_QueryBuilder2_ORMLiteAct.setOnClickListener(this);
        btn_orderBy_ORMLiteAct.setOnClickListener(this);
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

            case R.id.btn_updateById_ORMLiteAct:
                Logger.d("哈哈",""+6999);
                User user8 = new User("周YK","蛀虫");
                user8.setId(6L);
                userDao.updateUserByID(user8,10L);
                break;

            case R.id.btn_updateID_1_ORMLiteAct:
                User user9 = new User("解决了","id = 1");
                user9.setId(1L);
                userDao.updateUserByBuilder(user9);
                break;

            case R.id.btn_del_one_ORMLiteAct:
                Toast.makeText(ORMLiteActivity.this, "点击了~", Toast.LENGTH_SHORT).show();
                User user10=new User("周永康","。。。");
                user10.setId(6L);
                userDao.deleteUser(user10);
                break;

            case R.id.btn_del_List_ORMLiteAct:
                Toast.makeText(ORMLiteActivity.this, "点击了~", Toast.LENGTH_SHORT).show();
                List<User> userList=new ArrayList<>();

                User user11 = new User("张三","大连人");user11.setId(1L);
                User user12 = new User("Tom","加州人");user12.setId(3L);
                User user13 = new User("张三","大连人");user13.setId(4L);
                User user14 = new User("孙先生","台湾人");user14.setId(5L);
                userList.add(user11);
                userList.add(user12);
                userList.add(user13);
                userList.add(user14);

                userDao.deleteMuUser(userList);

                break;

            case R.id.btn_del_ListID_ORMLiteAct:
                Toast.makeText(ORMLiteActivity.this, "点击了！", Toast.LENGTH_SHORT).show();

                List<Long> ids=new ArrayList<>();
                ids.add(2L);
                ids.add(6L);

                userDao.deleteUserByIDs(ids);
                break;

            case R.id.btn_QueryBuilder_ORMLiteAct:
                Toast.makeText(ORMLiteActivity.this, "点击了！", Toast.LENGTH_SHORT).show();
                List<User> list = userDao.queryBuilder1();
                Logger.d("查询条件一",""+list.toString());
                break;

            case R.id.btn_QueryBuilder2_ORMLiteAct:
                Toast.makeText(ORMLiteActivity.this, "点击了！", Toast.LENGTH_SHORT).show();
                List<User> list2 = userDao.queryBuilder2();
                Logger.d("查询条件二",""+list2.toString());
                break;

            case R.id.btn_orderBy_ORMLiteAct:
                Toast.makeText(ORMLiteActivity.this, "点击了！", Toast.LENGTH_SHORT).show();
                List<User> list3 = userDao.queryBuilder3();
                Logger.d("排序",""+list3.toString());
                break;
        }
    }
}
