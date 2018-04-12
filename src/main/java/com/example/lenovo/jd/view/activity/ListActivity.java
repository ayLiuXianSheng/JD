package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.ListPresenter;
import com.example.lenovo.jd.view.adapter.ListAdapter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.bean.ListSuperClass;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
/**
 * 商品列表
 * */
public class ListActivity extends BaseActivity<ListPresenter> implements IListView {

    private XRecyclerView list_recycle;
    private ListAdapter adapter;
    private int page = 1;
    private List<ListSuperClass.DataBean> listAll;
    private String pscid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected ListPresenter getPresenter() {
        presenter = new ListPresenter(this);
        return presenter;
    }

    @Override
    protected void initView() {
        list_recycle = findViewById(R.id.list_recycle);
        adapter = new ListAdapter(this);
    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        pscid = intent.getStringExtra("pscid");
        presenter.list(Api.HOME_NAME, pscid,page + "");

        list_recycle.setLayoutManager(new LinearLayoutManager(this));
        list_recycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        list_recycle.setAdapter(adapter);

        //XRecyclerView的上下拉监听方法
        list_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            //下拉刷新
            public void onRefresh() {
                page = 1;
                presenter.list(Api.HOME_NAME, pscid,page + "");
                list_recycle.refreshComplete();
            }

            @Override
            //上拉加载
            public void onLoadMore() {
                page++;
                presenter.list(Api.HOME_NAME, pscid,page + "");
                list_recycle.refreshComplete();
            }
        });
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(this,str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(List<ListSuperClass.DataBean> data) {
        if (data != null){
            if (page == 1){
                listAll = new ArrayList<>();
            }
            listAll.addAll(data);
            adapter.setList(listAll);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}

