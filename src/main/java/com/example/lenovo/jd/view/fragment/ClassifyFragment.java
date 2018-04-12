package com.example.lenovo.jd.view.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.ClassifyPresenter;
import com.example.lenovo.jd.view.activity.IClassifyView;
import com.example.lenovo.jd.view.adapter.LeftAdapter;
import com.example.lenovo.jd.view.adapter.RightAdapter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseFragment;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;

import java.util.List;

/**
 * 分类
 */

public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements IClassifyView {

    private RecyclerView left_recycle,right_recycle;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.classify_fragment;
    }

    @Override
    protected ClassifyPresenter getPresenter() {
        presenter = new ClassifyPresenter(this);
        return presenter;
    }

    @Override
    protected void initView(View view) {
        left_recycle = view.findViewById(R.id.left_recycle);
        right_recycle = view.findViewById(R.id.right_recycle);
        leftAdapter = new LeftAdapter(getContext());
        rightAdapter = new RightAdapter(getContext());
    }

    @Override
    protected void getData() {
        presenter.left(Api.HOME_NAME);

        left_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        /*left_recycle.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));*/

        left_recycle.setAdapter(leftAdapter);

        right_recycle.setLayoutManager(new LinearLayoutManager(getContext()));

        right_recycle.setAdapter(rightAdapter);

        leftAdapter.setOnItemClickedListen(new LeftAdapter.OnItemClickedListen() {
            @Override
            public void OnItemClicked(String cid) {
                presenter.right(Api.HOME_NAME,cid);
            }
        });
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(getContext(),str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLeftSuccess(List<ClassifyLeftSuperClass.DataBean> data) {
//        Toast.makeText(getContext(),data.size() + "---",Toast.LENGTH_LONG).show();
        if (data != null){
            leftAdapter.setList(data);
            presenter.right(Api.HOME_NAME,data.get(0).getCid() + "");
        }
    }

    @Override
    public void onRightSuccess(List<ClassifyRightSuperClass.DataBean> data) {
        if (data != null){
            rightAdapter.setList(data);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
