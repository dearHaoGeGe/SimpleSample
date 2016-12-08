package com.my.simplesampletest.logger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.logger.adapter.MainItemAdapter;
import com.my.simplesampletest.logger.listener.OnItemClickListener;
import com.my.simplesampletest.logger.view.ListViewDecoration;
import com.my.simplesampletest.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * 了解Logger、
 * RecyclerView侧滑菜单，长按拖拽，滑动删除，自动加载更多，和ViewPager结合使用，和任何下拉刷新框架结合使用。
 * 参考：https://github.com/yanzhenjie/SwipeRecyclerView
 * <p>
 * Created by YJH on 2016/6/15.
 */
public class LoggerActivity extends BaseActivity implements OnItemClickListener {

    private RecyclerView recyclerView;
    private List<String> titles;
    private List<String> descriptions;
    private MainItemAdapter mMainItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MenuTextAllCaps);
        setContentView(R.layout.activity_logger);

        initView();
        initData();
    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("侧滑RecyclerView以及Logger");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListViewDecoration());
    }

    @Override
    public void initData() {
        titles = Arrays.asList(getResources().getStringArray(R.array.main_item));
        descriptions = Arrays.asList(getResources().getStringArray(R.array.main_item_des));
        mMainItemAdapter = new MainItemAdapter(titles, descriptions);
        mMainItemAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mMainItemAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logger_act, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_show_logger:
                ToastUtil.showToast(this, "看Log");
                String json = getString(R.string.json_data);
                Log.i("json数据为", json);
                Logger.json("999", json);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, AllMenuActivity.class));     //两侧菜单
                break;
            case 1:
                startActivity(new Intent(this, ViewTypeMenuActivity.class));    //ViewType菜单
                break;
            case 2:
                startActivity(new Intent(this, ViewPagerMenuActivity.class));   //和ViewPager嵌套
                break;
            case 3:
                startActivity(new Intent(this, RefreshLoadMoreActivity.class));     //下拉刷新，加载更多
                break;
            case 4:
                startActivity(new Intent(this, ListDragMenuActivity.class));    //拖拽Item条目+菜单(List)
                break;
            case 5:
                startActivity(new Intent(this, GridDragMenuActivity.class));    //拖拽Item条目+菜单(Grid)
                break;
            case 6:
                startActivity(new Intent(this, ListDragSwipeActivity.class));   //滑动直接删除+拖拽Item条目
                break;
            case 7:
                startActivity(new Intent(this, DragSwipeFlagsActivity.class));  //指定Item不能拖拽/滑动删除
                break;
            case 8:
                startActivity(new Intent(this, VerticalMenuActivity.class));    //竖型菜单
                break;
            case 9:
                startActivity(new Intent(this, DefineActivity.class));      //用SwipeMenuLayout实现你自己的侧滑
                break;
        }
    }


}
