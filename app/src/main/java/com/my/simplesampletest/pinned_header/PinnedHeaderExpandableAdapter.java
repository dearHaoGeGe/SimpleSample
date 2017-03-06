package com.my.simplesampletest.pinned_header;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.simplesampletest.R;

/**
 * Created by YJH on 2017/3/4 16:19.
 */

public class PinnedHeaderExpandableAdapter extends BaseExpandableListAdapter implements PinnedHeaderExpandableListView.HeaderAdapter {

    private String[][] childrenData;
    private String[] groupData;
    private Context mContext;
    private PinnedHeaderExpandableListView listView;
    private LayoutInflater inflater;
    private SparseIntArray groupStatusMap;

    public PinnedHeaderExpandableAdapter(String[][] childrenData, String[] groupData, Context context, PinnedHeaderExpandableListView listView) {
        this.childrenData = childrenData;
        this.groupData = groupData;
        this.mContext = context;
        this.listView = listView;
        inflater = LayoutInflater.from(mContext);
        groupStatusMap = new SparseIntArray();
    }

    @Override
    public int getGroupCount() {
        return groupData.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childrenData[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenData[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        if (isExpanded) {
            holder.groupIcon.setImageResource(R.mipmap.albumset_selected);
        } else {
            holder.groupIcon.setImageResource(R.mipmap.albumset_preselected);
        }
        holder.groupto.setText(groupData[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_child, parent, false);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.childto.setText(childrenData[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //*********************************** PinnedHeaderExpandableListView.HeaderAdapter开始 **************************************
    @Override
    public int getHeaderState(int groupPosition, int childPosition) {
        int childCount = getChildrenCount(groupPosition);
        if (childPosition == childCount - 1) {
            return PINNED_HEADER_PUSHED_UP;
        } else if (childPosition == -1 && !listView.isGroupExpanded(groupPosition)) {
            return PINNED_HEADER_GONE;
        } else {
            return PINNED_HEADER_VISIBLE;
        }
    }

    @Override
    public void configureHeader(View header, int groupPosition, int childPosition, int alpha) {
        String groupData = this.groupData[groupPosition];
        ((TextView) header.findViewById(R.id.groupto)).setText(groupData);
    }

    @Override
    public void setGroupClickStatus(int groupPosition, int status) {
        groupStatusMap.put(groupPosition, status);
    }

    @Override
    public int getGroupClickStatus(int groupPosition) {
        if (groupStatusMap.keyAt(groupPosition) >= 0) {
            return groupStatusMap.get(groupPosition);
        } else {
            return 0;
        }
    }
    //*********************************** PinnedHeaderExpandableListView.HeaderAdapter结束 **************************************


    private class GroupHolder {
        ImageView groupIcon;
        TextView groupto;

        private GroupHolder(View convertView) {
            groupIcon = (ImageView) convertView.findViewById(R.id.groupIcon);
            groupto = (TextView) convertView.findViewById(R.id.groupto);
        }
    }

    private class ChildHolder {
        TextView childto;

        private ChildHolder(View convertView) {
            childto = (TextView) convertView.findViewById(R.id.childto);
        }
    }
}
