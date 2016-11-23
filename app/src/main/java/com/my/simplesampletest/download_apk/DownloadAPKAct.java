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
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * 调用Android原生的DownloadManager下载文件，自动打开apk，这个一般写在服务中
 * <p>
 * http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261781&idx=1&sn=fdaaeec5be125044648bb08ec6b02b7b&scene=0&ptlang=2052&ADUIN=2791077217&ADSESSION=1467533718&ADTAG=CLIENT.QQ.5473_.0&ADPUBNO=26569#wechat_redirect
 * http://www.jianshu.com/p/46fd1c253701
 * http://blog.csdn.net/qq_19431333/article/details/52798105
 * <p>
 * 总Context实例个数 = Service个数 + Activity个数 + 1（Application对应的Context实例）
 * <p>
 * Created by YJH on 2016/7/3.
 */
public class DownloadAPKAct extends BaseActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName() + "--->";
    private Button btn_DownloadAPKAct;
    private DownloadManager downloadManager;
    private long mTaskId;
    //豌豆荚apk
    private String downloadUrl = "https://sm.wdjcdn.com/release/files/jupiter/5.17.1.12029/wandoujia-web_seo_baidu_homepage.apk";
    //下载文件的路径
    private String DOWNLOAD_PATH = "/saiz/";
    //下载文件的名字
    private String fileName = "WDJ.apk";
    //广播接受者，接收下载状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };

    private long id;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            query();
        }
    };


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
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_DownloadAPKAct:
                Toast.makeText(DownloadAPKAct.this, "正在下载...", Toast.LENGTH_SHORT).show();
                downloadAPK(downloadUrl, fileName);
                break;
        }
    }


    /**
     * 使用系统下载器下载
     *
     * @param versionUrl  下载地址
     * @param versionName 下载文件名字
     */
    private void downloadAPK(String versionUrl, String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的(现在设置的是下载中和下载完成都显示在状态栏上面)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
        request.setTitle("豌豆荚最新版本正在下载...");
        //限制使用的网络(只让WiFi状态下下载)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

        //sdcard的目录下的download文件夹，必须设置(如果文件夹不存在直接创建，这里 不需要判断文件是否存在)
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, versionName);
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);

        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mTaskId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        //延时是为了能查到数据
        //handler.sendEmptyMessageDelayed(1, 2000);
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
                    installAPK(Environment.getExternalStoragePublicDirectory(DOWNLOAD_PATH + fileName));
                    break;
                case DownloadManager.STATUS_FAILED:
                    Logger.i(">>>下载失败");
                    break;
            }
        }
    }


    /**
     * 下载到本地后执行安装
     *
     * @param file 文件路径
     */
    protected void installAPK(File file) {
        if (!file.exists()) {
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


    /**
     * 查询操作
     */
    private void query() {

        DownloadManager.Query query = new DownloadManager.Query().setFilterById(id);
        Cursor cursor = downloadManager.query(query);

        if (cursor != null) {

            while (cursor.moveToNext()) {

                String bytesDownload = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                String descrition = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_DESCRIPTION));
                String id = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
                String localUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                String mimeType = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_MEDIA_TYPE));
                String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                String status = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                String totalSize = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                Log.i(TAG, "bytesDownload:" + bytesDownload);
                Log.i(TAG, "descrition:" + descrition);
                Log.i(TAG, "id:" + id);
                Log.i(TAG, "localUri:" + localUri);
                Log.i(TAG, "mimeType:" + mimeType);
                Log.i(TAG, "title:" + title);
                Log.i(TAG, "status:" + status);
                Log.i(TAG, "totalSize:" + totalSize);

            }

        }

    }

    /**
     * 暂时没用到
     * <p>
     * 广播接收器，接受ACTION_DOWNLOAD_COMPLETE和ACTION_NOTICATION_CLICKED
     */
    class DownloadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

                Uri uri = downloadManager.getUriForDownloadedFile(id);

                //imageView.setImageURI(uri);

            } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {

                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();

            }

        }
    }


}
