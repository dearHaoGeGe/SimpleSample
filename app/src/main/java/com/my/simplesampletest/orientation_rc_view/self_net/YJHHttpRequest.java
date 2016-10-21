package com.my.simplesampletest.orientation_rc_view.self_net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * 自己写的网络请求
 * <p>
 * Created by YJH on 2016/10/20 09:25.
 */

public class YJHHttpRequest {

    private static final String TAG = "HttpRequest网络请求--->";
    private static final String POST = "POST";
    private static final String GET = "GET";
    private static int TIME_OUT = 5000;

    /**
     * 普通的get请求
     *
     * @param path     请求地址
     * @param callBack 结果回调
     */
    public static void getResultAsGet(String path, OnYJHCallBack callBack) {
        String result = "";
        if (path == null || path.equals("")) {
            throw new NullPointerException("请求地址不能为空!!!");
        }
        if (callBack == null) {
            throw new NullPointerException("回调接口不能为空!!!");
        }

        try {
            callBack.onStart();
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(GET);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(TIME_OUT);

            int code = conn.getResponseCode();
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String len = "";
                    while ((len = br.readLine()) != null) {
                        result += len;
                    }
                    callBack.onSuccess(result,null);
                    isr.close();
                    br.close();
                    break;

                default:
                    callBack.onFailed(code);
                    Log.e(TAG, "失败,code = " + code);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG, ",getResultAsGet,走第一个异常了~", e);
            callBack.onError();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, ",getResultAsGet,走第二个异常了~", e);
            callBack.onError();
        }

    }


    /**
     * 普通的post请求
     *
     * @param path  地址
     * @param param 参数
     */
    public static void getResultAsPost(String path, Map<String, String> param, OnYJHCallBack callBack) {
        String result = "";
        if (param.isEmpty()) {
            throw new RuntimeException("POST请求参数不可能为空!!!");
        }
        if (callBack == null) {
            throw new NullPointerException("回调接口不能为空!!!");
        }
        callBack.onStart();
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(POST);
            conn.setUseCaches(false);
            conn.setReadTimeout(TIME_OUT);
            byte[] bytes = getAllParam(param).getBytes("UTF8");
            conn.getOutputStream().write(bytes);

            int code = conn.getResponseCode();
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    String len = null;
                    while ((len = reader.readLine()) != null) {
                        result += len;
                    }
                    callBack.onSuccess(result,null);
                    isr.close();
                    reader.close();
                    break;

                default:
                    Log.e(TAG, "失败,code = " + code);
                    callBack.onFailed(code);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG, ",getResultAsPost,走第一个异常了~", e);
            callBack.onError();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, ",getResultAsPost,走第二个异常了~", e);
            callBack.onError();
        }
    }

    public static void getNetImage(String path, OnYJHCallBack callBack) {
        Bitmap bitmap = null;
        callBack.onStart();
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int code = conn.getResponseCode();
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    callBack.onSuccess(null,bitmap);
                    is.close();
                    break;

                default:
                    Log.e(TAG, "图片-失败,code = " + code);
                    callBack.onFailed(code);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG, ",getNetImage,走第一个异常了~", e);
            callBack.onError();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, ",getNetImage,走第二个异常了~", e);
            callBack.onError();
        }
    }

    /**
     * POST请求传进来的参数进行拼接
     *
     * @param param 参数
     * @return 拼接后的URL
     */
    private static String getAllParam(Map<String, String> param) {
        String url = "";
        for (Map.Entry<String, String> entry : param.entrySet()) {
            url += "&" + entry.getKey() + "=" + entry.getValue();
        }
        Log.e(TAG + "第一次", url);
        StringBuilder sb = new StringBuilder(url);
        sb.delete(0, 1);
        Log.e(TAG + "第二次", sb.toString());
        return sb.toString();
    }
}
