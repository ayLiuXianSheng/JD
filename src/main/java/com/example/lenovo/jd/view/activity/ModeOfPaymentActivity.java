package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.base.BasePresenter;
/**
 * 选择支付方式
 * */
public class ModeOfPaymentActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mode_of_payment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        Toast.makeText(this,price,Toast.LENGTH_LONG).show();
    }
}
