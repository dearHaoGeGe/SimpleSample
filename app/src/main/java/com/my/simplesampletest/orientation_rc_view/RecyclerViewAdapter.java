package com.my.simplesampletest.orientation_rc_view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.simplesampletest.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RecyclerView适配器
 * <p/>
 * Created by YJH on 2016/10/17.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final String TAG = "RecyclerView适配器";
    private RcItemOnClickListener rcItemOnClickListener;    //点击接口
    private Context context;
    private List<String> data;
    private Map<Integer, Boolean> map;
    private int screenWidth;
    private int screenHeight;

    public RecyclerViewAdapter(Context context, List<String> data, int screenWidth, int screenHeight) {
        this.context = context;
        this.data = data;
        map = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            map.put(i, false);
        }
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_orientation_rcview, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        //动态设置item组件的宽高
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams((int) (0.333333 * screenWidth), (int) (0.08 * screenHeight));
        holder.tv_Type.setLayoutParams(p);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv_Type.setText(data.get(i));
        if (map.get(i)) {
            viewHolder.tv_Type.setBackgroundColor(Color.parseColor("#FFFFFF")); //选中状态
        } else {
            viewHolder.tv_Type.setBackgroundColor(Color.parseColor("#CCCCCC")); //未选中
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_Type;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_Type = (TextView) itemView.findViewById(R.id.tv_Type);
        }

        @Override
        public void onClick(View v) {
            if (rcItemOnClickListener != null) {
                rcItemOnClickListener.onItemClick(v, getPosition());
                setStatus(getPosition());
            }
        }


    }

    /**
     * 点击事件的接口
     */
    public interface RcItemOnClickListener {
        void onItemClick(View view, int position);
    }

    public void setRcItemOnClickListener(RcItemOnClickListener rcItemOnClickListener) {
        this.rcItemOnClickListener = rcItemOnClickListener;
    }

    /**
     * 设置选中的状态
     *
     * @param position 选中的位置
     */
    public void setStatus(int position) {
        for (int i = 0; i < data.size(); i++) {
            if (i == position) {
                map.put(i, true);
            } else {
                map.put(i, false);
            }
        }
        notifyDataSetChanged();
    }
}
