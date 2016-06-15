package com.my.simplesampletest.imagecache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by YJH on 2016/6/15.
 */
public class DiskLruCacheAct extends BaseActivity implements View.OnClickListener {

    private ImageView iv_DiskLruCacheAct;
    private Button btn_DiskLruCacheAct;
//    private static String url = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
    private static String url="http://img30.360buyimg.com/pop/jfs/t2716/316/2258673876/14087/ec88c0b0/575e4ba7Nb65d19fd.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk_lru_cache);

        initView();
        initData();
    }

    @Override
    public void initView() {
        iv_DiskLruCacheAct = (ImageView) findViewById(R.id.iv_DiskLruCacheAct);
        btn_DiskLruCacheAct = (Button) findViewById(R.id.btn_DiskLruCacheAct);

        btn_DiskLruCacheAct.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_DiskLruCacheAct:
                DiskLruCache mDiskLruCache = open();
                fromSDGetImg(mDiskLruCache);
                Picasso.with(this).load(url).fit().into(iv_DiskLruCacheAct);
                saveImg(mDiskLruCache);


                Toast.makeText(DiskLruCacheAct.this, ""+mDiskLruCache.size(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 保存图片到本地
     */
    private void saveImg(DiskLruCache mDiskLruCache) {
        String key = hashKeyForDisk(url);

        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                editor.commit();
            }
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从本地获取图片
     */
    private void fromSDGetImg(DiskLruCache mDiskLruCache) {
        String key = hashKeyForDisk(url);
        try {
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                iv_DiskLruCacheAct.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DiskLruCache open() {
        DiskLruCache mDiskLruCache = null;
        try {
            File cacheDir = getDiskCacheDir(this, "bitmap");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(this), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mDiskLruCache;
    }

    /**
     * 判断手机是否有SD卡
     * <p/>
     * 当SD卡存在或者SD卡不可被移除的时候，就调用getExternalCacheDir()方法来获取缓存路径，
     * 否则就调用getCacheDir()方法来获取缓存路径。
     * 前者获取到的就是 /sdcard/Android/data/<application package>/cache 这个路径，
     * 而后者获取到的是 /data/data/<application package>/cache 这个路径
     *
     * @param context
     * @param uniqueName 为了对不同类型的数据进行区分而设定的一个唯一值，
     *                   比如说在网易新闻缓存路径下看到的bitmap、object等文件夹
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 地获取到当前应用程序的版本号
     * 每当版本号改变，缓存路径下存储的所有数据都会被清除掉，
     * 因为DiskLruCache认为当应用程序有版本更新的时候，所有的数据都应该从网上重新获取
     *
     * @param context
     * @return
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
