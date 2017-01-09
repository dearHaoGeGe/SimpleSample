package com.my.simplesampletest.observer_mode.sample;


/**
 * 观察者需要实现的接口
 * <p>
 * Created by YJH on 2017/1/9 14:50.
 */

public interface Observer<T> {

    /**
     * 更新
     *
     * @param observable 被观察者
     * @param data       数据
     */
    void onUpdate(Observable<T> observable, T data);

}
