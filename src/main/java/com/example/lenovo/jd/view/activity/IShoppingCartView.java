package com.example.lenovo.jd.view.activity;


import com.example.lenovo.jd.view.bean.ShoppingCartSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/4.
 */

public interface IShoppingCartView {
    void onFailed(String str);

    void onSuccess(List<ShoppingCartSuperClass.DataBean> data);
}
