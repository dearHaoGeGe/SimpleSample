package com.my.simplesampletest.read_config_properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.zxing.common.StringUtils;
import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 读取assets文件夹下的config.properties文件
 * <p/>
 * Created by YJH on 2016/6/29.
 */
public class ConfigPropertiesAct extends BaseActivity implements View.OnClickListener {

    private Button btn_ConfigPropertiesAct;
    private static Properties properties;
    public static final String TAG = "ConfigPropertiesAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_properties);
        setTitle("读取config.properties文件");

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_ConfigPropertiesAct = (Button) findViewById(R.id.btn_ConfigPropertiesAct);
        btn_ConfigPropertiesAct.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ConfigPropertiesAct:
                properties = getProperties(this, "config.properties");
                Toast.makeText(ConfigPropertiesAct.this, ""+getProperty("app.cc"), Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * 读取到assets文件下的config.properties文件
     *
     * @param context
     * @param propertiesFileName
     * @return
     */
    private static Properties getProperties(Context context, String propertiesFileName) {
        Properties propterties = new Properties();
        InputStream input = null;
        try {
            AssetManager assetManager = context.getAssets();
            input = assetManager.open(propertiesFileName);
            propterties.load(input);
        } catch (IOException e) {
            Log.e(TAG, "读取【/assets/" + propertiesFileName + "】出现错误", e);
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return propterties;
    }

    /**
     * 获取到Key所对应的Values（不能要用中文，乱码，要转码）
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        String value = null;
        if (key != null && !key.equals("")) {
            value = properties.getProperty(key);
        }
        return value;
    }
}
