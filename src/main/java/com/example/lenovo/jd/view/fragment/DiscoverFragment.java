package com.example.lenovo.jd.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.DiscoverPresenter;
import com.example.lenovo.jd.view.activity.IDiscoverView;
import com.example.lenovo.jd.view.adapter.DiscoverAdapter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseFragment;
import com.example.lenovo.jd.view.bean.DiscoverSuperClass;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现
 */

public class DiscoverFragment extends BaseFragment<DiscoverPresenter> implements View.OnClickListener,IDiscoverView {
    private View view;
    private ImageView mImageGrid;
    private ImageView mImageLv;
    /**
     * 请输入关键字
     */
    private EditText mDiscoverEditText;
    /**
     * 搜索
     */
    private Button mDiscoverBtnSearch;
    private XRecyclerView mDiscoverRecycleView;
    private DiscoverAdapter adapter;
    private int page = 1;
    private String keywords;
    private List<DiscoverSuperClass.DataBean> listAll;

    @Override
    protected int getLayoutId() {
        return R.layout.discover_fragment;
    }

    @Override
    protected DiscoverPresenter getPresenter() {
        presenter = new DiscoverPresenter(this);
        return presenter;
    }

    @Override
    protected void initView(View view) {

        mImageGrid = (ImageView) view.findViewById(R.id.image_grid);
        mImageLv = (ImageView) view.findViewById(R.id.image_linear);
        mDiscoverEditText = (EditText) view.findViewById(R.id.discover_edit_text);
        mDiscoverBtnSearch = (Button) view.findViewById(R.id.discover_btn_search);
        mDiscoverBtnSearch.setOnClickListener(this);
        mDiscoverRecycleView = (XRecyclerView) view.findViewById(R.id.discover_recycle_view);
        adapter = new DiscoverAdapter(getContext());

    }

    @Override
    protected void getData() {
        presenter.discover(Api.HOME_NAME,"电脑",page + "","android");

        mDiscoverRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDiscoverRecycleView.setAdapter(adapter);

        //XRecyclerView的上下拉监听方法
        mDiscoverRecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            //下拉刷新
            public void onRefresh() {
                page = 1;
                listAll.clear();
                presenter.discover(Api.HOME_NAME,keywords,page + "","android");
                mDiscoverRecycleView.refreshComplete();
            }

            @Override
            //上拉加载
            public void onLoadMore() {
                page++;
                presenter.discover(Api.HOME_NAME,keywords,page + "","android");
                mDiscoverRecycleView.refreshComplete();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.discover_btn_search:
                page = 1;
                keywords = mDiscoverEditText.getText().toString().trim();
                presenter.discover(Api.HOME_NAME, keywords,page + "","android");
                break;
            case R.id.image_grid:

                break;
            case R.id.image_linear:

                break;
        }
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(getContext(),str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(List<DiscoverSuperClass.DataBean> data) {
//        Toast.makeText(getContext(),data.size() + "--",Toast.LENGTH_LONG).show();
        if (data != null){
            if (page == 1){
                listAll = new ArrayList<>();
            }
            listAll.addAll(data);
            adapter.setList(listAll);
        }
    }
}
