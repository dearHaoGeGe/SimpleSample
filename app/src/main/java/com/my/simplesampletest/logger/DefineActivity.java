package com.my.simplesampletest.logger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.yanzhenjie.recyclerview.swipe.SwipeSwitch;

/**
 * 用SwipeMenuLayout实现你自己的侧滑
 *
 * Created by YJH on 2016/11/30 17:03.
 */
public class DefineActivity extends AppCompatActivity {

    private Activity mContext;

    private TextView mTvContent, mBtnLeft, mBtnRight;

    private SwipeSwitch mSwipeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView(R.layout.activity_define);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeSwitch = (SwipeSwitch) findViewById(R.id.swipe_layout);
        mTvContent = (TextView) findViewById(R.id.content_view);
        mBtnLeft = (TextView) findViewById(R.id.left_view);
        mBtnRight = (TextView) findViewById(R.id.right_view);

        mBtnLeft.setOnClickListener(xOnClickListener);
        mBtnRight.setOnClickListener(xOnClickListener);
    }

    private View.OnClickListener xOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.left_view) {
                mSwipeSwitch.smoothCloseMenu();// 关闭菜单。
                Toast.makeText(mContext, "我是左面的", Toast.LENGTH_SHORT).show();
            } else if (v.getId() == R.id.right_view) {
                mSwipeSwitch.smoothCloseMenu();// 关闭菜单。
                Toast.makeText(mContext, "我是右面的", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}