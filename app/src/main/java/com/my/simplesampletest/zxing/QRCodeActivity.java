package com.my.simplesampletest.zxing;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * 用zxing做的生成二维码，并且扫码
 * <p/>
 * Created by YJH on 2016/6/25.
 */
public class QRCodeActivity extends BaseActivity implements View.OnClickListener {

    private EditText ed_QRCodeAct;
    private Button btn_generate_QRCodeAct, btn_scan_QRCodeAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        initView();
        initData();
    }

    @Override
    public void initView() {
        ed_QRCodeAct = (EditText) findViewById(R.id.ed_QRCodeAct);
        btn_generate_QRCodeAct = (Button) findViewById(R.id.btn_generate_QRCodeAct);
        btn_scan_QRCodeAct = (Button) findViewById(R.id.btn_scan_QRCodeAct);

        btn_generate_QRCodeAct.setOnClickListener(this);
        btn_scan_QRCodeAct.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_generate_QRCodeAct:   //生成二维码
                if (ed_QRCodeAct.getText().toString()!=null&&ed_QRCodeAct.getText().toString().equals("")){
                    showQRCodeDialog();
                }else {
                    Toast.makeText(QRCodeActivity.this, "请输入内容!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_scan_QRCodeAct:   //扫描二维码
                break;
        }
    }

    /**
     * 生成二维码
     */
    private void showQRCodeDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final View view= LayoutInflater.from(this).inflate(R.layout.dialog_create_qrcode_personal_data_activity,null);
        final ImageView iv_head_QRCode_Dialog= (ImageView) view.findViewById(R.id.iv_head_QRCode_Dialog);
        final TextView tv_username_QRCode_Dialog= (TextView) view.findViewById(R.id.tv_username_QRCode_Dialog);
        final AppCompatImageView iv_QRCode_Dialog= (AppCompatImageView) view.findViewById(R.id.iv_QRCode_Dialog);
        final String QRCodeText=ed_QRCodeAct.getText().toString();
        CreateQRCode.createQRImage(QRCodeText, "QRCode",
                new CreateQRCode.OnCreateQRListener() {
                    @Override
                    public void onSuccess(Bitmap qrImage) {
                        iv_head_QRCode_Dialog.setImageResource(R.mipmap.github);
                        tv_username_QRCode_Dialog.setText(QRCodeText);
                        iv_QRCode_Dialog.setImageBitmap(qrImage);
                        builder.setView(view);
                        builder.show();
                    }
                });

    }
}
