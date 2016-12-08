package com.my.simplesampletest.logger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.logger.adapter.MenuAdapter;
import com.my.simplesampletest.logger.listener.OnItemClickListener;
import com.my.simplesampletest.logger.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemStateChangedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 滑动直接删除+拖拽Item条目
 *
 * Created by YJH on 2016/11/30 17:44.
 */
public class ListDragSwipeActivity extends AppCompatActivity {

    private Activity mContext;

    private SwipeMenuRecyclerView mSwipeMenuRecyclerView;

    private List<String> mStrings;

    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView(R.layout.activity_all_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mStrings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mStrings.add("我是第" + i + "个。");
        }

        mSwipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
        mSwipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        mSwipeMenuRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mSwipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mSwipeMenuRecyclerView.addItemDecoration(new ListViewDecoration());// 添加分割线。

        // 这个就不用添加菜单啦，因为滑动删除和菜单是冲突的。

        mMenuAdapter = new MenuAdapter(mStrings);
        mMenuAdapter.setOnItemClickListener(onItemClickListener);
        mSwipeMenuRecyclerView.setAdapter(mMenuAdapter);

        mSwipeMenuRecyclerView.setLongPressDragEnabled(true);
        mSwipeMenuRecyclerView.setItemViewSwipeEnabled(true);// 开启滑动删除。
        mSwipeMenuRecyclerView.setOnItemMoveListener(onItemMoveListener);// 监听拖拽，更新UI。
        mSwipeMenuRecyclerView.setOnItemStateChangedListener(mOnItemStateChangedListener);
    }

    /**
     * 当Item移动的时候。
     */
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Collections.swap(mStrings, fromPosition, toPosition);
            mMenuAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {
            mStrings.remove(position);
            mMenuAdapter.notifyItemRemoved(position);
            Toast.makeText(mContext, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
        }

    };

    /**
     * Item的滑动状态发生变化监听。
     */
    private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == ACTION_STATE_DRAG) {
                getSupportActionBar().setSubtitle("状态：拖拽");
            } else if (actionState == ACTION_STATE_SWIPE) {

                getSupportActionBar().setSubtitle("状态：滑动删除");
            } else if (actionState == ACTION_STATE_IDLE) {
                getSupportActionBar().setSubtitle("状态：手指松开");
            }
        }
    };


    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(mContext, "我现在是第" + position + "条。", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}