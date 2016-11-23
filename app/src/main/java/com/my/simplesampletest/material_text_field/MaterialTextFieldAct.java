package com.my.simplesampletest.material_text_field;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 登录动画效果
 * https://github.com/florent37/MaterialTextField
 * <p>
 * Android开源特效最全合集
 * http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650236349&idx=1&sn=f4f54e0b4c942ccc8775b98617cfa587&scene=0&ptlang=2052&ADUIN=3077015110&ADSESSION=1467898995&ADTAG=CLIENT.QQ.5473_.0&ADPUBNO=26569#wechat_redirect
 * <p>
 * Created by YJH on 2016/7/7.
 */
public class MaterialTextFieldAct extends BaseActivity implements View.OnClickListener {

    private Button btn_show_PopUpWindow;
    private PopupWindow mPopWindow;
    private TextView tv_ProductName;
    private EditText et_Piece_PieceToPkg;
    private EditText et_Pkg_PieceToPkg;
    private EditText et_Pkg_PkgToPiece;
    private EditText et_Piece_PkgToPiece;
    private Button btn_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_text_field);

        initView();
        initData();
    }

    @Override
    public void initView() {
        btn_show_PopUpWindow = (Button) findViewById(R.id.btn_show_PopUpWindow);

        btn_show_PopUpWindow.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_PopUpWindow:
                showPopUpWindow();
                break;

            case R.id.btn_commit:
                mPopWindow.dismiss();
                et_Piece_PieceToPkg.getText();
                et_Pkg_PieceToPkg.getText();
                break;
        }
    }

    private void showPopUpWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_unit_change, null);
        tv_ProductName = (TextView) view.findViewById(R.id.tv_ProductName);
        et_Piece_PieceToPkg = (EditText) view.findViewById(R.id.et_Piece_PieceToPkg);
        et_Pkg_PieceToPkg = (EditText) view.findViewById(R.id.et_Pkg_PieceToPkg);
        et_Pkg_PkgToPiece = (EditText) view.findViewById(R.id.et_Pkg_PkgToPiece);
        et_Piece_PkgToPiece = (EditText) view.findViewById(R.id.et_Piece_PkgToPiece);
        btn_commit = (Button) view.findViewById(R.id.btn_commit);
        btn_commit.setOnClickListener(this);

        mPopWindow = new PopupWindow(view,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true);
        mPopWindow.setTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setContentView(view);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_material_text_field, null);
        mPopWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }
}
