package com.example.lenovo.jd.presenter;


import com.example.lenovo.jd.view.bean.ShoppingCartSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/4.
 */

public interface IShoppingCartPresenter {

    void shoppingCart(String path, String uid, String android);

    void onFailed(String str);

    void onSuccess(List<ShoppingCartSuperClass.DataBean> data);

    void onDestory();

}
