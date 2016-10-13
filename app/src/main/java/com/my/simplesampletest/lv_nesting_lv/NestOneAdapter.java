package com.my.simplesampletest.lv_nesting_lv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.my.simplesampletest.R;

import java.util.List;

/**
 * Created by YJH on 2016/9/28.
 */
public class NestOneAdapter extends BaseAdapter {

    private Context context;
    private List<List<OrderEntity>> datas;
    private NestTwoAdapter adapter;
    private int headCount = 0;

    public NestOneAdapter(Context context, List<List<OrderEntity>> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_submit_nesting, parent, false);
            holder = new ViewHolder();
            holder.myLv = (NestingListView) convertView.findViewById(R.id.myLv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        adapter = new NestTwoAdapter(context, datas.get(position));
        holder.myLv.setAdapter(adapter);
        //添加头布局
        if (headCount < datas.size()) {    //这个判断是为了解决listview复用导致的位置错乱
            View v = LayoutInflater.from(context).inflate(R.layout.item_order_product_head, null);
            holder.myLv.addHeaderView(v);
            headCount++;
        }

        return convertView;
    }

    private static class ViewHolder {
        private NestingListView myLv;
    }
}
