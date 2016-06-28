package com.my.simplesampletest.about;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 从MainActivity点击菜单关于
 *
 * Created by YJH on 2016/6/28.
 */
public class AboutActivity extends BaseActivity{

    private WebView webView_AboutAct;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("关于(V"+getVersionName(this)+")");

        initView();
        initData();
    }

    @Override
    public void initView() {
        webView_AboutAct= (WebView) findViewById(R.id.webView_AboutAct);
    }

    @Override
    public void initData() {
        settings=webView_AboutAct.getSettings();
        settings.setJavaScriptEnabled(true);    //如果访问的页面中有Javascript，则WebView必须设置支持Javascript
        settings.setJavaScriptCanOpenWindowsAutomatically(true);    //告诉JavaScript来自动打开的窗口。这适用于JavaScript函数window.open()。默认的是假的。
        settings.setSupportZoom(true);  //支持缩放
        settings.setBuiltInZoomControls(true);  //支持手势操作
        settings.setDisplayZoomControls(false); //是否显示缩放按钮

        // >= 19(SDK4.4)启动硬件加速，否则启动软件加速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            webView_AboutAct.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            settings.setLoadsImagesAutomatically(true);     //支持自动加载图片
        }else {
            webView_AboutAct.setLayerType(View.LAYER_TYPE_HARDWARE,null);
            settings.setLoadsImagesAutomatically(false);     //支持自动加载图片
        }

        settings.setUseWideViewPort(true);      //将图片调整到适合WebView的大小
        settings.setLoadWithOverviewMode(true); //自适应屏幕
        settings.setDomStorageEnabled(true);    //设置是否启用了DOM storage API。默认值false
        settings.setSaveFormData(true);         //WebView是否应该保存表单数据。默认是true
        settings.setSupportMultipleWindows(true);   //WebView是否支持多个窗口,如果设置为真,必须由主机应用程序实现。默认的是false
        settings.setAppCacheEnabled(true);  //是否应该启用应用程序缓存API集。默认的是false。注意,为了使应用程序缓存API启用,还必须提供一个有效的数据库路径
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);    //优先使用缓存

        webView_AboutAct.setHorizontalScrollbarOverlay(true);   //指定水平滚动条是否有叠加样式
        webView_AboutAct.setHorizontalScrollBarEnabled(false);  //定义是否应水平滚动条
        webView_AboutAct.setOverScrollMode(View.SCROLLBARS_INSIDE_OVERLAY); // 取消滚动条白边效果
        webView_AboutAct.requestFocus();    //调用这个尝试将焦点设置到一个特定的视图或它的后代之一

        webView_AboutAct.loadUrl("http://fanyi.baidu.com/#en/zh/sample");
        webView_AboutAct.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    // 获取当前应用的版本号
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            String version = packInfo.versionName;
            if (!TextUtils.isEmpty(version)) {
                return version;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
