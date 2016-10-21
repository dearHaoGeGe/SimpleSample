package com.my.simplesampletest.orientation_rc_view.self_net;

import android.graphics.Bitmap;

/**
 * 请求结果回调
 * 请求图片暂时和请求String用一个接口，
 * 到时候需要在主线程里面更新UI
 * <p>
 * Created by YJH on 2016/10/20 17:42.
 */

public interface OnYJHCallBack {
    /**
     * 准备开始请求
     */
    void onStart();

    /**
     * 请求成功
     *
     * @param result String结果
     * @param bitmap Bitmap结果
     */
    void onSuccess(String result, Bitmap bitmap);

    /**
     * 请求失败
     *
     * @param errorCode 错误码
     */
    void onFailed(int errorCode);

    /**
     * 请求错误
     */
    void onError();
}
