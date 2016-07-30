package com.my.simplesampletest.add_local_pic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.add_local_pic.common.ImageUtils;
import com.my.simplesampletest.add_local_pic.common.LocalImageHelper;
import com.my.simplesampletest.add_local_pic.common.StringUtils;
import com.my.simplesampletest.add_local_pic.widget.AlbumViewPager;
import com.my.simplesampletest.add_local_pic.widget.FilterImageView;
import com.my.simplesampletest.add_local_pic.widget.MatrixImageView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 发布动态的Activity
 * <p/>
 * Created by YJH on 2016/7/28.
 */
public class DynamicPostAct extends BaseActivity implements MatrixImageView.OnSingleTapListener, View.OnClickListener {

    /**
     * 返回键
     */
    private ImageView mBack;
    /**
     * 发送
     */
    private TextView mSend;
    /**
     * 动态内容编辑框
     */
    private EditText mContent;
    /**
     * 软键盘管理
     */
    private InputMethodManager imm;
    /**
     * 动态内容字数数字提醒
     */
    private TextView textRemain;
    /**
     * 图片数量提示
     */
    private TextView picRemain;
    /**
     * 添加图片按钮
     */
    private ImageView add;
    /**
     * 图片容器
     */
    private LinearLayout picContainer;
    /**
     * 图片路径数组
     */
    private List<LocalImageHelper.LocalFile> pictures = new ArrayList<>();
    /**
     * 滚动的图片容器
     */
    private HorizontalScrollView scrollView;
    /**
     * 动态编辑部分
     */
    private View editContainer;
    /**
     * 图片显示部分
     */
    private View pagerContainer;

