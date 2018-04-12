package com.example.lenovo.jd.presenter;


import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.IListView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.ListSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/3.
 */

public class ListPresenter extends BasePresenter<IListView> implements IListPresenter {
    private ITotalModle iTotalModle;

    public ListPresenter(IListView iListView) {
        super.attachView(iListView);
        iTotalModle = new TotalModle();
    }

    @Override
    public void list(String path, String pscid,String page) {
        iTotalModle.list(path,pscid,page,this);
    }

    @Override
    public void onFailed(String str) {
        if (view != null){
            view.onFailed(str);
        }
    }

    @Override
    public void onSuccess(List<ListSuperClass.DataBean> data) {
        if (view != null){
            view.onSuccess(data);
        }
    }

    @Override
    public void onDestory() {
        if (view != null){
            view = null;
        }
    }
}
