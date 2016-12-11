package com.my.simplesampletest.tablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;
import com.my.simplesampletest.logger.entity.ViewTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 在Fragment上面添加Fragment并且传值
 * <p>
 * Created by YJH on 2016/12/9 16:04.
 */

public class FragmentToFragmentAct extends BaseActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName() + "--->";
    private TextView tv_one;
    private TextView tv_two;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_to_fragment);

        initView();
        initData();
    }

    @Override
    public void initView() {
        tv_one = (TextView) findViewById(R.id.tv_one_fgm_to_fgm);
        tv_two = (TextView) findViewById(R.id.tv_two_fgm_to_fgm);
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new OneTabLFragment());
        fragmentList.add(new TwoTabLFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one_fgm_to_fgm:
                Bundle data0 = new Bundle();
                data0.putSerializable("data0", new ViewTypeBean("第一个Fragment", 100));
                goNextFragment(fragmentList.get(0), data0);
                break;

            case R.id.tv_two_fgm_to_fgm:
                Bundle data1 = new Bundle();
                data1.putSerializable("data1", new ViewTypeBean("第二个Fragment", 200));
                goNextFragment(fragmentList.get(1), data1);
                break;
        }
    }

    /**
     * 跳转Fragment
     *
     * @param fragment 跳转的Fragment
     */
    public void goNextFragment(Fragment fragment, Bundle data) {
        if (null == fragment) {
            return;
        } else {
            if (null != data) {
                fragment.setArguments(data);
            }

            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.frameLayout_fgm_to_fgm, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    /**
     * 返回上一个Fragment
     */
    public void goBackFragment() {
        manager.popBackStack();
    }

}
