package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.ParticularsPresenter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.banner.AccordionTransformer;
import com.example.lenovo.jd.view.banner.AutoBanner;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.bean.ParticularsSuperClass;

import java.util.ArrayList;
import java.util.List;

public class ParticularsActivity extends BaseActivity<ParticularsPresenter> implements AutoBanner.AutoBannerListener,IParticularsView {
    private ParticularsSuperClass.DataBean dataBean;
    private TextView text_title_view;
    private TextView text_oldprice_view;
    private TextView text_newprice_view;
    private TextView text_addToCar;
    private AutoBanner mAutoBanner;
    private List<String> mImgUrls;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    protected ParticularsPresenter getPresenter() {
        presenter = new ParticularsPresenter(this);
        return presenter;
    }

    @Override
    protected void initView() {

        mAutoBanner = (AutoBanner) findViewById(R.id.auto_banner);
        text_title_view = (TextView) findViewById(R.id.text_title_view);
        text_oldprice_view = (TextView) findViewById(R.id.text_oldprice_view);
        text_newprice_view = (TextView) findViewById(R.id.text_newprice_view);
        text_addToCar = (TextView) findViewById(R.id.text_addToCar);
        mImgUrls = new ArrayList<>();

    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        presenter.particulars(Api.HOME_NAME, pid, "android");
        /*text_addToCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ParticularsActivity.this,AddToCarActivity.class);
                intent1.putExtra("pid",dataBean.getPid() + "");
                startActivity(intent1);
            }
        });*/
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(ParticularsSuperClass.DataBean data) {
        this.dataBean = data;
        String images = dataBean.getImages();
        String[] split = images.split("\\|");

        for (int i = 0; i < split.length ; i++) {
            mImgUrls.add(split[i]);
        }

        //不设置setAuto，不会自动轮播，不设置监听，无法点击,动画转换器可以根据需要设置
        mAutoBanner.load(mImgUrls).setOnAutoBannerListener(this).setAuto(3000).setAutoBannerAnimation(AccordionTransformer.class).display();

        text_title_view.setText(dataBean.getTitle());
        text_oldprice_view.setText("原价：" + dataBean.getPrice());
        text_oldprice_view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        text_newprice_view.setText("优惠价：" + dataBean.getBargainPrice());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }

    @Override
    public void onClickListener(int postion) {

    }
}
