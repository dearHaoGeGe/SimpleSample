package com.my.simplesampletest.orientation_rc_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.simplesampletest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 横向的RecyclerView
 * 动态改变组件的宽高（根据手机屏幕的宽高）
 *
 * Created by YJH on 2016/10/17.
 */
public class OrientationRCViewAct extends AppCompatActivity implements RecyclerViewAdapter.RcItemOnClickListener {

    private final String TAG = "屏幕的宽高--->";
    private RecyclerView rcView;
    private RecyclerViewAdapter adapter;
    private List<String> data;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_rcview);

        rcView = (RecyclerView) findViewById(R.id.rcView);

        data = new ArrayList<>();
        data.add("冰品");
        data.add("常温");
        data.add("液态");
        data.add("加温");
        data.add("酸奶");
        data.add("低温");
        data.add("纯牛奶");
        data.add("高温");
        data.add("固态");

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcView.setLayoutManager(manager);

        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        Log.d(TAG, "宽度=" + screenWidth + ",高度=" + screenHeight);
        //动态设置组件的宽高
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, (int) (0.08 * screenHeight));
        rcView.setLayoutParams(param);

        adapter = new RecyclerViewAdapter(this, data, screenWidth, screenHeight);
        rcView.setAdapter(adapter);

        adapter.setRcItemOnClickListener(this);

        onItemClick(rcView, 0);
        adapter.setStatus(0);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(OrientationRCViewAct.this, "" + data.get(position), Toast.LENGTH_SHORT).show();
        //跳转到指定的position，第二个参数偏移量，屏幕1/3做偏移量
        manager.scrollToPositionWithOffset(position, (int) (0.333333 * getWindowManager().getDefaultDisplay().getWidth()));
    }
}
