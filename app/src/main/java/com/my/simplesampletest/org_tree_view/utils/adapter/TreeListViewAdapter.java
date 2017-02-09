package com.my.simplesampletest.org_tree_view.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.my.simplesampletest.org_tree_view.utils.Node;
import com.my.simplesampletest.org_tree_view.utils.TreeHelper;

import java.util.List;

/**
 * TreeListViewAdapter
 * <p>
 * Created by YJH on 2017/2/5 16:23.
 */

public abstract class TreeListViewAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<Node> mAllNodes;
    protected List<Node> mVisibleNodes;
    protected LayoutInflater mInflater;
    protected ListView mTree;

    private OnTreeNodeClickListener mTreeListener;

    /**
     * 设置Node的点击回调
     */
    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener mTreeListener) {
        this.mTreeListener = mTreeListener;
    }

    public TreeListViewAdapter(Context context, ListView tree, List<T> datas, int defaultExpandLevel) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mTree = tree;
        this.mAllNodes = TreeHelper.getSortedNodes(mContext, datas, defaultExpandLevel);
        this.mVisibleNodes = TreeHelper.filterVisibleNodes(mContext, mAllNodes);

        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);

                if (null != mTreeListener) {
                    mTreeListener.onClick(mVisibleNodes.get(position), position);
                }
            }
        });
    }

    /**
     * 点击收缩或者展开
     *
     * @param position 位置
     */
    private void expandOrCollapse(int position) {
        Node n = mVisibleNodes.get(position);
        if (null != n) {
            if (n.isLeaf()) {
                return;
            }
            n.setExpand(!n.isExpand());
            mVisibleNodes = TreeHelper.filterVisibleNodes(mContext, mAllNodes);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mVisibleNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
//        return mVisibleNodes.get(position).getId();
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Node node = mVisibleNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        //设置内边距(设置样式)
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);
}
