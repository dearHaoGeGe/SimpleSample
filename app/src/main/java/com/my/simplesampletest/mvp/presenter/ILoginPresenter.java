package com.my.simplesampletest.mvp.presenter;

/**
 * Created by YJH on 2016/8/22.
 */
public interface ILoginPresenter {

    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);

}
