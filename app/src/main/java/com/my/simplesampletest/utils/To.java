package com.my.simplesampletest.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;

/**
 * Toast工具类,还可以显示Toast图片和自定义布局
 * <p>
 * Created by YJH on 2016/6/8.
 */
public class To {

    private static Toast toast;

    /**
     * 控制是否显示
     */
    private static boolean isShow = true;

    public static void showShort(Context context, CharSequence text) {
        if (isShow) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showLong(Context context, CharSequence text) {
        if (isShow) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 这是一个带图片的吐司,其吐司的显示位置定义在了屏幕正中间-->短吐司
     *
     * @param context 上下文
     * @param message 字符串类型的消息(这个参数如果为空的话图片非常小)
     * @param resId   图片资源ID
     */
    public static void showCustomToastWithImage(Context context, CharSequence message, int resId) {
        if (isShow) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout ToastContentView = (LinearLayout) toast.getView();
            ImageView img = new ImageView(context);
            img.setImageResource(resId);
            ToastContentView.addView(img);
            toast.show();
        }
    }

    /**
     * 这是一个带图片的吐司,其吐司的显示位置定义在了屏幕正中间-->短吐司
     *
     * @param context   上下文
     * @param messageId 字符串资源的ID值
     * @param resId     图片资源ID
     */
    public static void showCustomToastWithImage(Context context, int messageId, int resId) {
        if (isShow) {
            toast = Toast.makeText(context, messageId, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout ToastContentView = (LinearLayout) toast.getView();
            ImageView img = new ImageView(context);
            img.setImageResource(resId);
            ToastContentView.addView(img);
            toast.show();
        }
    }

    /**
     * 这是一个完全自定义的短吐司Toast：所展示出来的视图是自己在res文件夹中自定义的xml布局文件
     *
     * @param context        所在Activity的上下文
     * @param layoutResource 所要加载的XML布局资源文件的ID值
     * @param message        所要通知的文本信息（CharSequence形式）
     * @param bitmap         所要通知的图片的信息（Bitmap形式）
     */

    public static void showCompletedCustomToast(Context context, int layoutResource, CharSequence message, Bitmap bitmap) {
        if (isShow) {
            toast = new Toast(context);
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflate.inflate(layoutResource, null);
            TextView mTextView = (TextView) view.findViewById(R.id.toast_text_content);
            ImageView mImageView = (ImageView) view.findViewById(R.id.toast_image_content);
            mTextView.setText(message);
            mImageView.setImageBitmap(bitmap);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    /**
     * 完全自定义的短吐司：所展示出来的视图是自己在res文件夹中自定义的xml布局文件
     *
     * @param context        所在Activity的上下文
     * @param layoutResource 所要加载的XML布局资源文件的ID值
     * @param message        所要通知的文本信息（CharSequence形式）
     * @param imageId        所要通知的图片的信息（图片资源ID值）
     */
    public static void showCompletedCustomToastWithResId(Context context, int layoutResource, CharSequence message, int imageId) {
        if (isShow) {
            toast = new Toast(context);
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflate.inflate(layoutResource, null);
            TextView mTextView = (TextView) view.findViewById(R.id.toast_text_content);
            ImageView mImageView = (ImageView) view.findViewById(R.id.toast_image_content);
            mTextView.setText(message);
            mImageView.setImageResource(imageId);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }


}
