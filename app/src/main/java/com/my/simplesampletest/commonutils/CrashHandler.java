package com.my.simplesampletest.commonutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 程序Crash之后捕获异常，把异常存到手机上，并且上传到后台
 * <p>
 * 参考：http://blog.csdn.net/allen315410/article/details/41444053
 * http://blog.csdn.net/singwhatiwanna/article/details/17289479
 * <p>
 * Created by YJH on 2016/11/10 15:33.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private final String TAG = getClass().getSimpleName();

    /**
     * 统默认的异常处理（默认情况下，系统会终止当前的异常程序）
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * 程序Context对象
     */
    private Context mContext;

    /**
     * 存放Crash信息的路径
     */
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/SimpleSample/log/";

    /**
     * 创建的Log名称的前面部分
     */
    private static final String FILE_NAME = "crash";

    /**
     * Log文件的后缀名
     */
    private static final String FILE_NAME_SUFFIX = ".trace";

    /**
     * 用于格式化日期,作为日志文件名的一部分
     */
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static CrashHandler INSTANCE = new CrashHandler();

    /**
     * 构造方法私有，防止外部构造多个实例，即采用单例模式
     */
    public CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     *
     * @return CrashHandler
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context context
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     *
     * @param thread thread
     * @param ex     异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //打印出当前调用栈信息
        ex.printStackTrace();

        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            android.os.Process.killProcess(Process.myPid());
            System.exit(1);
        }

    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //给用户Crash的提示
        showCrashToast();
        try {
            //导出异常信息到SD卡中
            dumpExceptionToSDCard(ex);

            //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
            uploadExceptionToServer();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 导出异常信息到SD卡中
     *
     * @param ex 异常
     * @throws IOException
     */
    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.w(TAG, "sdcard unmounted,skip dump exception");
            return;
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = formatter.format(new Date(current));
        //以当前时间创建log文件
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            //导出发生异常的时间
            pw.println(time);

            //导出手机信息
            dumpPhoneInfo(pw);

            pw.println();
            //导出异常的调用栈信息
            ex.printStackTrace(pw);

            pw.close();
        } catch (Exception e) {
            Log.e(TAG, "dump crash info failed");
        }
    }

    /**
     * 导出手机信息
     *
     * @param pw 错误信息
     * @throws PackageManager.NameNotFoundException
     */
    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        //应用的版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);

        //android版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        //手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        //手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);

        //cpu架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }

    /**
     * 显示崩溃Toast
     */
    private void showCrashToast() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,SimpleSample程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }

    /**
     * 通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
     */
    private void uploadExceptionToServer() {
        //TODO Upload Exception Message To Your Web Server
    }
}
