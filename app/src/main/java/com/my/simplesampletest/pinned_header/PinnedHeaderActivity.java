package com.my.simplesampletest.pinned_header;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

/**
 * Android 仿QQ联系人，分组悬浮，展开停至特定项
 * <p>
 * Created by YJH on 2017/3/4 15:40.
 */

public class PinnedHeaderActivity extends BaseActivity {

    private PinnedHeaderExpandableListView ex_listView;
    private PinnedHeaderExpandableAdapter adapter;
    private int expandFlag = -1;// 控制列表的展开

    private String[][] childrenData;
    private String[] groupData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinned_header);
        initView();
        initData();

        //展开所有的Group
        for (int i = 0; i < groupData.length; i++) {
            ex_listView.expandGroup(i);
        }
    }

    @Override
    public void initView() {
        ex_listView = (PinnedHeaderExpandableListView) findViewById(R.id.ex_listView);
    }

    @Override
    public void initData() {
        groupData = new String[12];
        childrenData = new String[12][10];

        for (int i = 0; i < 12; i++) {
            groupData[i] = "分组" + i;
            for (int j = 0; j < 10; j++) {
                childrenData[i][j] = "好友" + i + "-" + j;
            }
        }

        // 设置悬浮头部view
        ex_listView.setHeaderView(getLayoutInflater().inflate(R.layout.item_group_head, ex_listView, false));
        adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData, this, ex_listView);
//        ex_listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//
//                for (int i = 0; i < 10; i++) {
//                    if (groupPosition == i) {
//                        ex_listView.setSelection(i + 4);
//                        break;
//                    }
//                }
//
//            }
//        });

        // 设置单个分组展开(使得单个分组在顶端)
//        ex_listView.setOnGroupClickListener(new GroupClickListener());
        ex_listView.setAdapter(adapter);
    }

    /**
     * 点击展开的选项置顶
     */
    private class GroupClickListener implements ExpandableListView.OnGroupClickListener {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            if (expandFlag == -1) {
                // 展开被选的group
                ex_listView.expandGroup(groupPosition);
                // 设置被选中的group置于顶端
                ex_listView.setSelectedGroup(groupPosition);
                expandFlag = groupPosition;
            } else if (expandFlag == groupPosition) {
                ex_listView.collapseGroup(expandFlag);
                expandFlag = -1;
            } else {
                ex_listView.collapseGroup(expandFlag);
                // 展开被选的group
                ex_listView.expandGroup(groupPosition);
                // 设置被选中的group置于顶端
                ex_listView.setSelectedGroup(groupPosition);
                expandFlag = groupPosition;
            }
            return true;
        }
    }
}
