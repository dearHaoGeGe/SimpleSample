package com.my.simplesampletest.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class PhoneStateUtils {

	/**
	 * 飞行模式判断
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isAirplaneModeOn(Context context) {
		return (Settings.System.getInt(context.getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 0) != 0);
		//return (Settings.System.getInt(context.getContentResolver(), android.provider.Settings.Global.AIRPLANE_MODE_ON, 0) != 0);
	}

	/**
	 * 判断WiFi已连接并可用
	 * @param context
	 * @return
	 */
	public static boolean isWiFiActive(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {   
            NetworkInfo[] info = connectivity.getAllNetworkInfo();   
            if (info != null) {   
                for (int i = 0; i < info.length; i++) {   
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {   
                        return true;   
                    }   
                }   
            }   
        }   
        return false;   
    }  
	
	/**
	 * 判断是否为2或者3G
	 * @return
	 */
	public static boolean is2G3GActive(Context context){
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		int netWorkInfoType=connectivity.getActiveNetworkInfo().getSubtype();
		if(netWorkInfoType==2||netWorkInfoType==3){
			return true;
		}
		return false;
	}
	
	/**
	 * 网络判断
	 * @return
	 */
	public static boolean hasInternet(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			if (info.getState() == NetworkInfo.State.CONNECTED) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 网络判断
	 * @return
	 */
	public static boolean hasInternet(Activity mActivity) {
		ConnectivityManager manager = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			if (info.getState() == NetworkInfo.State.CONNECTED) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取手机唯一码 IMEI
	 * @return
	 */
	public static String getIMEI(Context context){
		TelephonyManager mTm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		return mTm.getDeviceId();
	}

	/**
	 * 判断是否得到某权限
	 * @param activity
	 * @param permName
	 * @return
	 */
	public static boolean getPermissionGranted(Activity activity, String permName){
		//String permName = "android.permission.CAMERA";
	    String pkgName = activity.getPackageName();
	    // 结果为0则表示使用了该权限，-1则表求没有使用该权限
	    int reslut = activity.getPackageManager().checkPermission(permName, pkgName);
	    if (reslut == PackageManager.PERMISSION_GRANTED) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	/**
	 * 检测是否安装
	 * @param context
	 * @param pageName
	 * @return
	 */
	public static boolean appIsInstalled(Context context, String pageName) {
	    try {
	        context.getPackageManager().getPackageInfo(pageName, 0);
	        return true;
	    } catch (NameNotFoundException e) {
	        return false;
	    }
	}
/* ------------------------	手机存储空间--------开始-------------------------- */
	private static final int ERROR = -1;

    /**
     * SDCARD是否存
     */
    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
    
    /**
	 * Whether the memory card in writing form
	 * 
	 * @Title: exterStorageReady
	 * @return true,false
	 */
	public static boolean externalStorageReady() {
		// Get SdCard state
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				/*&& Environment.getExternalStorageDirectory().canWrite()*/) {
			return true;

		}
		return false;
	}

    /**
     * 获取手机内部剩余存储空间
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = 0, availableBlocks = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        	blockSize = stat.getBlockSizeLong();
        	availableBlocks = stat.getAvailableBlocksLong();
        }else{
        	blockSize = stat.getBlockSize();
        	availableBlocks = stat.getAvailableBlocks();
        }
        
        return availableBlocks * blockSize;
    }

    /**
     * 获取手机内部总的存储空间
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = 0,totalBlocks = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        	blockSize = stat.getBlockSizeLong();
        	totalBlocks = stat.getBlockCount();
        }else{
        	blockSize = stat.getBlockSize();
        	totalBlocks = stat.getAvailableBlocks();
        }
        return totalBlocks * blockSize;
    }

    /**
     * 获取SDCARD剩余存储空间
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = 0, availableBlocks = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            	blockSize = stat.getBlockSizeLong();
            	availableBlocks = stat.getAvailableBlocksLong();
            }else{
            	blockSize = stat.getBlockSize();
            	availableBlocks = stat.getAvailableBlocks();
            }
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    /**
     * 获取SDCARD总的存储空间
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = 0, totalBlocks = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            	blockSize = stat.getBlockSizeLong();
            	totalBlocks = stat.getBlockCountLong();
            }else{
            	blockSize = stat.getBlockSize();
            	totalBlocks = stat.getBlockCount();
            }
            return totalBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    /**
     * 获取系统总内存
     * 
     * @param context 可传入应用程序上下文。
     * @return 总内存大单位为B。
     */
    public static long getTotalMemorySize(Context context) {
        String dir = "/proc/meminfo";
        try {
            FileReader fr = new FileReader(dir);
            BufferedReader br = new BufferedReader(fr, 2048);
            String memoryLine = br.readLine();
            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));
            br.close();
            return Integer.parseInt(subMemoryLine.replaceAll("\\D+", "")) * 1024l;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当前可用内存，返回数据以字节为单位。
     * 
     * @param context 可传入应用程序上下文。
     * @return 当前可用内存单位为B。
     */
    public static long getAvailableMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    private static DecimalFormat fileIntegerFormat = new DecimalFormat("#0");
    private static DecimalFormat fileDecimalFormat = new DecimalFormat("#0.#");

    /**
     * 单位换算
     * 
     * @param size 单位为B
     * @param isInteger 是否返回取整的单位
     * @return 转换后的单位
     */
    public static String formatFileSize(long size, boolean isInteger) {
        DecimalFormat df = isInteger ? fileIntegerFormat : fileDecimalFormat;
        String fileSizeString = "0M";
        if (size < 1024 && size > 0) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1024 * 1024) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1024 * 1024 * 1024) {
            fileSizeString = df.format((double) size / (1024 * 1024)) + "M";
        } else {
            fileSizeString = df.format((double) size / (1024 * 1024 * 1024)) + "G";
        }
        return fileSizeString;
    }
/* ------------------------	手机存储空间--------结束-------------------------- */
}
