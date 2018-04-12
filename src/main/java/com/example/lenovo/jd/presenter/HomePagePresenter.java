package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.IHomePageView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public class HomePagePresenter extends BasePresenter<IHomePageView> implements IHomePagePresenter {
    private ITotalModle iTotalModle;
    public HomePagePresenter(IHomePageView iHomePageView) {
        super.attachView(iHomePageView);
        iTotalModle = new TotalModle();
    }

    @Override
    public void homePage(String path) {
        iTotalModle.homePage(path,this);
    }

    @Override
    public void middle(String path) {
        iTotalModle.middle(path,this);
    }

    @Override
    public void onFailed(String str) {
        if (view != null){
            view.onFailed(str);
        }
    }

    @Override
    public void onSuccess(HomePageSuperClass homePageSuperClass) {
        if (view != null){
            view.onSuccess(homePageSuperClass);
        }
    }

    @Override
    public void onMiddleSuccess(List<ClassifyLeftSuperClass.DataBean> data) {
        if (view != null){
            view.onMiddleSuccess(data);
        }
    }

    @Override
    public void onDestory() {
        if (view != null){
            view = null;
        }
    }
}
