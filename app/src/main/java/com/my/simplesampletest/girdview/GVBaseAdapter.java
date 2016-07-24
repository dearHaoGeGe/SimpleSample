package com.my.simplesampletest.girdview;

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
 * Created by YJH on 2016/7/23.
 */
public class GVBaseAdapter extends BaseAdapter {

    private List<GVItemEntity> data;
    private Context context;
    private boolean isShowDelEnable;    //是否显示删除的按钮

    public GVBaseAdapter(List<GVItemEntity> data, Context context, boolean isShowDelEnable) {
        this.data = data;
        this.context = context;
        this.isShowDelEnable = isShowDelEnable;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_activity_gridview,parent,false);
            holder=new ViewHolder();
            holder.iv_item_GVAct= (ImageView) convertView.findViewById(R.id.iv_item_GVAct);
            holder.ivDel_item_GVAct= (ImageView) convertView.findViewById(R.id.ivDel_item_GVAct);
            holder.tvName_item_GVAct= (TextView) convertView.findViewById(R.id.tvName_item_GVAct);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tvName_item_GVAct.setText(data.get(position).getName());

        if (isShowDelEnable){
            holder.ivDel_item_GVAct.setVisibility(View.VISIBLE);
        }else {
            holder.ivDel_item_GVAct.setVisibility(View.GONE);
        }

        if (data.get(position).getLocalPic()!=null){
            holder.iv_item_GVAct.setImageBitmap(data.get(position).getLocalPic());
        }else {
            holder.iv_item_GVAct.setImageResource(R.mipmap.vonvon);
        }

        holder.ivDel_item_GVAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        private ImageView iv_item_GVAct,ivDel_item_GVAct;
        private TextView tvName_item_GVAct;
    }

    /**
     * 设置是否显示删除的按钮
     *
     * @param showDelEnable
     */
    public void setShowDelEnable(boolean showDelEnable) {
        isShowDelEnable = showDelEnable;
        notifyDataSetChanged();
    }

    public boolean isShowDelEnable() {
        return isShowDelEnable;
    }

    /**
     * 网适配器中添加数据
     *
     * @param entity
     */
    public void addData(GVItemEntity entity){
        this.data.add(entity);
        notifyDataSetChanged();
    }
}
