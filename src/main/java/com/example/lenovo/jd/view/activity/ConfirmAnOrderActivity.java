package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.adapter.ConfirmAnOrderAdapter;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.DataBean;

import java.util.List;
/**
 * 确定订单
 * */
public class ConfirmAnOrderActivity extends BaseActivity {

    private RecyclerView confirm_recycle_layout;
    private TextView mShippingAddress;
    /**
     * 确认订单
     */
    private TextView mConfirmAnOrder;
    private ConfirmAnOrderAdapter adapter;
    private double price = 0.00;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_an_order;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

        confirm_recycle_layout = (RecyclerView) findViewById(R.id.confirm_recycle_layout);
        mShippingAddress = (TextView) findViewById(R.id.shipping_address);
        mConfirmAnOrder = (TextView) findViewById(R.id.confirm_an_order);
        adapter = new ConfirmAnOrderAdapter(this);

    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        final List<DataBean> dataBeans = (List<DataBean>) intent.getSerializableExtra("dataBeans");

        mShippingAddress.setText("北京市海淀区上地七街");

        confirm_recycle_layout.setLayoutManager(new LinearLayoutManager(this));
        confirm_recycle_layout.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        if (dataBeans != null){
            adapter.setList(dataBeans);
        }

        confirm_recycle_layout.setAdapter(adapter);

        mConfirmAnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = 0.00;
                Intent jump = new Intent(ConfirmAnOrderActivity.this,ModeOfPaymentActivity.class);
                for (int i = 0; i < dataBeans.size() ; i++){
                    price += dataBeans.get(i).getBargainPrice() * dataBeans.get(i).getNum();
                }
                jump.putExtra("price",price + "");
                startActivity(jump);
                finish();
            }
        });
    }
}
