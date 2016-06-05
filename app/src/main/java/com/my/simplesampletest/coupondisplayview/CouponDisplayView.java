package com.my.simplesampletest.coupondisplayview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * android自定义控件—边缘凹凸的View
 * 参考鸿洋微信公众账号：http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650820217&idx=1&sn=69380a847716dc4c3caca4a702df6f0d&scene=0&ptlang=2052&ADUIN=1607609655&ADSESSION=1465135933&ADTAG=CLIENT.QQ.5473_.0&ADPUBNO=26569#wechat_redirect
 * <p/>
 * Created by YJH on 2016/6/5.
 */
public class CouponDisplayView extends LinearLayout {

    private Paint mPaint;

    //圆间距
    private float gap = 8;

    //半径
    private float radius = 10;
    private int circleNum;
    private float remain;

    public CouponDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (int) (w - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(x, 0, radius, mPaint);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
    }
}
