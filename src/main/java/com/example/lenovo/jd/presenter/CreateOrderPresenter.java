package com.example.lenovo.jd.presenter;

import android.util.Log;

import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.ICreateOrderView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.CreateOrderSuperClass;

/**
 * Created by lenovo on 2018/4/14.
 */

public class CreateOrderPresenter extends BasePresenter<ICreateOrderView> implements ICreateOrderPresenter {
    private ITotalModle iTotalModle;

    public CreateOrderPresenter(ICreateOrderView iCreateOrderView) {
        super.attachView(iCreateOrderView);
        iTotalModle = new TotalModle();
    }

    @Override
    public void createOrder(String path, String uid, String price) {
        iTotalModle.createOrder(path,uid,price,this);
    }

    @Override
    public void onFailed(String msg) {
        if (view != null){
            view.onFailed(msg);
        }
    }

    @Override
    public void onSucces(CreateOrderSuperClass createOrderSuperClass) {
        if (view != null){
            String code = createOrderSuperClass.getCode();
            if ("0".equals(code)){
                view.onSucces(createOrderSuperClass.getMsg());
            }else{
                view.onFailed(createOrderSuperClass.getMsg());
            }
        }
    }

    @Override
    public void onDestory() {
        if (view != null){
            view = null;
        }
    }
}
