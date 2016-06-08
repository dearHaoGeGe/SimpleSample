package com.my.simplesampletest.utils;

import android.util.Log;

/**
 * Log工具
 * isDebug  控制是否打印Log
 * <p>
 * SHOW_LOG_INFO    控制是否打印LogInfo
 * <p>
 * Created by YJH on 2016/6/8.
 */
public class L {

    /**
     * 控制是否打印Log
     */
    public static boolean isDebug = true;
    /**
     * 控制是否打印LogInfo  1为显示  其他值为不显示
     */
    private static int SHOW_LOG_INFO = 8;
    public static final String SEPARATOR = ",";
    private static final String TAG = "Log工具----->";

    public L() {
        //不能被实例化
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Verbose
     *
     * @param msg
     */
    public static void v(String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.v(TAG, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.v(TAG, msg);
                    break;
            }

        }
    }

    /**
     * Verbose
     *
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.v(tag, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.v(tag, msg);
                    break;
            }

        }
    }

    /**
     * Debug
     *
     * @param msg
     */
    public static void d(String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.d(TAG, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.d(TAG, msg);
                    break;
            }

        }
    }

    /**
     * Debug
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.d(tag, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.d(tag, msg);
                    break;
            }

        }
    }

    /**
     * Info
     *
     * @param msg
     */
    public static void i(String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.i(TAG, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.i(TAG, msg);
                    break;
            }

        }
    }

    /**
     * Info
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.i(tag, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.i(tag, msg);
                    break;
            }

        }
    }

    /**
     * Warn
     *
     * @param msg
     */
    public static void w(String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.w(TAG, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.w(TAG, msg);
                    break;
            }

        }
    }

    /**
     * Warn
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.w(tag, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.w(tag, msg);
                    break;
            }

        }
    }

    /**
     * Error
     *
     * @param msg
     */
    public static void e(String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.e(TAG, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.e(TAG, msg);
                    break;
            }

        }
    }

    /**
     * Error
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isDebug) {

            switch (SHOW_LOG_INFO) {
                case 1:
                    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                    Log.e(tag, msg + getLogInfo(stackTraceElement));
                    break;

                default:
                    Log.e(tag, msg);
                    break;
            }

        }
    }

    /**
     * 输出日志所包含的信息
     *
     * @param stackTraceElement
     * @return
     */
    public static String getLogInfo(StackTraceElement stackTraceElement) {
        StringBuilder logInfoStringBuilder = new StringBuilder();
        // 获取线程名
        String threadName = Thread.currentThread().getName();
        // 获取线程ID
        long threadID = Thread.currentThread().getId();
        // 获取文件名.即xxx.java
        String fileName = stackTraceElement.getFileName();
        // 获取类名.即包名+类名
        String className = stackTraceElement.getClassName();
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();
        // 获取生日输出行数
        int lineNumber = stackTraceElement.getLineNumber();

        logInfoStringBuilder.append("          [ ");
        logInfoStringBuilder.append("threadID = " + threadID).append(SEPARATOR);
        logInfoStringBuilder.append("threadName = " + threadName).append(SEPARATOR);
        logInfoStringBuilder.append("fileName = " + fileName).append(SEPARATOR);
        logInfoStringBuilder.append("className = " + className).append(SEPARATOR);
        logInfoStringBuilder.append("methodName = " + methodName).append(SEPARATOR);
        logInfoStringBuilder.append("lineNumber = " + lineNumber);
        logInfoStringBuilder.append(" ]");
        return logInfoStringBuilder.toString();
    }

    /**
     * 获取默认的TAG名称.
     * 比如在MainActivity.java中调用了日志输出.
     * 则TAG为MainActivity
     *
     * @param stackTraceElement
     * @return
     */
    public static String getDefaultTag(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        String stringArray[] = fileName.split("\\.");
        String tag = stringArray[0];
        return tag;
    }

}
