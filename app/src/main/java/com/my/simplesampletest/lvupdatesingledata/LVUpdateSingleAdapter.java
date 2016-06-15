package com.my.simplesampletest.lvupdatesingledata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.my.simplesampletest.R;

import java.util.List;

/**
 * LVUpdateSingleAct中的适配器
 *
 * Created by YJH on 2016/6/15.
 */
public class LVUpdateSingleAdapter extends BaseAdapter{

    private Context context;
    private List<String> datas;
    private static final String CHANGE_DATA="数据改变了";

    public LVUpdateSingleAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setDatas(List<String> datas) {
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
        ViewHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_activity_lv_update_single,parent,false);
            holder=new ViewHolder();
            holder.iv_item_LVUpdateSingleAct= (ImageView) convertView.findViewById(R.id.iv_item_LVUpdateSingleAct);
            holder.tv_item_LVUpdateSingleAct= (TextView) convertView.findViewById(R.id.tv_item_LVUpdateSingleAct);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        if (datas!=null){
            holder.tv_item_LVUpdateSingleAct.setText(datas.get(position));
            if (datas.get(position).equals(CHANGE_DATA)){
                holder.iv_item_LVUpdateSingleAct.setImageResource(R.mipmap.a);
            }else {
                holder.iv_item_LVUpdateSingleAct.setImageResource(R.mipmap.aa);
            }
        }

        return convertView;
    }

    private static class ViewHolder{
        private ImageView iv_item_LVUpdateSingleAct;
        private TextView tv_item_LVUpdateSingleAct;
    }

    /**
     * ListView更新单条数据
     *
     * @param position
     * @param listView
     */
    public void updataView(int position, ListView listView) {
        int visibleFirstPosi = listView.getFirstVisiblePosition();
        int visibleLastPosi = listView.getLastVisiblePosition();
        if (position >= visibleFirstPosi && position <= visibleLastPosi) {
            View view = listView.getChildAt(position - visibleFirstPosi);
            ViewHolder holder = (ViewHolder) view.getTag();

            holder.tv_item_LVUpdateSingleAct.setText(datas.get(position));
            if (datas.get(position).equals(CHANGE_DATA)) {
                holder.iv_item_LVUpdateSingleAct.setImageResource(R.mipmap.a);
            } else {
                holder.iv_item_LVUpdateSingleAct.setImageResource(R.mipmap.aa);
            }
        } else {

        }
    }
}
