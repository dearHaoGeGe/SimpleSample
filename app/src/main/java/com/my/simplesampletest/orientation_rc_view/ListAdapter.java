package com.my.simplesampletest.orientation_rc_view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.simplesampletest.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 横向滑动的ListView适配器
 *
 * Created by YJH on 2016/10/24 11:55.
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<String> data;
    private Map<Integer, Boolean> map;
    private int screenWidth;
    private int screenHeight;

    public ListAdapter(Context context, List<String> data, int screenWidth, int screenHeight) {
        this.context = context;
        this.data = data;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        map = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            map.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_orientation_rcview, parent, false);
            holder = new ViewHolder();
            holder.tv_Type = (TextView) convertView.findViewById(R.id.tv_Type);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //动态设置item组件的宽高
        int w=(int) (0.333333 * screenWidth);
        int h=(int) (0.08 * screenHeight);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(w, h);
        holder.tv_Type.setLayoutParams(p);

        holder.tv_Type.setText(data.get(position));
        if (map.get(position)) {
            holder.tv_Type.setBackgroundColor(Color.parseColor("#FFFFFF")); //选中状态
        } else {
            holder.tv_Type.setBackgroundColor(Color.parseColor("#CCCCCC")); //未选中
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_Type;
    }

    public void setMap(Map<Integer, Boolean> map) {
        this.map = map;
        notifyDataSetChanged();
    }


}
