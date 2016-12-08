package com.my.simplesampletest.logger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.logger.adapter.MenuViewTypeAdapter;
import com.my.simplesampletest.logger.entity.ViewTypeBean;
import com.my.simplesampletest.logger.listener.OnItemClickListener;
import com.my.simplesampletest.logger.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewType菜单
 *
 * Created by YJH on 2016/11/30 17:44.
 */
public class ViewTypeMenuActivity extends AppCompatActivity {

    private Activity mContext;

    private List<ViewTypeBean> mViewTypeBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = this;

        // 这里只是模拟数据，模拟Item的ViewType，根据ViewType决定显示什么菜单，到时候你可以根据你的数据来决定ViewType。
        mViewTypeBeanList = new ArrayList<>();
        for (int i = 0, j = 0; i < 30; i++, j++) {
            ViewTypeBean viewTypeBean = new ViewTypeBean();
            if (j == 0) {
                viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_NONE);
                viewTypeBean.setContent("我没有菜单");
            } else if (j == 1) {
                viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_SINGLE);
                viewTypeBean.setContent("我有1个菜单");
            } else if (j == 2) {
                viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_MULTI);
                viewTypeBean.setContent("我有2个菜单");
            } else if (j == 3) {
                viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_LEFT);
                viewTypeBean.setContent("我的左边有菜单，右边没有");
                j = -1;
            }
            mViewTypeBeanList.add(viewTypeBean);
        }

        SwipeMenuRecyclerView swipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new ListViewDecoration());

        swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);

        MenuViewTypeAdapter menuAdapter = new MenuViewTypeAdapter(mViewTypeBeanList);
        menuAdapter.setOnItemClickListener(onItemClickListener);

        swipeMenuRecyclerView.setAdapter(menuAdapter);
    }

    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_60);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_NONE) {// 根据Adapter的ViewType来决定菜单的样式、颜色等属性、或者是否添加菜单。
                // Do nothing.
            } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_SINGLE) {// 需要添加单个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_action_wechat)
                        .setText("微信")
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(wechatItem);
                swipeRightMenu.addMenuItem(wechatItem);

            } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_MULTI) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_action_wechat)
                        .setText("微信")
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(wechatItem);
                swipeRightMenu.addMenuItem(wechatItem);

                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_green)
                        .setImage(R.mipmap.ic_action_add)
                        .setText("添加")
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(addItem);
                swipeRightMenu.addMenuItem(addItem);
            } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_LEFT) {
                SwipeMenuItem wechatItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_action_wechat)
                        .setText("嘻嘻")
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(wechatItem);
            }
        }
    };

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(mContext, "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
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
