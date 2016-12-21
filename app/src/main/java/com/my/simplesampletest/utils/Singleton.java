package com.my.simplesampletest.utils;

/**
 * 双重校验锁单例模式Demo
 * 参考：http://blog.csdn.net/qq379454816/article/details/50202151
 *
 * Created by YJH on 2016/12/21 13:16.
 */

public class Singleton {
    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton)
                    singleton = new Singleton();
            }
        }
        return singleton;
    }
}
