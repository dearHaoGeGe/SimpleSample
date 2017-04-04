package com.my.simplesampletest.disable_other_app;

import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by YJH on 2017/4/4 20:59.
 */

public class MonitorService extends Service {

    private Timer mTimer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getTopApp();
            }
        };
        mTimer.schedule(task, 1000, 500);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mTimer.cancel();
        super.onDestroy();
    }

    private void getTopApp() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        //查询2S内的使用统计
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time - 2000, time);

        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            //获取List中最后一个的包名
            String topPackageName = usageStatsList.get(usageStatsList.size() - 1).getPackageName();
            if (getLauncherPackageName(this).equals(topPackageName) || "com.my.simplesampletest".equals(topPackageName)) {
                return;
            }
            Log.i("TopPackageName", topPackageName);

            //模拟home键点击
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);

            //启动提示页面
            Intent intent1 = new Intent(this, DisableAPPTipActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        }
    }

    private String getLauncherPackageName(Context context) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo res = context.getPackageManager().resolveActivity(intent, 0);
        if (res.activityInfo == null) {
            return "";
        }
        if (res.activityInfo.packageName.equals("android")) {
            return "";
        } else {
            return res.activityInfo.packageName;
        }
    }
}
