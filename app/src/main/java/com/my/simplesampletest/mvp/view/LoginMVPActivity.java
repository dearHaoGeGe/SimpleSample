package com.my.simplesampletest.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.mvp.presenter.ILoginPresenter;
import com.my.simplesampletest.mvp.presenter.LoginPresenterCompl;

/**
 * Created by YJH on 2016/8/22.
 */
public class LoginMVPActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private EditText editUser;
    private EditText editPass;
    private Button btnLogin;
    private Button btnClear;
    private ProgressBar progressBar;
    private ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvp);

        initView();
        initData();
    }

    @Override
    public void initView() {
        editUser = (EditText) this.findViewById(R.id.et_login_username);
        editPass = (EditText) this.findViewById(R.id.et_login_password);
        btnLogin = (Button) this.findViewById(R.id.btn_login_login);
        btnClear = (Button) this.findViewById(R.id.btn_login_clear);
        progressBar = (ProgressBar) this.findViewById(R.id.progress_login);

        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void initData() {
        loginPresenter=new LoginPresenterCompl(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_clear:
                loginPresenter.clear();
                break;

            case R.id.btn_login_login:
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                btnLogin.setEnabled(false);
                btnClear.setEnabled(false);
                loginPresenter.doLogin(editUser.getText().toString(),editPass.getText().toString());
                break;
        }
    }




    @Override
    public void onClearText() {
        editPass.setText("");
        editUser.setText("");
    }

    @Override
    public void onLoginResult(boolean result, int code) {
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        btnLogin.setEnabled(true);
        btnClear.setEnabled(true);
        if (result){
            Toast.makeText(LoginMVPActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(LoginMVPActivity.this, "登录失败！   code="+code, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
