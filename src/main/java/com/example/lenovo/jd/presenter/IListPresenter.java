package com.example.lenovo.jd.presenter;


import com.example.lenovo.jd.view.bean.ListSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/3.
 */

public interface IListPresenter {
    void list(String path, String pscid, String page);

    void onFailed(String str);

    void onSuccess(List<ListSuperClass.DataBean> data);

    void onDestory();
}
