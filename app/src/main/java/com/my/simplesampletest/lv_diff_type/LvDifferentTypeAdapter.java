package com.my.simplesampletest.lv_diff_type;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.simplesampletest.R;

import java.util.List;

/**
 * 不同type的listview适配器
 * <p/>
 * Created by YJH on 2016/10/10.
 */
public class LvDifferentTypeAdapter extends BaseAdapter {

    private Context context;
    private List<DiffTypeBean> data;
    private final int VIEW_TYPE = 3;
    private final int TYPE_1 = 1;
    private final int TYPE_2 = 2;
    private final int TYPE_3 = 3;

    public LvDifferentTypeAdapter(Context context, List<DiffTypeBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE+1; //这里原来写3报错(不是自己程序的错)，改成大于type数量就没问题了
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
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;

        //如果convertView没有默认布局会没有提示的报错，加上这句话就好了
        //convertView = LayoutInflater.from(context).inflate(R.layout.default_layout_find_listview, parent, false);

        if (null == convertView) {
            switch (getItemViewType(position)) {
                case TYPE_1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_diff_type_one, parent, false);
                    holder1 = new ViewHolder1();
                    holder1.iv_item_lv_diff_type_one = (ImageView) convertView.findViewById(R.id.iv_item_lv_diff_type_one);
                    holder1.tv_name_item_lv_diff_type_one = (TextView) convertView.findViewById(R.id.tv_name_item_lv_diff_type_one);
                    holder1.tv_num_item_lv_diff_type_one = (TextView) convertView.findViewById(R.id.tv_num_item_lv_diff_type_one);
                    holder1.tv_date_item_lv_diff_type_one = (TextView) convertView.findViewById(R.id.tv_date_item_lv_diff_type_one);

                    convertView.setTag(holder1);
                    break;

                case TYPE_2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_activity_gridview, parent, false);
                    holder2 = new ViewHolder2();
                    holder2.iv_item_GVAct = (ImageView) convertView.findViewById(R.id.iv_item_GVAct);
                    holder2.ivDel_item_GVAct = (ImageView) convertView.findViewById(R.id.ivDel_item_GVAct);
                    holder2.tvName_item_GVAct = (TextView) convertView.findViewById(R.id.tvName_item_GVAct);

                    convertView.setTag(holder2);
                    break;

                case TYPE_3:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_image_loader_activity, parent, false);
                    holder3 = new ViewHolder3();
                    holder3.iv_item_ImageLoaderAct = (ImageView) convertView.findViewById(R.id.iv_item_ImageLoaderAct);
                    holder3.tv_item_ImageLoaderAct = (TextView) convertView.findViewById(R.id.tv_item_ImageLoaderAct);

                    convertView.setTag(holder3);
                    break;
            }
        } else {
            switch (getItemViewType(position)) {
                case TYPE_1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;

                case TYPE_2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;

                case TYPE_3:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
            }
        }

        if (null != data) {
            switch (getItemViewType(position)) {
                case TYPE_1:
                    holder1.iv_item_lv_diff_type_one.setImageBitmap(data.get(position).getType_1().getPic());
                    holder1.tv_name_item_lv_diff_type_one.setText(data.get(position).getType_1().getName());
                    holder1.tv_num_item_lv_diff_type_one.setText(data.get(position).getType_1().getNum());
                    holder1.tv_date_item_lv_diff_type_one.setText(data.get(position).getType_1().getDate());
                    break;

                case TYPE_2:
                    holder2.iv_item_GVAct.setImageBitmap(data.get(position).getType_2().getPic());
                    holder2.tvName_item_GVAct.setText(data.get(position).getType_2().getStr());
                    break;

                case TYPE_3:
                    holder3.iv_item_ImageLoaderAct.setImageBitmap(data.get(position).getType_3().getPic());
                    holder3.tv_item_ImageLoaderAct.setText(data.get(position).getType_3().getStr());
                    break;
            }
        }
        return convertView;
    }

    private class ViewHolder1 {
        ImageView iv_item_lv_diff_type_one;
        TextView tv_name_item_lv_diff_type_one;
        TextView tv_num_item_lv_diff_type_one;
        TextView tv_date_item_lv_diff_type_one;
    }

    private class ViewHolder2 {
        ImageView iv_item_GVAct;
        ImageView ivDel_item_GVAct;
        TextView tvName_item_GVAct;
    }

    private class ViewHolder3 {
        ImageView iv_item_ImageLoaderAct;
        TextView tv_item_ImageLoaderAct;
    }
}
