package com.my.simplesampletest.servicelife;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service的生命周期
 *
 * Created by YJH on 2016/4/17.
 */
public class TestService extends Service {

    private static final String TAG = "TestService";
    private DownloadBinder mBinder=new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

    class DownloadBinder extends Binder{

        public void startDownload(){
            Log.i(TAG,"DownloadBinder_startDownload");
        }

        public int getProgress(){
            Log.i(TAG,"DownloadBinder_getProgress");
            return 0;
        }
    }
}
