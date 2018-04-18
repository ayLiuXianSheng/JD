package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.CreateOrderPresenter;
import com.example.lenovo.jd.view.alipay.AlipayTask;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseActivity;

/**
 * 选择支付方式
 */
public class ModeOfPaymentActivity extends BaseActivity<CreateOrderPresenter> implements ICreateOrderView {
    private CheckBox checkWeChatPay;
    private CheckBox checkPayByAlipay;
    private TextView text_to_pay_for;
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
        checkWeChatPay = (CheckBox) findViewById(R.id.check_WeChat_Pay);
        checkPayByAlipay = (CheckBox) findViewById(R.id.check_pay_by_Alipay);
        text_to_pay_for = (TextView) findViewById(R.id.text_to_pay_for);
        mSharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        String uid = mSharedPreferences.getString("uid", "");
        presenter.createOrder(Api.HOME_NAME, uid, price);

        boolean weChatPay = checkWeChatPay.isChecked();
        if (weChatPay){
            checkWeChatPay.setChecked(true);
            checkPayByAlipay.setChecked(false);
        }

        boolean payByAlipay = checkPayByAlipay.isChecked();
        if (payByAlipay){
            checkWeChatPay.setChecked(false);
            checkPayByAlipay.setChecked(true);
        }

        checkWeChatPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkWeChatPay.setChecked(true);
                    checkPayByAlipay.setChecked(false);
                }
            }
        });

        checkPayByAlipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkWeChatPay.setChecked(false);
                    checkPayByAlipay.setChecked(true);
                }
            }
        });

        text_to_pay_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = checkWeChatPay.isChecked();
                if (checked){
                    //微信支付
                    Toast.makeText(ModeOfPaymentActivity.this,"微信支付",Toast.LENGTH_LONG).show();
                }else {
                    //支付宝支付
                    Toast.makeText(ModeOfPaymentActivity.this,"支付宝支付",Toast.LENGTH_LONG).show();
                    new AlipayTask(ModeOfPaymentActivity.this, 0).execute("小米 MIX 2s", "全面屏2.0，骁龙835处理器，全陶瓷机身。", "0.01");
                }
            }
        });
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSucces(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
