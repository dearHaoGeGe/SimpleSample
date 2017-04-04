package com.my.simplesampletest.loading_and_annotation;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.loading_and_annotation.annotation.CustomUtils;
import com.my.simplesampletest.loading_and_annotation.annotation.Person;
import com.my.simplesampletest.loading_and_annotation.loadingdrawable.CollisionLoadingRenderer;
import com.my.simplesampletest.loading_and_annotation.loadingdrawable.GearLoadingRenderer;
import com.my.simplesampletest.loading_and_annotation.loadingdrawable.LevelLoadingRenderer;
import com.my.simplesampletest.loading_and_annotation.loadingdrawable.LoadingDrawable;
import com.my.simplesampletest.loading_and_annotation.loadingdrawable.MaterialLoadingRenderer;
import com.my.simplesampletest.loading_and_annotation.loadingdrawable.SwapLoadingRenderer;
import com.my.simplesampletest.loading_and_annotation.loadingdrawable.WhorlLoadingRenderer;

/**
 * 一些加载动画、获取APP当前的版本、自定义Annotation
 * <p/>
 * Created by YJH on 2016/4/20.
 */
public class LoadActivity extends AppCompatActivity {

    private LoadingDrawable mSwapDrawable;
    private LoadingDrawable mGearDrawable;
    private LoadingDrawable mWhorlDrawable;
    private LoadingDrawable mLevelDrawable;
    private LoadingDrawable mMaterialDrawable;
    private LoadingDrawable mCollisionDrawable;

    private ImageView mIvSwap;
    private ImageView mIvGear;
    private ImageView mIvWhorl;
    private ImageView mIvLevel;
    private ImageView mIvMaterial;
    private ImageView mIvCollision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        mIvSwap = (ImageView) findViewById(R.id.swap_view);
        mIvGear = (ImageView) findViewById(R.id.gear_view);
        mIvWhorl = (ImageView) findViewById(R.id.whorl_view);
        mIvLevel = (ImageView) findViewById(R.id.level_view);
        mIvMaterial = (ImageView) findViewById(R.id.material_view);
        mIvCollision = (ImageView) findViewById(R.id.collision_view);

        mSwapDrawable = new LoadingDrawable(new SwapLoadingRenderer(this));
        mGearDrawable = new LoadingDrawable(new GearLoadingRenderer(this));
        mWhorlDrawable = new LoadingDrawable(new WhorlLoadingRenderer(this));
        mLevelDrawable = new LoadingDrawable(new LevelLoadingRenderer(this));
        mMaterialDrawable = new LoadingDrawable(new MaterialLoadingRenderer(this));
        mCollisionDrawable = new LoadingDrawable(new CollisionLoadingRenderer(this));

        mIvSwap.setImageDrawable(mSwapDrawable);
        mIvGear.setImageDrawable(mGearDrawable);
        mIvWhorl.setImageDrawable(mWhorlDrawable);
        mIvLevel.setImageDrawable(mLevelDrawable);
        mIvMaterial.setImageDrawable(mMaterialDrawable);
        mIvCollision.setImageDrawable(mCollisionDrawable);


        Toast.makeText(LoadActivity.this, "当前版本:" + getVersionName(), Toast.LENGTH_SHORT).show();

        CustomUtils.getInfo(Person.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSwapDrawable.start();
        mGearDrawable.start();
        mWhorlDrawable.start();
        mLevelDrawable.start();
        mMaterialDrawable.start();
        mCollisionDrawable.start();
    }

    @Override
    protected void onStop() {
        mSwapDrawable.stop();
        mGearDrawable.stop();
        mWhorlDrawable.stop();
        mLevelDrawable.stop();
        mMaterialDrawable.stop();
        mCollisionDrawable.stop();
        super.onStop();
    }

    /**
     * 获取版本名称，从build文件获取
     */
    private String getVersionName() {
        //1.包管理者对象
        PackageManager packageManager = getPackageManager();
        try {
            //2.从包的管理者对象中，获取指定包名的基本信息（版本名称，版本号）传0代表获取基本信息
            PackageInfo info = packageManager.getPackageInfo(getPackageName(), 0);

            return info.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
