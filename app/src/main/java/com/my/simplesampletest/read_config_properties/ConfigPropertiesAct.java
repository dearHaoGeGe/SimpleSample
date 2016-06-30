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
import com.orhanobut.logger.Logger;

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

    /**
     * alt+f8 debug时选中查看值
     * f8相当于eclipse的f6跳到下一步
     * shift+f8相当于eclipse的f8跳到下一个断点，也相当于eclipse的f7跳出函数
     * f7相当于eclipse的f5就是进入到代码
     * alt+shift+f7这个是强制进入代码
     * ctrl+shift+f9 debug运行java类
     * ctrl+shift+f10正常运行java类
     * command+f2停止运行
     */
    @Override
    public void initData() {

        int a = 0;
        a = a + 1;
        a = a + 2;
        a = a + 3;
        a = a + 4;
        a = a + 5;
        a = a + 6;
        a = a + 7;
        a = a + 8;
        a = a + 9;
        Log.d(TAG, a + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ConfigPropertiesAct:
                properties = getProperties(this, "config.properties");
                Toast.makeText(ConfigPropertiesAct.this, "" + getProperty("app.cc"), Toast.LENGTH_SHORT).show();
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
