package com.my.simplesampletest.pic_save_sd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.text.TextPaint;
import android.util.Log;

import com.my.simplesampletest.utils.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    public static String APP_FOLDER_NAME = "SimpleSample";

    /**
     * 保存Bitmap图片在SD卡中的 APP_FOLDER_NAME 文件夹内
     *
     * @param bitmap   Bitmap
     * @param fileName 想要保存图片的名字
     * @return 是否成功
     */
    public static boolean savePic(Bitmap bitmap, String fileName) {
        return savePic(bitmap, APP_FOLDER_NAME, fileName);
    }

    /**
     * 保存Bitmap图片在SD卡中的 dirName 文件夹内
     *
     * @param bitmap   Bitmap
     * @param dirName  指定文件目录
     * @param fileName Bitmap图片的名称
     * @return 是否成功
     */
    public static boolean savePic(Bitmap bitmap, String dirName, String fileName) {
        boolean isSuccess = true;

        //判断手机是否有SD卡
        if (!hasSDCard()) {
            Log.d(TAG, "手机没有SD卡");
            return false;
        }
        //Log.d(TAG, "手机安装SD卡");

        String outPath = assignSDPath(dirName);
        File outDir = new File(outPath);

        //判断这个文件夹是否存在
        if (!outDir.exists()) {
            outDir.mkdir(); //如果不存在创建一个
        }

        FileOutputStream fops = null;
        try {
            fops = new FileOutputStream(mergePicName(dirName, fileName));    //outPath + "/" + fileName + ".jpg"
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fops);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            if (null != bitmap) {
                bitmap.recycle();
            }
            try {
                if (null != fops) {
                    fops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return isSuccess;
    }

    /**
     * 在手机的SD卡中指定路径
     * 路径：/storage/emulated/0 + "/" + dirName + "/"
     *
     * @param dirName 你想要创建文件的(如果是多层级必须用/隔开)
     *                eg.   demo/file/cache/pic/    (多层级必须这样写)
     * @return SD卡路径+文件
     */
    public static String assignSDPath(String dirName) {
        if (StringUtil.isEmpty(dirName)) {
            throw new NullPointerException("dirName路径不能为空！");
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + dirName + "/";
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
        File dirFile = new File(assignSDPath(APP_FOLDER_NAME));
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
     * 因为无法确定文件的大小，以及数量、嵌套层数
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
     * 合并图片的路径，相当于  /storage/emulated/0 + APP_FOLDER_NAME + "/" + fileName + ".jpg"
     *
     * @param fileName 图片的名字
     * @return 整个的路径名字
     */
    private static String mergePicName(String fileName) {
        return mergePicName(APP_FOLDER_NAME, fileName);
    }

    /**
     * 合并图片的路径，相当于  /storage/emulated/0 + dirName + "/" + fileName + ".jpg"
     *
     * @param dirName  指定文件夹的目录
     * @param fileName 图片的名字
     * @return 整个的路径名字
     */
    private static String mergePicName(String dirName, String fileName) {
        return assignSDPath(dirName) + "/" + fileName + ".jpg";
    }


    /**
     * 为图片在右下角加水印
     *
     * @param src       需要加水印的图片
     * @param watermark 水印 Bitmap
     * @param title     文字
     * @return Bitmap
     */
    public static Bitmap watermarkBitmap(Bitmap src, Bitmap watermark,
                                         String title) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        // 需要处理图片太大造成的内存超过的问题,这里我的图片很小所以不写相应代码了
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
        Paint paint = new Paint();
        // 加入图片
        if (watermark != null) {
            int ww = watermark.getWidth();
            int wh = watermark.getHeight();
            paint.setAlpha(50);
            cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, paint);// 在src的右下角画入水印
        }
        // 加入文字
        if (title != null) {
            String familyName = "宋体";
            Typeface font = Typeface.create(familyName, Typeface.BOLD);
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.WHITE);
            textPaint.setTypeface(font);
            textPaint.setTextSize(22);

            float len = textPaint.measureText(title);
            // 这里是自动换行的
            // StaticLayout layout = new
            // StaticLayout(title,textPaint,w,Alignment.ALIGN_NORMAL,1.0F,0.0F,true);
            // layout.draw(cv);
            cv.drawText(title, w - len - 5, h - 20, textPaint);// 在src的右下角写上文字
            // 文字就加左上角算了
            // cv.drawText(title,0,40,paint);
        }
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        return newb;
    }


    /**
     *  File file = new File(outPath);
     *      if (file.canExecute()) {
     *          Log.v(TAG, "可执行文件");
     *      }
     *      if (file.canRead()) {
     *          Log.v(TAG, "可读");
     *      }
     *      if (file.canWrite()) {
     *          Log.v(TAG, "可写");
     *      }
     */


    /**
     * 将彩色图转换为纯黑白二色图片
     *
     * @param bmp 位图
     * @return 返回转换好的位图
     */
    public static Bitmap convertToBlackWhite(Bitmap bmp) {
        int width = bmp.getWidth(); // 获取位图的宽
        int height = bmp.getHeight(); // 获取位图的高
        int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                //分离三原色
                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                //转化成灰度像素
                grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        //新建图片
        Bitmap newBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        //设置图片数据
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);

        Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(newBmp, 380, 460);
        return resizeBmp;
    }

    /**
     * 设置图片旋转
     *
     * @param mContext   Context
     * @param resourceId 资源id
     * @param degrees    旋转角度
     * @return Bitmap
     */
    public static Bitmap rotatePic(Context mContext, int resourceId, float degrees) {
        Matrix matrix = new Matrix();
        Bitmap bitmap = ((BitmapDrawable) mContext.getResources().getDrawable(resourceId)).getBitmap();
        // 设置旋转角度
        matrix.setRotate(degrees);
        // 重新绘制Bitmap
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 设置图片旋转
     *
     * @param bitmap  Bitmap
     * @param degrees 旋转角度
     * @return Bitmap
     */
    public static Bitmap rotatePic(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        // 设置旋转角度
        matrix.setRotate(degrees);
        // 重新绘制Bitmap
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

}
