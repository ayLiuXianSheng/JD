package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.view.bean.DiscoverSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/13.
 */

public interface IDiscoverPresenter {
    void discover(String path,String keywords,String page, String android);

    void onFailed(String str);

    void onSuccess(List<DiscoverSuperClass.DataBean> data);

    void onDestory();
}
