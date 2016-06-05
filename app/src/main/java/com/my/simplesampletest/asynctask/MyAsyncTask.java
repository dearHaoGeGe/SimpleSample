package com.my.simplesampletest.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by YJH on 2016/6/5.
 */
public class MyAsyncTask extends AsyncTask<Void,Integer,Boolean> {

    private Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("下载进度");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        HttpURLConnection conn=null;

        return null;
    }
}
