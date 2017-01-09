package com.my.simplesampletest.observer_mode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.observer_mode.sample.Weather;

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

    private final String TAG = getClass().getSimpleName() + "--->";
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
        observerDemo();
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
        switch (v.getId()) {
            case R.id.btn_observer:
                asyncLoadPic();
                break;
        }
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

    /**
     * 异步加载图片
     */
    private void asyncLoadPic() {
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

    /**
     * 观察者模式
     */
    private void observerDemo() {
        com.my.simplesampletest.observer_mode.sample.Observable<Weather> observable = new com.my.simplesampletest.observer_mode.sample.Observable();
        com.my.simplesampletest.observer_mode.sample.Observer<Weather> observer1 = new Observer1();
        com.my.simplesampletest.observer_mode.sample.Observer<Weather> observer2 = new Observer2();

        observable.register(observer1);
        observable.register(observer2);

        observable.notifyObservers(new Weather("晴转多云"));

        observable.notifyObservers(new Weather("多云转阴"));

        observable.unregister(observer1);
        observable.unregister(observer2);

        observable.notifyObservers(new Weather("台风"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //用完了要移除
        observablerable.deleteObserver(this);
    }

    private class Observer1 implements com.my.simplesampletest.observer_mode.sample.Observer<Weather> {

        @Override
        public void onUpdate(com.my.simplesampletest.observer_mode.sample.Observable<Weather> observable, Weather data) {
            Log.e(TAG, "观察者1：" + data.toString());
        }
    }

    private class Observer2 implements com.my.simplesampletest.observer_mode.sample.Observer<Weather> {
        @Override
        public void onUpdate(com.my.simplesampletest.observer_mode.sample.Observable<Weather> observable, Weather data) {
            Log.e(TAG, "观察者2：" + data.toString());
        }
    }
}