    //显示大图的viewpager 集成到了Actvity中 下面是和viewpager相关的控件
    /**
     * 大图显示pager
     */
    private AlbumViewPager viewpager;
    /**
     * 返回/关闭大图
     */
    private ImageView mBackView;
    /**
     * 大图数量提示
     */
    private TextView mCountView;
    /**
     * 大图顶部栏
     */
    private View mHeaderBar;
    /**
     * 删除按钮
     */
    private ImageView delete;
    /**
     * 小图大小
     */
    private int size;
    /**
     * 小图间距
     */
    private int padding;

    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_post);
        initImageLoader(this);  //初始化ImageLoader
        //设置ImageLoader的参数
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .showImageForEmptyUri(R.mipmap.dangkr_no_picture_small)
                .showImageOnFail(R.mipmap.dangkr_no_picture_small)
                .showImageOnLoading(R.mipmap.dangkr_no_picture_small)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new SimpleBitmapDisplayer()).build();
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        initView();
        initData();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.post_back);
        mSend = (TextView) findViewById(R.id.post_send);
        mContent = (EditText) findViewById(R.id.post_content);
        textRemain = (TextView) findViewById(R.id.post_text_remain);
        picRemain = (TextView) findViewById(R.id.post_pic_remain);
        add = (ImageView) findViewById(R.id.post_add_pic);
        picContainer = (LinearLayout) findViewById(R.id.post_pic_container);
        scrollView = (HorizontalScrollView) findViewById(R.id.post_scrollview);
        viewpager = (AlbumViewPager) findViewById(R.id.albumviewpager);
        mBackView = (ImageView) findViewById(R.id.header_bar_photo_back);
        mCountView = (TextView) findViewById(R.id.header_bar_photo_count);
        mHeaderBar = findViewById(R.id.album_item_header_bar);
        delete = (ImageView) findViewById(R.id.header_bar_photo_delete);
        editContainer = findViewById(R.id.post_edit_container);
        pagerContainer = findViewById(R.id.viewpager);

        delete.setVisibility(View.VISIBLE);

        viewpager.setOnPageChangeListener(pageChangeListener);
        viewpager.setOnSingleTapListener(this);
        mBackView.setOnClickListener(this);
        mCountView.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSend.setOnClickListener(this);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);

        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textRemain.setText(s.toString().length() + "/140");
            }
        });
    }

    private void initData() {
        size = (int) getResources().getDimension(R.dimen.size_100dip);
        padding = (int) getResources().getDimension(R.dimen.size_10dip);
    }

    //*******************************  setOnPageChangeListener 开始  *************************************
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            if (viewpager.getAdapter() != null) {
                String text = (position + 1) + "/" + viewpager.getAdapter().getCount();
                mCountView.setText(text);
            } else {
                mCountView.setText("0/0");
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };
    //*******************************  setOnPageChangeListener 结束  *************************************


    //*******************************  setOnSingleTapListener 开始  *************************************
    @Override
    public void onSingleTap() {
        hideViewPager();
    }
    //*******************************  setOnSingleTapListener 结束  *************************************


    /**
     * 显示大图pager
     *
     * @param index
     */
    private void showViewPager(int index) {
        pagerContainer.setVisibility(View.VISIBLE);
        editContainer.setVisibility(View.GONE);
        viewpager.setAdapter(viewpager.new LocalViewPagerAdapter(pictures));
        viewpager.setCurrentItem(index);
        mCountView.setText((index + 1) + "/" + pictures.size());
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation((float) 0.9, 1, (float) 0.9, 1, pagerContainer.getWidth() / 2, pagerContainer.getHeight() / 2);
        scaleAnimation.setDuration(200);
        set.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation((float) 0.1, 1);
        alphaAnimation.setDuration(200);
        set.addAnimation(alphaAnimation);
        pagerContainer.startAnimation(set);
    }

    /**
     * 关闭大图显示
     */
    private void hideViewPager() {
        pagerContainer.setVisibility(View.GONE);
        editContainer.setVisibility(View.VISIBLE);
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, (float) 0.9, 1, (float) 0.9, pagerContainer.getWidth() / 2, pagerContainer.getHeight() / 2);
        scaleAnimation.setDuration(200);
        set.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(200);
        set.addAnimation(alphaAnimation);
        pagerContainer.startAnimation(set);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post_back:    //返回键
                Snackbar.make(mBack, "退出", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.header_bar_photo_back:    //进入ViewPager后的返回箭头图片
                hideViewPager();
                break;

            case R.id.header_bar_photo_count:   //进入ViewPager后 1/9 的TextView
                hideViewPager();
                break;

            case R.id.header_bar_photo_delete:  //进入ViewPager后 右上角的删除图片
                int index = viewpager.getCurrentItem();
                Snackbar.make(mBack, "删除" + index, Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.post_send:
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                String content = mContent.getText().toString();
                if (StringUtils.isEmpty(content) && pictures.isEmpty()) {
                    Toast.makeText(this, "请添写动态内容或至少添加一张图片", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //设置为不可点击，防止重复提交
                    v.setEnabled(false);
                }
                break;

            case R.id.post_add_pic:
                Intent intent = new Intent(this, LocalAlbumAct.class);
                startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
                //Toast.makeText(DynamicPostAct.this, "跳到下一个Activity", Toast.LENGTH_SHORT).show();
                break;

            default:
                if (v instanceof FilterImageView) {
                    for (int i = 0; i < picContainer.getChildCount(); i++) {
                        if (v == picContainer.getChildAt(i)) {
                            showViewPager(i);
                        }
                    }
                }
                break;
        }
    }

    /**
     * 返回键的重写
     * 参考：http://blog.csdn.net/gf771115/article/details/5842190
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (pagerContainer.getVisibility() != View.VISIBLE) {
            //showSaveDialog();
        } else {
            hideViewPager();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                if (LocalImageHelper.getInstance().isResultOk()) {
                    LocalImageHelper.getInstance().setResultOk(false);
                    //获取选中的图片
                    List<LocalImageHelper.LocalFile> files = LocalImageHelper.getInstance().getCheckedItems();
                    for (int i = 0; i < files.size(); i++) {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
                        params.rightMargin = padding;
                        FilterImageView imageView = new FilterImageView(this);
                        imageView.setLayoutParams(params);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        ImageLoader.getInstance().displayImage(files.get(i).getThumbnailUri(), new ImageViewAware(imageView), options,
                                null, null, files.get(i).getOrientation());
                        imageView.setOnClickListener(this);
                        pictures.add(files.get(i));
                        if (pictures.size() == 9) {
                            add.setVisibility(View.GONE);
                        } else {
                            add.setVisibility(View.VISIBLE);
                        }
                        picContainer.addView(imageView, picContainer.getChildCount() - 1);
                        picRemain.setText(pictures.size() + "/9");
                        LocalImageHelper.getInstance().setCurrentSize(pictures.size());
                    }
                    //清空选中的图片
                    files.clear();
                    //设置当前选中的图片数量
                    LocalImageHelper.getInstance().setCurrentSize(pictures.size());
                    //延迟滑动至最右边
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                        }
                    }, 50L);
                }
                //清空选中的图片
                LocalImageHelper.getInstance().getCheckedItems().clear();
                break;

            default:
                break;
        }
    }

    /**
     * 初始化ImageLoader的，一般写在Application中，
     * 但这个Demo暂时写在activity中
     *
     * @param context
     */
    private void initImageLoader(Context context) {
        //缓存文件的目录
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "学习ImageLoader/cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800)   // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)  //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))  //你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)   // 内存缓存的最大值
                .diskCacheSize(10 * 1024 * 1024)    // 10 Mb sd卡(本地)缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCache(new UnlimitedDiskCache(cacheDir))    //自定义缓存路径
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))   // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs()   // Remove for release app
                .build();
        //全局初始化此配置
        ImageLoader.getInstance().init(config);
    }


}
