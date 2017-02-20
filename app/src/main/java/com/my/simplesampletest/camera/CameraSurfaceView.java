package com.my.simplesampletest.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.my.simplesampletest.pic_save_sd.SaveAndReadPic;
import com.my.simplesampletest.utils.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 自定义相机
 * <p>
 * Created by YJH on 2017/2/19 16:01.
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.AutoFocusCallback {

    private final String TAG = getClass().getSimpleName() + "--->";
    private final int PREVIEW = 1;
    private final int PICTURE = 2;
    private Context mContext;
    private SurfaceHolder mHolder;
    private Camera mCamera;

    private int mScreenWidth;
    private int mScreenHeight;

    public CameraSurfaceView(Context context) {
        super(context);
        this.mContext = context;
        getScreenMetrix(context);
        initView();
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        getScreenMetrix(context);
        initView();
    }

    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        getScreenMetrix(context);
        initView();
    }


    private void initView() {
        mHolder = getHolder();    //获得surfaceHolder引用
        mHolder.addCallback(this);  ////surfaceHolder的回调
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);   //设置类型
    }

    //****************************** addCallback(this)开始 *******************************
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated()");
        if (null == mCamera) {
            mCamera = getCamera(); //获取Camera对象
            try {
                mCamera.setPreviewDisplay(holder);//摄像头画面显示在Surface上
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged()");
        setCameraParams(mScreenWidth, mScreenHeight);   //设置参数
        mCamera.startPreview(); //Camera开始预览
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceDestroyed()");
        releaseCamera();    //释放Camera资源
    }
    //****************************** addCallback(this)结束 *******************************

    /**
     * 设置Camera的参数
     * Camera.Size：图像大小(宽度和高度尺寸)。
     */
    private void setCameraParams(int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();  //获取camera的参数

        //获取摄像头支持图片大小的列表
        List<Camera.Size> pictureSizeList = parameters.getSupportedPictureSizes();
        for (Camera.Size size : pictureSizeList) {
            Log.i(TAG, "摄像头支持PictureSize的列表：width = " + size.width + "，height = " + size.height);
        }

        //从图片列表中选取合适的分辨率
        Camera.Size picSize = getProperSize(pictureSizeList, (float) (height / width), parameters, PICTURE);
        //设置照片的大小
        parameters.setPictureSize(picSize.width, picSize.height);

        //根据选出的PictureSize重新设置SurfaceView大小
        float w = picSize.width;
        float h = picSize.height;
        this.setLayoutParams(new FrameLayout.LayoutParams((int) (height * (h / w)), height));

        // 获取摄像头支持的PreviewSize列表
        List<Camera.Size> previewSizeList = parameters.getSupportedPreviewSizes();
        for (Camera.Size size : previewSizeList) {
            Log.i(TAG, "摄像头支持PreviewSize的列表：width = " + size.width + "，height = " + size.height);
        }
        //从预览列表中选取合适的分辨率
        Camera.Size preSize = getProperSize(previewSizeList, (float) (height / width), parameters, PREVIEW);

        //设置照片的大小
        parameters.setPreviewSize(preSize.width, preSize.height);

        //设置照片质量
        parameters.setJpegQuality(100);

        //如果相机支持连续自动对焦就连续自动对焦
        if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续对焦模式
        }

        //mCamera.cancelAutoFocus();     //取消自动对焦。
        //设置PreviewDisplay的方向，效果就是将捕获的画面旋转多少度显示(默认的是横向的，所以要旋转90度)
        mCamera.setDisplayOrientation(90);
        mCamera.setParameters(parameters);
    }


    /**
     * 从摄像头支持图片大小的列表中选取合适分辨率（默认w:h=4:3）
     * <p>
     * 注意：因为摄像头默认的是横向的,所以这里的,
     * w对应的是屏幕的height,
     * h对应的是屏幕的Width.
     * <p>
     * 首先遍历pictureSizeList从中找到列表中宽高比率和当前屏幕的宽高比率相同的然后返回，
     * 如果pictureSizeList中的宽高比率没有和当前屏幕的宽高比率相同的，
     * 就从pictureSizeList中找到宽高比率为4:3的返回,
     * 如果pictureSizeList中没有宽高比率为4:3的，就直接从Camera参数中直接获取默认的返回.
     *
     * @param cameraSizeList 摄像头所支持图片大小的列表
     * @param screenRatio    手机屏幕宽高的比例
     * @param parameters     Camera的参数
     * @param mode           判断是想要获取PictureSize还是PreviewSize
     * @return Camera.Size：图像大小(宽度和高度尺寸)
     */
    private Camera.Size getProperSize(List<Camera.Size> cameraSizeList, float screenRatio, Camera.Parameters parameters, int mode) {
        Camera.Size result = null;
        for (Camera.Size size : cameraSizeList) {
            float currentRatio = (float) (size.width / size.height);
            if (currentRatio == screenRatio) {
                result = size;
                return result;
            }
        }

        for (Camera.Size size : cameraSizeList) {
            float currentRatio = (float) (size.width / size.height);
            if (currentRatio == (4f / 3f)) {
                result = size;
                return result;
            }
        }

        if (mode == PICTURE) {
            result = parameters.getPictureSize();
            Log.i(TAG, "PictureSize中没有和屏幕比率相同的、没有4:3的、直接从参数中获取的图片大小：width = " + result.width + "，height = " + result.height);
            return result;
        } else {
            result = parameters.getPreviewSize();
            Log.i(TAG, "PreviewSize中没有和屏幕比率相同的、没有4:3的、直接从参数中获取的图片大小：width = " + result.width + "，height = " + result.height);
            return result;
        }

    }

    public void takePicture() {
        //设置参数,并拍照
        setCameraParams(mScreenWidth, mScreenHeight);
        // 当调用camera.takePiture方法后，camera关闭了预览，这时需要调用startPreview()来重新开启预览
        mCamera.takePicture(null, null, jpeg);
    }

    // 拍照瞬间调用
    private Camera.ShutterCallback shutter = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            Log.i(TAG, "shutter");
        }
    };

    // 获得没有压缩过的图片数据
    private Camera.PictureCallback raw = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera Camera) {
            Log.i(TAG, "raw");

        }
    };

    //创建jpeg图片回调数据对象（注释掉的是自己写的保存图片的方法，自己2692毫秒，demo1765毫秒，暂时用demo的方法保存图片）
    private Camera.PictureCallback jpeg = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera Camera) {
            BufferedOutputStream bos = null;
            Bitmap bm = null;
            try {
                //判断手机是否装载SD卡
                if (SaveAndReadPic.hasSDCard()) {
                    Log.e("XXX--->拍照开始：", "" + System.currentTimeMillis());

//                    // 获得图片
//                    bm = BitmapFactory.decodeByteArray(data, 0, data.length);
//                    bm = SaveAndReadPic.rotatePic(bm, 90);
//                    SaveAndReadPic.savePic(bm, "CameraDemo", "YJH" + System.currentTimeMillis());

                    bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                    String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/YJH" + System.currentTimeMillis() + ".jpg";//照片保存路径
                    File file = new File(filePath);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    bos = new BufferedOutputStream(new FileOutputStream(file));
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流中


                    ToastUtil.showToast(mContext, "拍照成功！");
                } else {
                    ToastUtil.showToast(mContext, "没有检测到内存卡");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                if (null != bm) {
//                    bm.recycle();// 回收bitmap空间
//                }
//                mCamera.stopPreview();// 关闭预览
//                mCamera.startPreview();// 开启预览

                try {
                    bos.flush();//输出
                    bos.close();//关闭
                    bm.recycle();// 回收bitmap空间
                    mCamera.stopPreview();// 关闭预览
                    mCamera.startPreview();// 开启预览
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e("XXX--->拍照结束：", "" + System.currentTimeMillis());
            }

        }
    };

    public void setAutoFocus() {
        getCamera().autoFocus(this);
    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        if (success) {
            Log.i(TAG, "自动对焦成功!");
        }
    }

    /**
     * 获取Camera对象
     *
     * @return Camera
     */
    private Camera getCamera() {
        if (null != mCamera) {
            return this.mCamera;
        }
        Camera camera = null;
        try {
            camera = Camera.open();     //开启相机
        } catch (Exception e) {
            ToastUtil.showToast(mContext, "相机打开失败！");
            Log.e(TAG, "getCamera()相机打开失败！", e);
        }
        return camera;
    }

    /**
     * 释放Camera资源
     */
    private void releaseCamera() {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);   //将相机的回调置空
            mCamera.stopPreview();  //取消掉相机的取景功能
            mCamera.release();      //释放Camera资源
            mCamera = null;
        }
    }

    private void getScreenMetrix(Context context) {
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
    }


}
