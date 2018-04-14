package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.CreateOrderPresenter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseActivity;

/**
 * 选择支付方式
 * */
public class ModeOfPaymentActivity extends BaseActivity<CreateOrderPresenter> implements ICreateOrderView {
    private SharedPreferences mSharedPreferences;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mode_of_payment;
    }

    @Override
    protected CreateOrderPresenter getPresenter() {
        presenter = new CreateOrderPresenter(this);
        return presenter;
    }

    @Override
    protected void initView() {
        mSharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        String uid = mSharedPreferences.getString("uid", "");
        presenter.createOrder(Api.HOME_NAME,uid,price);
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSucces(String str) {
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
