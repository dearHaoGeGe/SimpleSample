package com.my.simplesampletest.org_tree_view.utils.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.org_tree_view.utils.Node;
import com.my.simplesampletest.org_tree_view.utils.TreeHelper;

import java.util.List;

/**
 * Created by YJH on 2017/2/5 18:43.
 */

public class SimpleTreeListViewAdapter<T> extends TreeListViewAdapter {


    public SimpleTreeListViewAdapter(Context context, ListView tree, List datas, int defaultExpandLevel) {
        super(context, tree, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_tree_view_list, parent, false);
            holder = new ViewHolder();
            holder.mIcon = (ImageView) convertView.findViewById(R.id.iv_tree_view);
            holder.mText = (TextView) convertView.findViewById(R.id.tv_tree_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (null == node.getIcon()) {
            holder.mIcon.setVisibility(View.INVISIBLE);
        } else {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageBitmap(node.getIcon());
        }

        holder.mText.setText(node.getName());
        return convertView;
    }

    /**
     * 动态插入节点
     * （后台请求成功或者数据库插入成功之后执行）
     *
     * @param position 位置
     * @param id       id
     * @param name     name
     */
    public void addExtraNode(int position, int id, String name) {
        Node node = (Node) mVisibleNodes.get(position);
        int indexOf = mAllNodes.indexOf(node);
        Node extraNode = new Node(id, node.getId(), name);
        extraNode.setParent(node);
        node.getChildren().add(extraNode);

        node.setExpand(!extraNode.isExpand());  //添加完成之后要展开

        mAllNodes.add(indexOf + 1, extraNode);

        mVisibleNodes = TreeHelper.filterVisibleNodes(mContext, mAllNodes);
        notifyDataSetChanged();
    }

    public void removeSelectNode(int position) {
        Node node = (Node) mVisibleNodes.get(position);
//        int indexOf = mAllNodes.indexOf(node);      //获取到要删除的节点在所有数据的位置
//
//        if (((Node) mAllNodes.get(indexOf)).isLeaf()) { //判断此节点是否是叶子节点
//            if (!node.isRoot()) {   //如果有根节点
//                node.getParent().getChildren().remove(node);
//            }
//            mAllNodes.remove(indexOf);  //从所有数据中移除
//        } else {
//
//            return;
//        }
        removeLeafNode(node);

        mVisibleNodes = TreeHelper.filterVisibleNodes(mContext, mAllNodes);
        notifyDataSetChanged();
    }

    /**
     * 删除叶子节点（没有Children的节点）
     *
     * @param leafNode 叶子节点
     */
    private boolean removeLeafNode(Node leafNode) {
        if (leafNode.isLeaf()) { //判断此节点是否是叶子节点
            if (!leafNode.isRoot()) {   //如果有根节点
                leafNode.getParent().getChildren().remove(leafNode);
            }
            int indexOf = mAllNodes.indexOf(leafNode);      //获取到要删除的节点在所有数据的位置
            mAllNodes.remove(indexOf);  //从所有数据中移除指定项
            return true;
        } else {
            return false;
        }
    }

    private class ViewHolder {
        TextView mText;
        ImageView mIcon;
    }
}
