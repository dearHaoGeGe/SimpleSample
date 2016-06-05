package com.my.simplesampletest.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.simplesampletest.R;

import java.util.List;

/**
 * MainActivity的RecyclerView适配器
 *
 * Created by YJH on 2016/6/4.
 */
public class MainActAdapter extends RecyclerView.Adapter<MainActAdapter.MyViewHolder>{

    private Context context;
    private List<String> datas;
    private MyItemOnClickListener onClickListener;
    private MyItemOnLongClickListener onLongClickListener;

    public MainActAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 创建item，初始化item中的组件
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_activity_main,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        viewHolder.tv_item_MainAct= (TextView) view.findViewById(R.id.tv_item_MainAct);
        return viewHolder;
    }

    /**
     * 设置item中的数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_item_MainAct.setText(position+"、"+datas.get(position));
    }

    /**
     * 返回item的条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 内部缓存类
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView tv_item_MainAct;

        public MyViewHolder(View itemView) {
            super(itemView);
            //对整个item进行监听
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onClickListener!=null){
                onClickListener.onItemClick(v,getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (onLongClickListener!=null){
                onLongClickListener.onItemLongClick(v,getLayoutPosition());
            }
            return true;
        }
    }

    /**
     * 点击事件接口
     */
    public interface MyItemOnClickListener{
        void onItemClick(View view,int position);
    }

    /**
     * 长按事件接口
     */
    public interface MyItemOnLongClickListener{
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(MyItemOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(MyItemOnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }
}
