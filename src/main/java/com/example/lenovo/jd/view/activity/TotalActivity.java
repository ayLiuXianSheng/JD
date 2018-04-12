package com.example.lenovo.jd.view.activity;

import android.graphics.Color;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.fragment.ClassifyFragment;
import com.example.lenovo.jd.view.fragment.DiscoverFragment;
import com.example.lenovo.jd.view.fragment.HomePageFragment;
import com.example.lenovo.jd.view.fragment.MineFragment;
import com.example.lenovo.jd.view.fragment.ShoppingCartFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class TotalActivity extends BaseActivity {

    private BottomTabBar bottomTabBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_total;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        bottomTabBar = findViewById(R.id.bottomTabBar);
    }

    @Override
    protected void getData() {
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(14)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED, Color.BLACK)
                .addTabItem("首页", R.mipmap.home, HomePageFragment.class)
                .addTabItem("分类", R.mipmap.liebiao, ClassifyFragment.class)
                .addTabItem("发现", R.mipmap.find, DiscoverFragment.class)
                .addTabItem("购物车", R.mipmap.shoppingcart, ShoppingCartFragment.class)
                .addTabItem("我的", R.mipmap.mine, MineFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }
}
