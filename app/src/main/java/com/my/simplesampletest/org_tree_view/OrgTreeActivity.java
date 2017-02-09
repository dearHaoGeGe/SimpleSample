package com.my.simplesampletest.org_tree_view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.org_tree_view.bean.FileBean;
import com.my.simplesampletest.org_tree_view.bean.OrgBean;
import com.my.simplesampletest.org_tree_view.utils.Node;
import com.my.simplesampletest.org_tree_view.utils.adapter.SimpleTreeListViewAdapter;
import com.my.simplesampletest.org_tree_view.utils.adapter.TreeListViewAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构关系View（类似于多层ExpandableListView效果）
 * <p>
 * Created by YJH on 2017/2/4 11:21.
 */

public class OrgTreeActivity extends BaseActivity {

    private ListView lv_tree_view_act;
    private List<FileBean> mDatas;
    //    private SimpleTreeListViewAdapter<FileBean> mAdapter;
    private SimpleTreeListViewAdapter<OrgBean> mAdapter;
    private List<OrgBean> mDatas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_tree);

        initView();
        initData();
        initEvent();
    }

    @Override
    public void initView() {
        lv_tree_view_act = (ListView) findViewById(R.id.lv_tree_view_act);
    }

    @Override
    public void initData() {
//        mDatas = new ArrayList<>();
//        FileBean bean = new FileBean(1, 0, "根目录1");
//        mDatas.add(bean);
//        bean = new FileBean(2, 0, "根目录2");
//        mDatas.add(bean);
//        bean = new FileBean(3, 0, "根目录3");
//        mDatas.add(bean);
//        bean = new FileBean(4, 1, "根目录1-1");
//        mDatas.add(bean);
//        bean = new FileBean(5, 1, "根目录1-2");
//        mDatas.add(bean);
//        bean = new FileBean(6, 5, "根目录1-2-1");
//        mDatas.add(bean);
//        bean = new FileBean(7, 3, "根目录3-1");
//        mDatas.add(bean);
//        bean = new FileBean(8, 3, "根目录3-2");
//        mDatas.add(bean);

        //initDatas2
        mDatas2 = new ArrayList<>();
        OrgBean bean2 = new OrgBean(1, 0, "根目录1");
        mDatas2.add(bean2);
        bean2 = new OrgBean(2, 0, "根目录2");
        mDatas2.add(bean2);
        bean2 = new OrgBean(3, 0, "根目录3");
        mDatas2.add(bean2);
        bean2 = new OrgBean(4, 1, "目录1-1");
        mDatas2.add(bean2);
        bean2 = new OrgBean(5, 1, "目录1-2");
        mDatas2.add(bean2);
        bean2 = new OrgBean(6, 5, "目录1-2-1");
        mDatas2.add(bean2);
        bean2 = new OrgBean(7, 3, "目录3-1");
        mDatas2.add(bean2);
        bean2 = new OrgBean(8, 3, "目录3-2");
        mDatas2.add(bean2);

        mAdapter = new SimpleTreeListViewAdapter<>(this, lv_tree_view_act, mDatas2, 0);
        lv_tree_view_act.setAdapter(mAdapter);
    }

    private void initEvent() {
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (node.isLeaf()) {
                    Toast.makeText(OrgTreeActivity.this, node.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv_tree_view_act.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                mAdapter.removeSelectNode(position);

//                final EditText et = new EditText(OrgTreeActivity.this);
//                new AlertDialog.Builder(OrgTreeActivity.this)
//                        .setTitle("添加节点")
//                        .setView(et)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                if (TextUtils.isEmpty(et.getText().toString())) {
//                                    return;
//                                }
//                                mAdapter.addExtraNode(position, -1, et.getText().toString());
//                            }
//                        })
//                        .setNegativeButton("取消", null)
//                        .show();

                return true;   //返回false后可以触发点击事件、返回true后不触发点击事件
            }
        });
    }


}
