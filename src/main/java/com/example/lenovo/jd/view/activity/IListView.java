package com.example.lenovo.jd.view.activity;


import com.example.lenovo.jd.view.bean.ListSuperClass;

import java.util.List;

public interface IListView {
    void onFailed(String str);

    void onSuccess(List<ListSuperClass.DataBean> data);
}
