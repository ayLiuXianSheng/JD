package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.IShoppingCartView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.ShoppingCartSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/4.
 */

public class ShoppingCartPresenter extends BasePresenter<IShoppingCartView> implements IShoppingCartPresenter {
    private ITotalModle iTotalModle;

    public ShoppingCartPresenter(IShoppingCartView iShoppingCartView) {
        super.attachView(iShoppingCartView);
        iTotalModle = new TotalModle();
    }

    @Override
    public void shoppingCart(String path, String uid, String android) {
        iTotalModle.shoppingCart(path,uid,android,this);
    }

    @Override
    public void onFailed(String str) {
        if (view != null){
            view.onFailed(str);
        }
    }

    @Override
    public void onSuccess(List<ShoppingCartSuperClass.DataBean> data) {
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
