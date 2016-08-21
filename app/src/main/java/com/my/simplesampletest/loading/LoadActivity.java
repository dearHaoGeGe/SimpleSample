package com.my.simplesampletest.loading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.my.simplesampletest.R;
import com.my.simplesampletest.loading.loadingdrawable.CollisionLoadingRenderer;
import com.my.simplesampletest.loading.loadingdrawable.GearLoadingRenderer;
import com.my.simplesampletest.loading.loadingdrawable.LevelLoadingRenderer;
import com.my.simplesampletest.loading.loadingdrawable.LoadingDrawable;
import com.my.simplesampletest.loading.loadingdrawable.MaterialLoadingRenderer;
import com.my.simplesampletest.loading.loadingdrawable.SwapLoadingRenderer;
import com.my.simplesampletest.loading.loadingdrawable.WhorlLoadingRenderer;

/**
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
}
