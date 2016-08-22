package com.my.simplesampletest.mvp.view;

/**
 * 登录的View接口
 *
 * Created by YJH on 2016/8/22.
 */
public interface ILoginView {

    public void onClearText();
    public void onLoginResult(boolean result,int code);
    public void onSetProgressBarVisibility(int visibility);

}
