package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.IDiscoverView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.DiscoverSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/13.
 */

public class DiscoverPresenter extends BasePresenter<IDiscoverView> implements IDiscoverPresenter {
    private ITotalModle iTotalModle;

    public DiscoverPresenter(IDiscoverView iDiscoverView) {
        super.attachView(iDiscoverView);
        iTotalModle = new TotalModle();
    }

    @Override
    public void discover(String path, String keywords, String page, String android) {
        iTotalModle.discover(path,keywords,page,android,this);
    }

    @Override
    public void onFailed(String str) {
        if (view != null){
            view.onFailed(str);
        }
    }

    @Override
    public void onSuccess(List<DiscoverSuperClass.DataBean> data) {
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
