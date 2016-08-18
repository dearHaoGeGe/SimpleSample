package com.my.simplesampletest.servicelife;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.my.simplesampletest.R;

/**
 * 测试Service的生命周期
 */
public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private TestService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (TestService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            downloadBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Button btn_mainAct_startService = (Button) findViewById(R.id.btn_serviceAct_startService);
        Button btn_mainAct_stopService = (Button) findViewById(R.id.btn_serviceAct_stopService);
        Button btn_mainAct_bindService = (Button) findViewById(R.id.btn_serviceAct_bindService);
        Button btn_mainAct_unbindService = (Button) findViewById(R.id.btn_serviceAct_unbindService);

        btn_mainAct_startService.setOnClickListener(this);
        btn_mainAct_stopService.setOnClickListener(this);
        btn_mainAct_bindService.setOnClickListener(this);
        btn_mainAct_unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_serviceAct_startService:
                startService(new Intent(this, TestService.class));
                break;

            case R.id.btn_serviceAct_stopService:
                stopService(new Intent(this, TestService.class));
                break;

            case R.id.btn_serviceAct_bindService:
                Intent intent=new Intent(this,TestService.class);
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btn_serviceAct_unbindService:
                unbindService(connection);
                break;
        }
    }
}
