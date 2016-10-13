package com.my.simplesampletest.lv_nesting_lv;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ListView嵌套ListView要自定义这个ListView
 *
 * Created by YJH on 2016/9/28.
 */
public class NestingListView extends ListView {

    public NestingListView(Context context) {
        super(context);
    }

    public NestingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        //Log.e("widthMeasureSpec=",widthMeasureSpec+"");
        //Log.e("expandSpec=",expandSpec+"");
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
