package com.example.lenovo.jd.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.jd.R;

/**
 * Created by lenovo on 2018/4/9.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(getLayoutId());
        presenter = getPresenter();
        initView();
        getData();
    }

    protected abstract int getLayoutId();

    protected abstract T getPresenter();

    protected abstract void initView();

    protected abstract void getData();
}
