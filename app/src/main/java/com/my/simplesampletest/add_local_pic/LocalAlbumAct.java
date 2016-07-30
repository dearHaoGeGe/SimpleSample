package com.my.simplesampletest.add_local_pic;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.add_local_pic.common.LocalImageHelper;

import java.util.List;

/**
 * 本地相册
 *
 * Created by YJH on 2016/7/29.
 */
public class LocalAlbumAct extends BaseActivity{
    /**
     * 加载进度的图片
     */
    private ImageView progress;
    /**
     * 相机的图片
     */
    private View camera;

    private ListView listView;
    private LocalImageHelper helper;
    private List<String> folderNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_album);
    }
}
