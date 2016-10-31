package com.my.simplesampletest.orientation_rc_view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.orientation_rc_view.self_net.OnYJHCallBack;
import com.my.simplesampletest.orientation_rc_view.self_net.YJHHttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 横向的RecyclerView
 * 动态改变组件的宽高（根据手机屏幕的宽高）
 * 下面的布局是自己封装的网络请求
 * <p/>
 * Created by YJH on 2016/10/17.
 */
public class OrientationRCViewAct extends BaseActivity implements RecyclerViewAdapter.RcItemOnClickListener, View.OnClickListener, AdapterView.OnItemClickListener {

    /**
     * 下面组件是横向滑动的RecyclerView用到的
     */
    private final String TAG_SRCEEN = "屏幕的宽高--->";
    private RecyclerView rcView;
    private RecyclerViewAdapter adapter;
    private List<String> data;
    private LinearLayoutManager manager;
    private int screenWidth;
    private int screenHeight;

    /**
     * 下面组件是横行ListView用到的的
     */
    private ListAdapter adapterLV;
    private HorizontalListView lv_HorizontalRC;

    /**
     * 下面的组件是自己写的网络请求用到的
     */
    private TextView tv_Result;
    private Button btn_LoadGet;
    private Button btn_LoadPost;
    private Button btn_LoadBitmap;
    private ImageView iv;
    private String URL_GET = "https://www.baidu.com/";
    private String URL_POST = "http://apis.juhe.cn/mobile/get";    //查询手机号码归属地的API
    private String URL_IMAGE = "http://img.jrjimg.cn/2010/10/20101015125850689.jpg";  //图片
    private final int CODE = 100;
    private Handler mHandler;
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_rcview);

        initView();
        initData();
    }

    @Override
    public void initView() {
        tv_Result = (TextView) findViewById(R.id.tv_Result);
        btn_LoadGet = (Button) findViewById(R.id.btn_LoadGet);
        btn_LoadPost = (Button) findViewById(R.id.btn_LoadPost);
        btn_LoadBitmap = (Button) findViewById(R.id.btn_LoadBitmap);
        iv = (ImageView) findViewById(R.id.iv);

        btn_LoadGet.setOnClickListener(this);
        btn_LoadPost.setOnClickListener(this);
        btn_LoadBitmap.setOnClickListener(this);
    }

    @Override
    public void initData() {
        falseData();
        getScreenInfo();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case CODE:
                        tv_Result.setText(msg.getData().getString(URL_GET));
                        break;
                }
                return false;
            }
        });
        initRecyclerView();
        initListView(screenWidth, screenHeight);
    }

    @Override
    public void onItemClick(View view, final int position) {
        Toast.makeText(OrientationRCViewAct.this, "" + data.get(position), Toast.LENGTH_SHORT).show();
        //跳转到指定的position，第二个参数偏移量，屏幕1/3做偏移量
        manager.postOnAnimation(new Runnable() {
            @Override
            public void run() {
                manager.scrollToPositionWithOffset(position, (int) (0.333333 * getWindowManager().getDefaultDisplay().getWidth()));
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        Toast.makeText(this, "" + data.get(position), Toast.LENGTH_SHORT).show();
        setStatus(position);
        lv_HorizontalRC.postDelayed(new Runnable() {
            @Override
            public void run() {
                lv_HorizontalRC.scrollTo((position * (screenWidth / 3)) - (screenWidth / 3));
            }
        }, 300);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_LoadGet:
                getLoad();
                break;

            case R.id.btn_LoadPost:
                postLoad();
                break;

            case R.id.btn_LoadBitmap:
                loadImage();
                break;
        }
    }

    /**
     * get请求
     */
    private void getLoad() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                YJHHttpRequest.getResultAsGet(URL_GET, new getResultCallBack());
//                String result = YJHHttpRequest.getResultAsGet(URL_GET, "");
//                Log.w(TAG, ",get请求" + result);
//                if (!result.equals("")) {
//                    Bundle mBundle = new Bundle();
//                    mBundle.putString(URL_GET, result);
//                    Message msg = new Message();
//                    msg.setData(mBundle);
//                    msg.what = CODE;
//                    mHandler.sendMessage(msg);
//                }
            }
        }).start();
    }

    /**
     * post请求
     */
    private void postLoad() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("key", "5b20adf6f27bd3e78c9e5b05ffdabac2");
                param.put("dtype", "json");
                param.put("phone", "15104467870");
                YJHHttpRequest.getResultAsPost(URL_POST, param, new getResultCallBack());
            }
        }).start();
    }

    /**
     * 加载图片
     */
    private void loadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                YJHHttpRequest.getNetImage(URL_IMAGE, new getResultCallBack());
            }
        }).start();
    }


    /**
     * 请求结果回调
     */
    private class getResultCallBack implements OnYJHCallBack {

        @Override
        public void onStart() {
            Log.d(TAG, "onStart");
        }


        @Override
        public void onSuccess(String result, final Bitmap bitmap) {
            Log.d(TAG, "onSuccess");

            if (result != null && !result.equals("")) {
                Bundle mBundle = new Bundle();
                mBundle.putString(URL_GET, result);
                Message msg = new Message();
                msg.setData(mBundle);
                msg.what = CODE;
                mHandler.sendMessage(msg);
            }

            if (bitmap != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageBitmap(bitmap);
                    }
                });
            }
        }

        @Override
        public void onFailed(int errorCode) {
            Log.d(TAG, "onFailed");
        }

        @Override
        public void onError() {
            Log.d(TAG, "onError");
        }
    }

    /**
     * 对RecyclerView初始化以及操作
     */
    private void initRecyclerView() {
        rcView = (RecyclerView) findViewById(R.id.rcView);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcView.setLayoutManager(manager);

        adapter = new RecyclerViewAdapter(this, data, screenWidth, screenHeight);
        rcView.setAdapter(adapter);
        adapter.setRcItemOnClickListener(this);

        //动态设置组件的宽高
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, (int) (0.08 * screenHeight));
        rcView.setLayoutParams(param);
        onItemClick(rcView, 0);
        adapter.setStatus(0);
    }

    /**
     * 对ListView初始化以及操作
     *
     * @param screenWidth  屏幕宽度
     * @param screenHeight 屏幕高度
     */
    private void initListView(int screenWidth, int screenHeight) {
        lv_HorizontalRC = (HorizontalListView) findViewById(R.id.lv_HorizontalRC);
        lv_HorizontalRC.setOnItemClickListener(this);

        adapterLV = new ListAdapter(this, data, screenWidth, screenHeight);
        lv_HorizontalRC.setAdapter(adapterLV);

        //动态设置组件的宽高
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, (int) (0.08 * screenHeight));
        lv_HorizontalRC.setLayoutParams(param);

        onItemClick(null, null, 0, 0);
        setStatus(0);
    }

    /**
     * 设置选中的状态
     *
     * @param position 选中的位置
     */
    public void setStatus(int position) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            if (i == position) {
                map.put(i, true);
            } else {
                map.put(i, false);
            }
        }
        adapterLV.setMap(map);
    }

    /**
     * 获取屏幕的宽高
     */
    private void getScreenInfo() {
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        Log.d(TAG_SRCEEN, "宽度=" + screenWidth + ",高度=" + screenHeight);
    }

    /**
     * 假数据
     */
    private void falseData() {
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
    }
}
