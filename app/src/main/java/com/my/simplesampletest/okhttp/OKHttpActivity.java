package com.my.simplesampletest.okhttp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 学习使用OKHttp
 * http://blog.csdn.net/lmj623565791/article/details/47911083
 *
 * Created by YJH on 2016/6/30.
 */
public class OKHttpActivity extends BaseActivity implements View.OnClickListener {

    private OkHttpClient mOkHttpClient;
    private Button btn_get_OKHttpAct,btn_post_OKHttpAct;
    public static final String TAG="OKHttpActivity----->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_get_OKHttpAct= (Button) findViewById(R.id.btn_get_OKHttpAct);
        btn_post_OKHttpAct= (Button) findViewById(R.id.btn_post_OKHttpAct);

        btn_get_OKHttpAct.setOnClickListener(this);
        btn_post_OKHttpAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mOkHttpClient=new OkHttpClient();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_OKHttpAct:
                oKHttpGetRequest();
                break;

            case R.id.btn_post_OKHttpAct:
                oKHttpPostRequest();
                break;
        }
    }

    /**
     * OKHttp的Get请求
     */
    private void oKHttpGetRequest(){
//        //创建OkHttpClient对象
//        OkHttpClient mOkHttpClient=new OkHttpClient();
        //创建一个Request
        Request request=new Request.Builder().url("http://www.mengxianyi.net/one/question.json").build();
        //new call
        Call call=mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.v(TAG,"请求失败！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //这个回调方法不是在主线程中，如果要在主线程中做事情，需要写这个方法才能在主线程中更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OKHttpActivity.this, "请求成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                if (response.isSuccessful()){
                    Logger.v(TAG,response.body().string());
                }
            }
        });
    }


    /**
     * OKHttp的Post请求
     */
    private void oKHttpPostRequest(){
        FormBody.Builder form_builder=new FormBody.Builder();
        form_builder.add("dtype","json");
        form_builder.add("key","5b20adf6f27bd3e78c9e5b05ffdabac2");
        form_builder.add("phone","15164054795");

        RequestBody request_Body=form_builder.build();
        Request request=new Request.Builder().url("http://apis.juhe.cn/mobile/get").post(request_Body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OKHttpActivity.this, "请求失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OKHttpActivity.this, "请求成功！", Toast.LENGTH_SHORT).show();
                    }
                });

                if (response.isSuccessful()){
                    Log.v(TAG,response.body().string());
                }
            }
        });
    }


}
