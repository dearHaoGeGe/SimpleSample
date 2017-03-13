package com.my.simplesampletest.lv_nesting_lv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.lv_nesting_lv.linearlistview.LinearListView;

import java.util.List;

/**
 * parentAdapter
 * <p>
 * Created by YJH on 2016/9/28.
 */
public class NestOneAdapter extends BaseAdapter {

    private Context context;
    private List<List<OrderEntity>> datas;
    private LayoutInflater inflater;

    public NestOneAdapter(Context context, List<List<OrderEntity>> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(this.context);
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order_submit_nesting, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.adapter.setItems(datas.get(position));
        return convertView;
    }

    private class ViewHolder {

        private LinearListView linearListView;
        private TextView tv_combo_number;
        private NestTwoAdapter adapter;

        public ViewHolder(View convertView) {
            linearListView = (LinearListView) convertView.findViewById(R.id.linearListView);
            tv_combo_number = (TextView) convertView.findViewById(R.id.tv_combo_number);
            adapter = new NestTwoAdapter(context);
            linearListView.setAdapter(adapter);
        }
    }
}
