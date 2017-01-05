package com.my.simplesampletest.observer_mode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/**
 * 观察这模式
 * http://mp.weixin.qq.com/s/riK69z6t93QSZXGC2kLYzQ
 * <p>
 * Created by YJH on 2017/1/5 11:31.
 */

public class ObserverActivity extends BaseActivity implements View.OnClickListener, Observer {

    private Button btn_observer;
    private ImageView iv_observer;
    private MyObserverable observablerable;
    private Bitmap bitmap;
    private final String url = "http://cdn.duitang.com/uploads/item/201409/05/20140905015324_zvwG2.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_observer = (Button) findViewById(R.id.btn_observer);
        iv_observer = (ImageView) findViewById(R.id.iv_observer);

        btn_observer.setOnClickListener(this);
    }

    @Override
    public void initData() {
        observablerable = new MyObserverable();
        observablerable.addObserver(this);
    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(ObserverActivity.this.url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(5 * 1000);
                    conn.setConnectTimeout(5 * 1000);
                    bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                    conn.disconnect();

                    observablerable.setChanged();       //设置数据改变
                    observablerable.notifyObservers();  //通知观察者
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void update(Observable observable, Object data) {
        iv_observer.post(new Runnable() {
            @Override
            public void run() {
                iv_observer.setImageBitmap(bitmap);
            }
        });
    }
}
