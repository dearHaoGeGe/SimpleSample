package com.my.simplesampletest.add_local_pic;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.add_local_pic.common.LocalImageHelper;
import com.my.simplesampletest.add_local_pic.widget.AlbumViewPager;

import java.util.List;

/**
 * 相册详情页
 * <p/>
 * Created by YJH on 2016/7/31.
 */
public class LocalAlbumDetailAct extends BaseActivity {

    private GridView gridView;
    /**
     * 标题
     */
    private TextView title;
    /**
     * 标题栏
     */
    private View titleBar;
    /**
     * 图片显示部分
     */
    private View pagerContainer;
    /**
     * 大图显示pager
     */
    private AlbumViewPager viewPager;
    private TextView finish, headerFinish;
    private String folder;
    private String mCountView;
    List<LocalImageHelper.LocalFile> currentFolder = null;

    private ImageView mBackView;
    private View headerBar;
    private CheckBox checkBox;
    private LocalImageHelper helper = LocalImageHelper.getInstance();
    private List<LocalImageHelper.LocalFile> checkedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_album_detail);
        /**写到这，定义完了全局变量，还没有初始化*/
    }
}
