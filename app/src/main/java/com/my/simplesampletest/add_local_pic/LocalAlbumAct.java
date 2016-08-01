package com.my.simplesampletest.add_local_pic;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.add_local_pic.common.ExtraKey;
import com.my.simplesampletest.add_local_pic.common.ImageUtils;
import com.my.simplesampletest.add_local_pic.common.LocalImageHelper;
import com.my.simplesampletest.add_local_pic.common.StringUtils;
import com.my.simplesampletest.base.BaseApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 本地相册
 * <p/>
 * Created by YJH on 2016/7/29.
 */
public class LocalAlbumAct extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    /**
     * 加载进度的图片
     */
    private ImageView progress;
    /**
     * 相机的图片
     */
    private View camera;
    /**
     * 返回按钮
     */
    private View mBack;

    private ListView listView;
    private LocalImageHelper helper;
    private List<String> folderNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_album);

        initView();
        initData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.local_album_list);
        camera = findViewById(R.id.loacal_album_camera);
        progress = (ImageView) findViewById(R.id.progress_bar);
        findViewById(R.id.album_back).setOnClickListener(this);
    }

    private void initData() {
        camera.setOnClickListener(this);
        camera.setVisibility(View.GONE);
        listView.setOnItemClickListener(this);

        helper = LocalImageHelper.getInstance();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_loading);
        progress.startAnimation(animation);


        /**本地图片辅助类初始化,这个方法写在BaseApplication,不写的话没有初始化，会报空指针，不能写在这里*/
        //LocalImageHelper.init(BaseApplication.getInstance());

        new Thread(new Runnable() {
            @Override
            public void run() {
                /** 开启线程初始化本地图片列表，该方法是synchronized(同步)的，因此当AppContent在初始化时，此处阻塞 */
                LocalImageHelper.getInstance().initImage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**初始化完毕后，显示文件夹列表*/
                        if (!isDestroy) {
                            initAdapter();
                            progress.clearAnimation();
                            ((View) progress.getParent()).setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            camera.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }).start();
    }

    private void initAdapter() {
        listView.setAdapter(new FolderAdapter(this, helper.getFolderMap()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loacal_album_camera:
                takePic();  /**点击拍照*/
                break;

            case R.id.album_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
                    String cameraPath = LocalImageHelper.getInstance().getCameraImgPath();
                    if (StringUtils.isEmpty(cameraPath)) {
                        Toast.makeText(this, "图片获取失败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    File file = new File(cameraPath);
                    if (file.exists()) {
                        Uri uri = Uri.fromFile(file);
                        LocalImageHelper.LocalFile localFile = new LocalImageHelper.LocalFile();
                        localFile.setThumbnailUri(uri.toString());
                        localFile.setOriginalUri(uri.toString());
                        localFile.setOrientation(getBitmapDegree(cameraPath));
                        LocalImageHelper.getInstance().getCheckedItems().add(localFile);
                        LocalImageHelper.getInstance().setResultOk(true);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        //这里本来有个弹出progressDialog的，在拍照结束后关闭，但是要延迟1秒，原因是由于三星手机的相机会强制切换到横屏，
                        //此处必须等它切回竖屏了才能结束，否则会有异常
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(this, "图片获取失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 读取图片的旋转的角度，还是三星的问题，需要根据图片的旋转角度正确显示
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, LocalAlbumDetailAct.class);
        intent.putExtra(ExtraKey.LOCAL_FOLDER_NAME, folderNames.get(position));
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
    }

    /**
     * 点击拍照按钮
     */
    private void takePic() {
        if (LocalImageHelper.getInstance().getCurrentSize() + LocalImageHelper.getInstance().getCheckedItems().size() >= 9) {
            Toast.makeText(this, "最多选择9张图片", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /**拍照后保存图片的绝对路径*/
        String cameraPath = LocalImageHelper.getInstance().setCameraImgPath();
        File file = new File(cameraPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
    }

    /**
     * 适配器
     */
    private class FolderAdapter extends BaseAdapter {

        private Map<String, List<LocalImageHelper.LocalFile>> folders;
        private Context context;
        private DisplayImageOptions options;

        public FolderAdapter(Context context, Map<String, List<LocalImageHelper.LocalFile>> folders) {
            this.folders = folders;
            this.context = context;
            folderNames = new ArrayList<>();

            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(false)
                    .showImageForEmptyUri(R.mipmap.ic_empty)
                    .showImageOnFail(R.mipmap.ic_error)
                    .showImageOnLoading(R.mipmap.dangkr_no_picture_small)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .setImageSize(new ImageSize(((BaseApplication) context.getApplicationContext()).getQuarterWidth(), 0))
                    .displayer(new SimpleBitmapDisplayer()).build();

            Iterator iterator = folders.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                folderNames.add(key);
            }

            /** 根据文件夹内的图片数量降序显示 */
            Collections.sort(folderNames, new Comparator<String>() {
                @Override
                public int compare(String lhs, String rhs) {
                    Integer num1 = helper.getFolder(lhs).size();
                    Integer num2 = helper.getFolder(rhs).size();
                    return num2.compareTo(num1);
                }
            });
        }

        @Override
        public int getCount() {
            return folders.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null || convertView.getTag() == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_albumfoler, parent, false);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                holder.textView = (TextView) convertView.findViewById(R.id.textView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String name = folderNames.get(position);
            List<LocalImageHelper.LocalFile> files = folders.get(name);
            holder.textView.setText(name + "(" + files.size() + ")");
            if (files.size() > 0) {
                ImageLoader.getInstance().displayImage(files.get(0).getThumbnailUri(),
                        new ImageViewAware(holder.imageView), options,
                        null, null, files.get(0).getOrientation());
            }

            return convertView;
        }

        private class ViewHolder {
            private ImageView imageView;
            private TextView textView;
        }
    }
}
