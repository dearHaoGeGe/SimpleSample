package com.my.simplesampletest.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.my.simplesampletest.R;
import com.zgh.stylelib.style.StyleHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseActivity
 * <p>
 * Created by YJH on 2016/6/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private MPermissionListener mListener;
    private final int M_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** 夜间模式初始化Activity */
        StyleHelper.initActivity(this);
    }

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /** 夜间模式 Destroy Activity */
        StyleHelper.destroyActivity();
    }

    /**
     * 6.0请求运行时权限
     *
     * @param listener    请求结果回调
     * @param permissions 需要请求的权限
     */
    public void requestRuntimePermission(MPermissionListener listener, String... permissions) {
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        //遍历需要申请的所有权限
        for (String permission : permissions) {
            //如果申请的权限中存在没有同意的，添加到List中
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {    //当申请的权限中存在没有同意的，就去系统申请权限
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), M_PERMISSION_REQUEST_CODE);
        } else {    //如果申请的权限都已经同意，直接掉接口
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case M_PERMISSION_REQUEST_CODE:
                List<String> deniedPermissions = new ArrayList<>();
                //遍历所有的授权结果
                for (int i = 0; i < grantResults.length; i++) {
                    //如果没有没有授权的，添加到未授权的List里面
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        deniedPermissions.add(permissions[i]);
                    }
                }

                if (deniedPermissions.isEmpty()) {  //如果未授权的List为空，直接掉全部授权的接口
                    mListener.onGranted();
                } else {    //如果有未授权的，把未授权的List传出去
                    mListener.onDenied(deniedPermissions);
                }
                break;
        }
    }

    /**
     * 设置全屏
     * 要在setContentView()之前调用
     */
    protected void setFullScreen() {
        //继承AppCompatActivity(V7包下的Activity)之后要用这个方法去掉title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置Activity的Toolbar的标题、是否显示返回按钮
     * (此方法必须在setTheme()和setContentView()之后调用)
     *
     * @param title                标题
     * @param isShowNavigationIcon 是否显示返回按钮
     * @param resId                设置Toolbar左边的图标（0的话默认为返回箭头图标）
     */
    protected void setToolbar(String title, boolean isShowNavigationIcon, int resId) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        toolbar.setTitle(title);
        if (resId != 0) {
            toolbar.setNavigationIcon(resId);
        }
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(isShowNavigationIcon);
    }
}
