package com.example.lenovo.jd.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.HomePagePresenter;
import com.example.lenovo.jd.view.activity.HomePageParticularsActivity;
import com.example.lenovo.jd.view.activity.IHomePageView;
import com.example.lenovo.jd.view.activity.SearchActivity;
import com.example.lenovo.jd.view.adapter.MiddleAdapter;
import com.example.lenovo.jd.view.adapter.MyAdapterHorizontal;
import com.example.lenovo.jd.view.adapter.MyAdapterVertical;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.banner.AccordionTransformer;
import com.example.lenovo.jd.view.banner.AutoBanner;
import com.example.lenovo.jd.view.base.BaseFragment;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主页
 */

public class HomePageFragment extends BaseFragment<HomePagePresenter> implements AutoBanner.AutoBannerListener, IHomePageView {
    private AutoBanner mAutoBanner;
    private List<String> mImgUrls;
    private RecyclerView middle_recycle;
    private MiddleAdapter middleAdapter;
    private View view;
    private RecyclerView mMiddleRecycle;
    private TextView mTexthView;
    private RecyclerView mRecyclehView;
    private TextView mTextvView;
    private LinearLayout search_richScan,search_skip,search_message;
    private RecyclerView mRecyclevView;
    private MyAdapterHorizontal myAdapterHorizontal;
    private MyAdapterVertical myAdapterVertical;
    private HomePageSuperClass homePageSuperClass;

    @Override
    protected int getLayoutId() {
        return R.layout.home_page_fragment;
    }

    @Override
    protected HomePagePresenter getPresenter() {
        presenter = new HomePagePresenter(this);
        return presenter;
    }

    @Override
    protected void initView(View view) {
        mAutoBanner = (AutoBanner) view.findViewById(R.id.auto_banner);
        middle_recycle = view.findViewById(R.id.middle_recycle);
        mMiddleRecycle = (RecyclerView) view.findViewById(R.id.middle_recycle);
        mTexthView = (TextView) view.findViewById(R.id.texth_view);
        search_richScan = (LinearLayout) view.findViewById(R.id.search_richScan);
        search_skip = (LinearLayout) view.findViewById(R.id.search_skip);
        search_message = (LinearLayout) view.findViewById(R.id.search_message);
        mRecyclehView = (RecyclerView) view.findViewById(R.id.recycleh_view);
        mTextvView = (TextView) view.findViewById(R.id.textv_view);
        mRecyclevView = (RecyclerView) view.findViewById(R.id.recyclev_view);
        middleAdapter = new MiddleAdapter(getContext());
        myAdapterHorizontal = new MyAdapterHorizontal(getContext());
        myAdapterVertical = new MyAdapterVertical(getContext());
        mImgUrls = new ArrayList<>();
    }

    @Override
    protected void getData() {
        presenter.homePage(Api.HOME_NAME);
        presenter.middle(Api.HOME_NAME);

        middle_recycle.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));

        middle_recycle.setAdapter(middleAdapter);
        //扫一扫
        search_richScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "扫一扫", Toast.LENGTH_SHORT).show();
            }
        });
        //跳转到搜索
        search_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
        //消息
        search_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "消息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickListener(int postion) {
        Intent intent = new Intent(getContext(),HomePageParticularsActivity.class);
        intent.putExtra("detailUrl",homePageSuperClass.getData().get(postion).getUrl());
        startActivity(intent);
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(final HomePageSuperClass homePageSuperClass) {
        this.homePageSuperClass = homePageSuperClass;
        List<HomePageSuperClass.DataBean> data = homePageSuperClass.getData();
        for (int i = 0; i < data.size(); i++) {
            mImgUrls.add(data.get(i).getIcon());
        }
        //不设置setAuto，不会自动轮播，不设置监听，无法点击,动画转换器可以根据需要设置
        mAutoBanner.load(mImgUrls).setOnAutoBannerListener(this).setAuto(3000).setAutoBannerAnimation(AccordionTransformer.class).display();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int time = homePageSuperClass.getMiaosha().getTime();
                int newtime = time - 1000;
                homePageSuperClass.getMiaosha().setTime(newtime);
                getActivity().runOnUiThread(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        Date date = new Date(homePageSuperClass.getMiaosha().getTime());
                        String format = sdf.format(date);
                        mTexthView.setText(homePageSuperClass.getMiaosha().getName() + "\t" + format);
                    }
                });
            }
        }, 1000, 1000);

        mTextvView.setText(homePageSuperClass.getTuijian().getName());

        myAdapterHorizontal.setList(homePageSuperClass.getMiaosha().getList());
        myAdapterVertical.setList(homePageSuperClass.getTuijian().getList());

        mRecyclehView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//        recycleh_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclehView.setAdapter(myAdapterHorizontal);

        mRecyclevView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclevView.setAdapter(myAdapterVertical);

    }

    @Override
    public void onMiddleSuccess(List<ClassifyLeftSuperClass.DataBean> data) {
//        Toast.makeText(getContext(), data.size() + "===", Toast.LENGTH_SHORT).show();
        if (data != null) {
            middleAdapter.setList(data);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAutoBanner.startAuto();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAutoBanner.stopAuto();
        presenter.onDestory();
    }
}
