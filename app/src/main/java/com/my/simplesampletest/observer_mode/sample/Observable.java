package com.my.simplesampletest.observer_mode.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式（被观察的对象）
 * http://www.w2bc.com/Article/76136
 * <p>
 * Created by YJH on 2017/1/9 13:27.
 */

public class Observable<T> {

    private List<Observer<T>> mObservers = new ArrayList<>();

    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    public void register(Observer<T> observer) {
        if (null == observer) {
            throw new NullPointerException("observer不能为空");
        }
        synchronized (this) {
            if (!mObservers.contains(observer)) {
                mObservers.add(observer);
            }
        }
    }

    /**
     * 取消注册观察者
     *
     * @param observer 观察者
     */
    public synchronized void unregister(Observer<T> observer) {
        if (null == observer) {
            throw new NullPointerException("observer不能为空");
        }
        mObservers.remove(observer);
    }

    /**
     * 移除所有的观察者
     */
    public synchronized void unregisterAll() {
        mObservers.clear();
    }

    /**
     * 刷新所有的观察者
     *
     * @param data 新的数据
     */
    public void notifyObservers(T data) {
        for (Observer<T> observer : mObservers) {
            observer.onUpdate(this, data);
        }
    }
}
