package com.my.simplesampletest.lv_nesting_lv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.my.simplesampletest.R;

import java.util.List;

/**
 * Created by YJH on 2016/9/28.
 */
public class NestTwoAdapter extends BaseAdapter {

    private Context context;
    private List<OrderEntity> datas;

    public NestTwoAdapter(Context context, List<OrderEntity> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_product, parent, false);
            holder = new ViewHolder();
            holder.tv_category = (TextView) convertView.findViewById(R.id.tv_category);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tv_TJBM = (TextView) convertView.findViewById(R.id.tv_TJBM);
            holder.tv_reducePrice = (TextView) convertView.findViewById(R.id.tv_reducePrice);
            holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            holder.tv_AllMoney = (TextView) convertView.findViewById(R.id.tv_AllMoney);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_category.setText(datas.get(position).getCategory());
        if (datas.get(position).getCategory().equals("单品")){
//            holder.tv_category.setBackgroundColor(Color.parseColor("#D7D7D7"));
//            holder.tv_category.setTextColor(Color.parseColor("#FF0000"));
            holder.tv_category.setSelected(true);
            holder.tv_category.setEnabled(false);
        }else if (datas.get(position).getCategory().equals("本品")){
//            holder.tv_category.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            holder.tv_category.setTextColor(Color.parseColor("#FF0000"));
            holder.tv_category.setEnabled(true);
        }else {
//            holder.tv_category.setBackgroundColor(Color.parseColor("#FF6600"));
//            holder.tv_category.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tv_category.setSelected(false);
            holder.tv_category.setEnabled(false);
        }

        holder.tv_name.setText(datas.get(position).getName());
        holder.tv_price.setText(datas.get(position).getPrice() + "");
        holder.tv_TJBM.setText(datas.get(position).getTCBM());
        holder.tv_reducePrice.setText(datas.get(position).getReducePrice() + "");
        holder.tv_num.setText(datas.get(position).getNum()+"");
        holder.tv_AllMoney.setText(datas.get(position).getTotal() + "");

        return convertView;
    }

    private static class ViewHolder {
        private TextView tv_category;   //单品、本品、赠品
        private TextView tv_name;       //产品名称
        private TextView tv_price;      //产品单价
        private TextView tv_TJBM;       //特价编码
        private TextView tv_reducePrice;//折后价格
        private TextView tv_num;        //购买的数量
        private TextView tv_AllMoney;   //合计
    }
}
