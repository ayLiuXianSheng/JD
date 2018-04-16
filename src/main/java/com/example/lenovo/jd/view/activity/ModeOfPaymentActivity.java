package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.CreateOrderPresenter;
import com.example.lenovo.jd.view.alipay.OrderInfoUtil2_0;
import com.example.lenovo.jd.view.alipay.PayResult;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseActivity;

import java.util.Map;

/**
 * 选择支付方式
 */
public class ModeOfPaymentActivity extends BaseActivity<CreateOrderPresenter> implements ICreateOrderView {
    private CheckBox checkWeChatPay;
    private CheckBox checkPayByAlipay;
    private TextView text_to_pay_for;
    private SharedPreferences mSharedPreferences;
    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNYm+oveZOECAjwrH1E+RHznGxVqdAKI/teijarKYIV7RjpNyfMaEaI0ms8vd9aXtN6gEeSPvBQmWVunY1FWfLpAOkSYGJLJ8GJEgiNTAstCgkHw21DaojrD9LxoUZbvfBwWXiDLDAPUGiU6pnG7AkClJuzSETMCTWsrcB35Y9MMprnPaXgNG8+MJ6P2Z1xmN51uNQw4Z99iDrR27lrQH/OXNzLnRDzlj0rwoYFHDSPds58qmjVRTcBXCVpZoLmuf4OfSc8gplNGz/qs/rjOfKEOrcZQeKw1SCkG5U4ZHsMM5XmwbCGg20G9+BokYdHJNKFKu/+kwu69No1Mcy8RTfAgMBAAECggEAIXBCkFo5egT+VPbbN+d4ejMtWI/yBo6RW80klHN44Ug89cQsGcqXG6N07V6ZgiPMceUCVrNUN6UIeZ0cD/n8DoHACr8Hz/Wptr4mAVErD6ecRs7BYyzULJO0dKuDFzzThBPFkO0HcLAMMeQvzSsTQbLfRC1nwS4FyHGELwE+e0IQy3wug7jAid/X2crGC438pwxS7iCjZxsO44WCteCLTjIG/y2AR42wJXSRlPpsGQP6CVgUKa1ATEsoGBDoImDAitnPAyADyOvRMf3jqOcadWq8MtXKPM1KyfM1Sq+NgPawwXxdBHPXB4aDPHmoZm3qb8Nat1VkbTfnmnFNVNiGAQKBgQDGcR0xEI/oP/HRdhKQJCNguUN2dcXIfbfLj4ff9yMtQ+086W3BpJYO5rq6B8mXU66wg3crKJHwpaQ5a6CXb1U757y2J2qPccKdy3ZXed7z0bEkGxwPzwkAiNXM30KvHO9QxVFX3oILDca2qOk7h5vRrRCH9GHdZkYgf7F0WRFwnwKBgQC2ZKYOVPE881ek0SFHURuTN99M+MsciyLzJNeRpopXCBvViRV3rMvyzCRsciJEqQmZnQM7VDkqh3MtutEDnPv2Qux3Qlhk756Q8PdmS9hPl9WK8NGSSA6AQFGqrV16ngjYRm1h+fm6c6K9YFaoJXw/5qYF48X0hXRE39++TXSzwQKBgBnji/Fovb2JCh1PkCBp9ouZ3+lGeCUt8ZqHAS0A6v/uyraVpZILzN/ozheTCIPLkRDKNfPVeSSyF3i+R9c52R7VntMM1WQdbUx0zN2gsquQgdG6D7EoS35cW7g8sFB0L+yTsYcLKmASzgfqhXMUwAlc0LlL8rCVtTRsNFR/gjz1AoGAUiANmSRsHvqe+wpjRp5hoS8mL51Srz6C9SIgomdvoPJ4vfRkoyc+Ccwblmzpuyq1tOI640rwFpM4rF2S4WKdHOxTVvubm489QZwOeZQrCOOf9liqtIgXZ24Ol6BKF/zylJdZhyUsaeTJYSXwvvNp98fd94bwykIQ8TYwo5pyssECgYAZC+l1Ok0VJyisBLgOHoAuwYmWbFRC0RJAwQQoTs4/ozHiR+kFOgiHY6W7sjfgdMej+0U0gNifm2nn0lj1KRuOXiAzkzRBTkiwDChP0PAa2ns9GSbxApRVPJJzeM2NlRX4ptscjKUqWB3tgqPNWDTjW0d7iCYeFWkx0GfRgSwHaQ==";
    public static final String APPID = "2017062807585767";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(ModeOfPaymentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModeOfPaymentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

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
                    //秘钥验证的类型 true:RSA2 false:RSA
                    boolean rsa = false;
                    //构造支付订单参数列表
                    Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
                    //构造支付订单参数信息
                    String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                    //对支付参数信息进行签名
                    String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
                    //订单信息
                    final String orderInfo = orderParam + "&" + sign;
                    //异步处理
                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            //新建任务
                            PayTask alipay = new PayTask(ModeOfPaymentActivity.this);
                            //获取支付结果
                            Map<String, String> result = alipay.payV2(orderInfo, true);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
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
