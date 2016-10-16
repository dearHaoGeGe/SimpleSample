package com.my.simplesampletest.pic_save_sd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.utils.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 调取相机拍照保存在本地，并且显示出来
 * 从SD卡中 读取Bitmap、保存Bitmap、删除Bitmap、删除文件夹
 * <p/>
 * Created by YJH on 2016/10/16.
 */
public class PicSaveSDActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "结果--->";
    private Button btn_savePic_PicSaveSDAct;
    private Button btn_setPic_PicSaveSDAct;
    private Button btn_delPic_PicSaveSDAct;
    private Button btn_delDir_PicSaveSDAct;
    private Button btn_TakePic_PicSaveSDAct;
    private ImageView iv_pic_PicSaveSDAct;
    private String currentTime;
    private static int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_save_sd);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_savePic_PicSaveSDAct = (Button) findViewById(R.id.btn_savePic_PicSaveSDAct);
        btn_setPic_PicSaveSDAct = (Button) findViewById(R.id.btn_setPic_PicSaveSDAct);
        btn_delPic_PicSaveSDAct = (Button) findViewById(R.id.btn_delPic_PicSaveSDAct);
        btn_delDir_PicSaveSDAct = (Button) findViewById(R.id.btn_delDir_PicSaveSDAct);
        btn_TakePic_PicSaveSDAct = (Button) findViewById(R.id.btn_TakePic_PicSaveSDAct);
        iv_pic_PicSaveSDAct = (ImageView) findViewById(R.id.iv_pic_PicSaveSDAct);
    }

    @Override
    public void initData() {
        btn_savePic_PicSaveSDAct.setOnClickListener(this);
        btn_setPic_PicSaveSDAct.setOnClickListener(this);
        btn_delPic_PicSaveSDAct.setOnClickListener(this);
        btn_delDir_PicSaveSDAct.setOnClickListener(this);
        btn_TakePic_PicSaveSDAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_savePic_PicSaveSDAct:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.health);
                if (SaveAndReadPic.savePic(bitmap, "sss")) {
                    ToastUtil.showToast(this, "保存成功~");
                } else {
                    ToastUtil.showToast(this, "保存失败~");
                }
                break;

            case R.id.btn_TakePic_PicSaveSDAct:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File outDir = new File(SaveAndReadPic.outPath);
                if (!outDir.exists()) {
                    outDir.mkdirs();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                File outFile = new File(outDir, sdf.format(new Date(System.currentTimeMillis())) + ".jpg");
                currentTime = outFile.getAbsolutePath();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
                startActivityForResult(intent, REQUEST_CODE);
                break;

            case R.id.btn_setPic_PicSaveSDAct:
                if (null == SaveAndReadPic.readPic("sss")) {
                    Toast.makeText(this, "Bitmap为空~", Toast.LENGTH_SHORT).show();
                }
                iv_pic_PicSaveSDAct.setImageBitmap(SaveAndReadPic.readPic("sss"));
                break;

            case R.id.btn_delPic_PicSaveSDAct:
                if (SaveAndReadPic.delPic("sss")) {
                    Toast.makeText(this, "删除成功~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "删除失败~", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_delDir_PicSaveSDAct:
                if (SaveAndReadPic.delDir(SaveAndReadPic.outPath)) {
                    Toast.makeText(this, "删除文件夹成功~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "删除文件夹失败~", Toast.LENGTH_SHORT).show();
                }
                SaveAndReadPic.RecursionDeleteFile(new File(SaveAndReadPic.outPath));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "请求码=" + requestCode + "     结果码=" + resultCode);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            iv_pic_PicSaveSDAct.setImageBitmap(SaveAndReadPic.BitmapToSmaill(BitmapFactory.decodeFile(currentTime), 600));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "onNewIntent", Toast.LENGTH_SHORT).show();
    }
}
