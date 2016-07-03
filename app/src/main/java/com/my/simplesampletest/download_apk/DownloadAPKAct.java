package com.my.simplesampletest.download_apk;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * 调用Android原生的DownloadManager下载文件，自动打开apk
 * http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261781&idx=1&sn=fdaaeec5be125044648bb08ec6b02b7b&scene=0&ptlang=2052&ADUIN=2791077217&ADSESSION=1467533718&ADTAG=CLIENT.QQ.5473_.0&ADPUBNO=26569#wechat_redirect
 * http://www.jianshu.com/p/46fd1c253701
 * 
 * Created by YJH on 2016/7/3.
 */
public class DownloadAPKAct extends BaseActivity implements View.OnClickListener {

    private Button btn_DownloadAPKAct;
    private DownloadManager downloadManager;
    private long mTaskId;
    //广播接受者，接收下载状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };
    //豌豆荚apk
    String downloadUrl="https://sm.wdjcdn.com/release/files/jupiter/5.17.1.12029/wandoujia-web_seo_baidu_homepage.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_apk);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_DownloadAPKAct = (Button) findViewById(R.id.btn_DownloadAPKAct);

        btn_DownloadAPKAct.setOnClickListener(this);
    }

    @Override
    public void initData() {
        downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_DownloadAPKAct:
                Toast.makeText(DownloadAPKAct.this, "333", Toast.LENGTH_SHORT).show();
                downloadAPK(downloadUrl,"WDJ.apk");
                break;
        }
    }

    private void downloadAPK() {
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir("/download/", "WDJ");
        //获取下载管理器
        //DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        downloadManager.enqueue(request);
    }

    //使用系统下载器下载
    private void downloadAPK(String versionUrl, String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);

        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir("/download/", versionName);
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mTaskId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    Logger.i(">>>下载暂停");
                case DownloadManager.STATUS_PENDING:
                    Logger.i(">>>下载延迟");
                case DownloadManager.STATUS_RUNNING:
                    Logger.i(">>>正在下载");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    Logger.i(">>>下载完成");
                    //下载完成安装APK
                    //downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + versionName;
                    installAPK(Environment.getExternalStoragePublicDirectory("/download/WDJ.apk"));
                    break;
                case DownloadManager.STATUS_FAILED:
                    Logger.i(">>>下载失败");
                    break;
            }
        }
    }

    //下载到本地后执行安装
    protected void installAPK(File file) {
        if (!file.exists()){
            Logger.e("文件打开失败！");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
