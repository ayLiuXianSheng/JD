package com.example.lenovo.jd.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.base.BasePresenter;

public class SearchActivity extends BaseActivity {

    private ImageView image_return;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        image_return = findViewById(R.id.image_return);
        image_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void getData() {

    }
}
