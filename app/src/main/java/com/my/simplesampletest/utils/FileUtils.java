package com.my.simplesampletest.utils;

import android.os.Environment;
import android.util.Log;

import com.my.simplesampletest.pic_save_sd.SaveAndReadPic;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 保存文件到SD卡
 * <p>
 * Created by YJH on 2017/3/4 13:55.
 */

public class FileUtils {

    /**
     * 把String类型已txt的形式保存在SD卡中
     *
     * @param content 需要保存的内容
     */
    public static void saveStringFile(String content) {

        if (!SaveAndReadPic.hasSDCard()) {
            Log.e("FileUtils", "没有SD卡保存失败");
            return;
        }

        //txt保存的路径
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/YJH" + System.currentTimeMillis() + "hello.txt";
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
