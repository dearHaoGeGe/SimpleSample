package com.my.simplesampletest.observer_mode;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by YJH on 2017/1/5 11:38.
 */

public class MyObserverable extends Observable {

    private final String TAG = getClass().getSimpleName();

    /**
     * 数据改变时调用
     */
    @Override
    protected void setChanged() {
        super.setChanged();
        Log.e(TAG, "数据改变了");
    }

    /**
     * 添加观察这时调用
     *
     * @param observer 需要观察的对象
     */
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        Log.e(TAG, "新增加了一个观察者");
    }

    @Override
    public synchronized void deleteObserver(Observer observer) {
        super.deleteObserver(observer);
        Log.e(TAG,"移除一个观察者");
    }
}
