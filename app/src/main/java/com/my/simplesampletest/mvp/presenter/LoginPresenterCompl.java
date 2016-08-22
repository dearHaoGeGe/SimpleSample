package com.my.simplesampletest.mvp.presenter;

import android.os.Handler;
import android.os.Looper;

import com.my.simplesampletest.mvp.model.IUser;
import com.my.simplesampletest.mvp.model.UserModel;
import com.my.simplesampletest.mvp.view.ILoginView;

/**
 * Created by YJH on 2016/8/22.
 */
public class LoginPresenterCompl implements ILoginPresenter {

    private ILoginView iLoginView;
    private IUser iUser;
    private Handler handler;

    public LoginPresenterCompl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    private void initUser() {
        iUser = new UserModel("mvp", "mvp");
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        boolean isLoginSuccess = true;
        final int code = iUser.checkUserValidity(name, passwd);
        if (code != 0) {
            isLoginSuccess = false;
        }
        final boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result, code);
            }
        }, 5000);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }
}
