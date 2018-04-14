package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.view.bean.CreateOrderSuperClass;

/**
 * Created by lenovo on 2018/4/14.
 */

public interface ICreateOrderPresenter {
    void createOrder(String path, String uid,String price);

    void onFailed(String msg);

    void onSucces(CreateOrderSuperClass createOrderSuperClass);

    void onDestory();
}
