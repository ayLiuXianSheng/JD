package com.example.lenovo.jd.view.activity;

import com.example.lenovo.jd.view.bean.DiscoverSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/13.
 */

public interface IDiscoverView {
    void onFailed(String str);

    void onSuccess(List<DiscoverSuperClass.DataBean> data);
}
