package com.my.simplesampletest.add_local_pic.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 点击时显示明暗变化(滤镜效果)的ImageView
 *
 * Created by YJH on 2016/7/24.
 */
public class FilterImageView extends ImageView{
	public FilterImageView(Context context){
		super(context);
	}
	public FilterImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
    public FilterImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			//在按下事件中设置滤镜
			setFilter();
			break;
		case MotionEvent.ACTION_UP:
			//由于捕获了Touch事件，需要手动触发Click事件
			performClick();
		case MotionEvent.ACTION_CANCEL:
			//在CANCEL和UP事件中清除滤镜
			removeFilter();
			break;
		default:
			break;
		}
		return true;
	}

	/**  
	 *   设置滤镜
	 */
	private void setFilter() {
		//先获取设置的src图片
		Drawable drawable=getDrawable();
		//当src图片为Null，获取背景图片
		if (drawable==null) {
			drawable=getBackground();
		}
		if(drawable!=null){
			//设置滤镜
			drawable.setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY);;
		}
	}
	/**  
	 *   清除滤镜
	 */
	private void removeFilter() {
		//先获取设置的src图片
		Drawable drawable=getDrawable();
		//当src图片为Null，获取背景图片
		if (drawable==null) {
			drawable=getBackground();
		}
		if(drawable!=null){
			//清除滤镜
			drawable.clearColorFilter();
		}
	}
}
