package com.my.simplesampletest.add_local_pic;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/7/24.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BaseActivity extends JobService {


    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
