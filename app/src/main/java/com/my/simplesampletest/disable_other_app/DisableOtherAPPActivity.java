package com.my.simplesampletest.disable_other_app;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.utils.ToastUtil;

import java.util.Calendar;
import java.util.List;

/**
 * Created by YJH on 2017/4/4 18:00.
 */

public class DisableOtherAPPActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_open_permission;
    private Button btn_open_service;
    private Button btn_stop_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MenuTextAllCaps);
        setContentView(R.layout.activity_disable_other_app);
        setToolbar("禁用其他APP", true, 0);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_open_permission = (Button) findViewById(R.id.btn_open_permission);
        btn_open_service = (Button) findViewById(R.id.btn_open_service);
        btn_stop_service = (Button) findViewById(R.id.btn_stop_service);

        btn_open_permission.setOnClickListener(this);
        btn_open_service.setOnClickListener(this);
        btn_stop_service.setOnClickListener(this);
    }

    @Override
    public void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getHistoryApps();
        } else {
            btn_open_permission.setEnabled(false);
            btn_open_service.setEnabled(false);
            btn_stop_service.setEnabled(false);
            ToastUtil.showToast(this, "当前系统为：" + Build.VERSION.SDK_INT + "，系统必须 >= Android 5.0 (21)");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_permission:
                openPermission();
                break;

            case R.id.btn_open_service:
                startService(new Intent(this, MonitorService.class));
                ToastUtil.showToast(this, "服务已开启！");
                break;

            case R.id.btn_stop_service:
                stopService(new Intent(this, MonitorService.class));
                ToastUtil.showToast(this, "服务已关闭！");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (!checkPermission()) {
                ToastUtil.showToast(this, "权限未开启！");
            } else {
                ToastUtil.showToast(this, "权限已开启！");
            }
        }
    }

    /**
     * 获取此设备从当前时间向前一年内使用过的app的包名集合（其中包括系统级别的）
     */
    private void getHistoryApps() {
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();  //获取当前时间
        calendar.add(Calendar.YEAR, -1);    //日期以年的形式减去一年
        long startTime = calendar.getTimeInMillis();  //获取时间

        //从系统服务中获取UsageStatsManager(使用统计管理者)
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        //获得了过去一年手机上使用过的app的包名集合（其中包括系统级别的）
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_YEARLY, startTime, endTime);

        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            for (UsageStats stats : usageStatsList) {
                Log.i("最近一年使用过", stats.getPackageName());
            }
        }
    }

    /**
     * 检查权限是否允许
     *
     * @return boolean
     */
    private boolean checkPermission() {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    /**
     * 去请求权限
     */
    private void openPermission() {
        if (!checkPermission()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivityForResult(intent, 100);
        } else {
            Toast.makeText(this, "权限已开启！", Toast.LENGTH_SHORT).show();
        }
    }
}
