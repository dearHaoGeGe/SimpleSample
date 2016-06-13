package com.my.simplesampletest.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Picasso工具类
 * <p>
 * Created by YJH on 2016/6/12.
 */
public class PicassoUtils {

    /**
     * 加载图片设置大小
     *
     * @param context
     * @param url
     * @param width
     * @param height
     * @param imageView
     */
    public static void loadImageWithSize(Context context, String url, int width, int height, ImageView imageView) {
        Picasso.with(context).load(url).resize(width, height).centerCrop().into(imageView);
    }

    /**
     * 正在加载的时候默认的图片填充
     *
     * @param context
     * @param url
     * @param resID
     * @param imageView
     */
    public static void loadImageWithHodler(Context context, String url, int resID, ImageView imageView) {
        Picasso.with(context).load(url).fit().placeholder(resID).into(imageView);
    }

    /**
     * 裁剪图片
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void loadImageWithCrop(Context context, String path, ImageView imageView) {
        Picasso.with(context).load(path).transform(new CropSquareTransformation()).into(imageView);
    }

    /**
     * 实现对图片的自定义裁剪
     */
    public static class CropSquareTransformation implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != null) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "square()";
        }
    }
}
