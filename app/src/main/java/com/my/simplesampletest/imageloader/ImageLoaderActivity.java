package com.my.simplesampletest.imageloader;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * 学习使用ImageLoader，ImageLoader在2015年9月份已经停止更新
 * 以后的项目中不建议使用
 *
 * http://mp.weixin.qq.com/s?__biz=MzA4NTQwNDcyMA==&mid=2650661949&idx=1&sn=09aececd879bd8b4635e6a63a8249808&scene=0#wechat_redirect
 * http://blog.csdn.net/xiaanming/article/details/26810303
 *
 * Created by YJH on 2016/6/27.
 */
public class ImageLoaderActivity extends BaseActivity{

    private ListView lv_ImageLoaderAct;
    private ImageLoaderAdapter adapter;
    private ImageLoader imageLoader;
    private DisplayImageOptions options; // 设置图片显示相关参数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

        initImageLoader(this);
        initView();
        initData();
    }

    /**
     * 初始化ImageLoader的，一般写在Application中，
     * 但这个Demo暂时写在activity中
     *
     * @param context
     */
    private void initImageLoader(Context context){
        //缓存文件的目录
        File cacheDir= StorageUtils.getOwnCacheDirectory(context,"学习ImageLoader/cache");
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480,800)   // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)  //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY-2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new UsingFreqLimitedMemoryCache(2*1024*1024))  //你可以通过自己的内存缓存实现
                .memoryCacheSize(2*1024*1024)   // 内存缓存的最大值
                .diskCacheSize(10*1024*1024)    // 10 Mb sd卡(本地)缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCache(new UnlimitedDiskCache(cacheDir))    //自定义缓存路径
                .imageDownloader(new BaseImageDownloader(context,5*1000,30*1000))   // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs()   // Remove for release app
                .build();
        //全局初始化此配置
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void initView() {
        lv_ImageLoaderAct= (ListView) findViewById(R.id.lv_ImageLoaderAct);
    }

    @Override
    public void initData() {
        options=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_stub)   // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_empty)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_error)     // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)   // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20))// 设置成圆角图片
                .build();   // 构建完成

        imageLoader=ImageLoader.getInstance();

        /**
         * 快速滑动GridView，ListView，我们希望能停止图片的加载，
         * 而在GridView，ListView停止滑动的时候加载当前界面的图片，
         * 这个框架当然也提供这个功能，使用起来也很简单，
         * 它提供了PauseOnScrollListener这个类来控制ListView,GridView滑动过程中停止去加载图片
         *
         * 第一个参数就是我们的图片加载对象ImageLoader,
         * 第二个是控制是否在滑动过程中暂停加载图片，如果需要暂停传true就行了，
         * 第三个参数控制猛的滑动界面的时候图片是否加载
         */
        lv_ImageLoaderAct.setOnScrollListener(new PauseOnScrollListener(imageLoader,true,true));

        adapter=new ImageLoaderAdapter(images,this,imageLoader,options);
        lv_ImageLoaderAct.setAdapter(adapter);
    }


    /**
     * 适配器
     */
    private class ImageLoaderAdapter extends BaseAdapter{

        private String[] img;
        private Context context;
        private ImageLoader imageLoader;
        private DisplayImageOptions options;

        public ImageLoaderAdapter(String[] img, Context context, ImageLoader imageLoader, DisplayImageOptions options) {
            this.img = img;
            this.context = context;
            this.imageLoader = imageLoader;
            this.options = options;
        }

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public Object getItem(int position) {
            return img[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if (convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.item_image_loader_activity,parent,false);
                holder=new ViewHolder();
                holder.iv_item_ImageLoaderAct= (ImageView) convertView.findViewById(R.id.iv_item_ImageLoaderAct);
                holder.tv_item_ImageLoaderAct= (TextView) convertView.findViewById(R.id.tv_item_ImageLoaderAct);

                convertView.setTag(holder);
            }else {
                holder= (ViewHolder) convertView.getTag();
            }

            /**
             * imageUrl 图片的Url地址 imageView 承载图片的ImageView控件 options
             * DisplayImageOptions配置文件
             */
            imageLoader.displayImage(img[position],holder.iv_item_ImageLoaderAct,options);
            holder.tv_item_ImageLoaderAct.setText("Item"+(position+1));

            return convertView;
        }

        class ViewHolder{
            private ImageView iv_item_ImageLoaderAct;
            private TextView tv_item_ImageLoaderAct;
        }
    }



    public static final String[] images = new String[] {
            "http://cdn.duitang.com/uploads/blog/201308/18/20130818150526_Ru2Bk.thumb.600_0.png",
            "http://www.bkill.com/u/info_img/2012-09/02/2012083116140522302.jpg",
            "http://www.it165.net/uploadfile/2011/1218/20111218070928328.jpg",
            "http://www.3761.com/uploads/pic/42861391998071.jpg",
            "http://www.jhq8.cn/qqtouxiang/UploadPic/2012-9/201291016107737.jpg",
            "http://www.3761.com/uploads/pic/71781391998073.jpg",
            "http://www.qqcan.com/uploads/allimg/c120822/13455c923250-91E45.jpg",
            "http://p1.qq181.com/cms/120503/2012050320291269450.jpg",
            "http://www.qqbody.com/uploads/allimg/201401/20-094917_95.jpg",
            "http://www.qqbody.com/uploads/allimg/201301/15-200525_65.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130117/1-13011F61119.jpg",
            "http://www.xk77.com/uploads/allimg/111115/1_111115124125_6.jpg",
            "http://www.3761.com/uploads/pic/7761379903040.jpg",
            "http://www.qqai.net/fa/UploadPic/2012-8/2012829161638939.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqya.com/userimg/2455/110514234111.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://img1.touxiang.cn/uploads/20121125/25-032807_173.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://img1.touxiang.cn/uploads/20121204/04-013352_840.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://img1.touxiang.cn/uploads/20121204/04-013953_608.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqphotos.com/uploads/allimg/1401/2-140112112226.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130326/1-130326094313-52.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.3761.com/uploads/pic/32201377656359.png",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qjis.com/uploads/allimg/121027/130Z635X-3.png",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130928110958_zmzdn.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.jhq8.cn/qqtouxiang/UploadPic/2012-11/2012119154647323.jpg",
            "http://www.qqbody.com/uploads/allimg/201301/12-202555_717.jpg",
            "http://www.qqw21.com/article/UploadPic/2012-8/20128923812441.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130111/1-130111210R4.png",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.bkill.com/u/info_img/2012-09/02/2012083116140516694.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130320/1-1303200ZZ0.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210553_4kr7a.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210555_iexeg.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130326/1-1303261A329-51.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20121202211215_fnoal.jpg",
            "http://www.qqw21.com/article/UploadPic/2012-8/20128923812441.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130111/1-130111210R4.png",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.bkill.com/u/info_img/2012-09/02/2012083116140516694.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130320/1-1303200ZZ0.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210553_4kr7a.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210555_iexeg.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130326/1-1303261A329-51.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20121202211215_fnoal.jpg",
            "http://www.qqw21.com/article/UploadPic/2012-8/20128923812441.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130111/1-130111210R4.png",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.bkill.com/u/info_img/2012-09/02/2012083116140516694.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130320/1-1303200ZZ0.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210553_4kr7a.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210555_iexeg.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130326/1-1303261A329-51.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20121202211215_fnoal.jpg",
            "http://www.qqw21.com/article/UploadPic/2012-8/20128923812441.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130111/1-130111210R4.png",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.bkill.com/u/info_img/2012-09/02/2012083116140516694.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130320/1-1303200ZZ0.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210553_4kr7a.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210555_iexeg.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130326/1-1303261A329-51.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20121202211215_fnoal.jpg",
            "http://www.qqw21.com/article/UploadPic/2012-8/20128923812441.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130111/1-130111210R4.png",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.bkill.com/u/info_img/2012-09/02/2012083116140516694.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130320/1-1303200ZZ0.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210553_4kr7a.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210555_iexeg.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130326/1-1303261A329-51.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20121202211215_fnoal.jpg",
            "http://www.qqw21.com/article/UploadPic/2012-8/20128923812441.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130111/1-130111210R4.png",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.bkill.com/u/info_img/2012-09/02/2012083116140516694.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130320/1-1303200ZZ0.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210553_4kr7a.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210555_iexeg.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130326/1-1303261A329-51.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20121202211215_fnoal.jpg",
            "http://www.qqw21.com/article/UploadPic/2012-8/20128923812441.jpg",
            "http://img1.touxiang.cn/uploads/20120902/02-055331_356.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130111/1-130111210R4.png",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.bkill.com/u/info_img/2012-09/02/2012083116140516694.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130320/1-1303200ZZ0.jpg",
            "http://www.qqbody.com/uploads/allimg/201302/20-143539_945.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210553_4kr7a.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20130521210555_iexeg.jpg",
            "http://www.qqgqtx.com/uploads/allimg/130326/1-1303261A329-51.jpg",
            "http://www.meilinvsheng.com/upload_files/article/135/1_20121202211215_fnoal.jpg"
    };
}
