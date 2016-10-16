package com.my.simplesampletest.pic_save_sd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 保存和读取图片的工具类
 * <p/>
 * Created by YJH on 2016/10/16.
 */
public class SaveAndReadPic {

    private static final String TAG = "保存和读取图片--->";

    /**
     * 文件夹的名称
     */
    private static String APP_FOLDER_NAME = "TakePicDir";
    /**
     * 输出路径(储存图片的文件夹路径)
     */
    public static String outPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + APP_FOLDER_NAME + "/";

    /**
     * 保存Bitmap图片在SD卡中
     *
     * @param bitmap   Bitmap
     * @param fileName 想要保存图片的名字
     * @return 是否成功
     */
    public static boolean savePic(Bitmap bitmap, String fileName) {
        boolean isSuccess = true;

        //判断手机是否有SD卡
        if (!hasSDCard()) {
            Log.d(TAG, "手机没有SD卡");
            return false;
        }
        //Log.d(TAG, "手机安装SD卡");

        File outDir = new File(outPath);
        //判断这个文件夹是否存在
        if (!outDir.exists()) {
            outDir.mkdir(); //如果不存在创建一个
        }

        FileOutputStream fops = null;
        try {
            fops = new FileOutputStream(mergePicName(fileName));    //outPath + "/" + fileName + ".jpg"
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fops);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 从SD中读取图片
     *
     * @param fileName 图片的名字
     * @return Bitmap
     */
    public static Bitmap readPic(String fileName) {
        File bitmapFile = new File(mergePicName(fileName)); // outPath + "/" + fileName + ".jpg"

        //若文件存在
        if (bitmapFile.exists()) {
            return BitmapFactory.decodeFile(mergePicName(fileName));
        } else { //不存在
            Log.d(TAG, "" + fileName + "文件SD中不存在");
            return null;
        }
    }

    /**
     * 删除单张图片
     *
     * @param fileName 图片的名字
     * @return 是否成功
     */
    public static boolean delPic(String fileName) {
        File bitmapFile = new File(mergePicName(fileName)); // outPath + "/" + fileName + ".jpg"
        if (bitmapFile.exists()) {
            return bitmapFile.delete();
        } else {
            Log.d(TAG, fileName + "文件SD中不存在");
            return false;
        }
    }

    /**
     * 删除文件夹，删除文件夹必须要进这个文件夹里面先把子项给删除了，
     * 然后才可以删除当前的文件夹(用这个方法最好开线程，如果删除文件多的话，会阻塞主线程)
     *
     * @param dirName 文件夹的名字
     * @return 是否成功
     * @deprecated 使用 {@link #RecursionDeleteFile(File file)} 代替
     */
    @Deprecated
    public static boolean delDir(String dirName) {
        File dirFile = new File(outPath);
        if (!dirFile.exists()) {
            Log.d(TAG, "删除文件夹，没有" + dirName + "这个文件夹");
            return false;
        } else {
            if (dirFile.isDirectory()) {
                File[] children = dirFile.listFiles();
                for (int i = 0; i < children.length; i++) {
                    boolean b = children[i].delete();
                    Log.d(TAG, i + "、" + b);
                }
            }
            return dirFile.delete();
        }
    }

    /**
     * 递归删除文件，用这个方法尽可能的开线程，不要在主线程中执行，
     * 因为无法确定文件的大小，已经数量、嵌套层数
     *
     * @param file 文件
     */
    public static void RecursionDeleteFile(File file) {
        //如果是文件直接删除
        if (file.isFile()) {
            file.delete();
            return;
        }
        //如果是文件夹获取子项
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            //如果子项没有文件，直接删除
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            //如果有文件，继续掉自己
            for (File f : childFile) {
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 对图片进行等比缩减
     *
     * @param bitmap 需要缩小的图片
     * @param size   大小(值越大图片越大)
     * @return 缩小过的Bitmap
     */
    public static Bitmap BitmapToSmaill(Bitmap bitmap, int size) {
        Bitmap Smallbitmap = null;
        Matrix m = new Matrix();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > size || height > size) {
            float ScrWidth;
            if (width > height) {
                ScrWidth = (float) size / width;
            } else {
                ScrWidth = (float) size / height;
            }
            m.postScale(ScrWidth, ScrWidth); // 长和宽放大缩小的比例
        }
        Smallbitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), m, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Smallbitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Smallbitmap = BitmapFactory.decodeStream(isBm, null, null);
        return Smallbitmap;
    }

    /**
     * 判断手机是否有SD卡
     *
     * @return true为有，false没有
     */
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 合并图片的路径，相当于   outPath + "/" + fileName + ".jpg"
     *
     * @param fileName 图片的名字
     * @return 整个的路径名字
     */
    public static String mergePicName(String fileName) {
        return outPath + "/" + fileName + ".jpg";
    }


//    File file = new File(outPath);
//    if (file.canExecute()) {
//        Log.v(TAG, "可执行文件");
//    }
//    if (file.canRead()) {
//        Log.v(TAG, "可读");
//    }
//    if (file.canWrite()) {
//        Log.v(TAG, "可写");
//    }
}
