package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface IHomePagePresenter {
    void homePage(String path);

    void middle(String path);

    void onFailed(String str);

    void onSuccess(HomePageSuperClass homePageSuperClass);

    void onMiddleSuccess(List<ClassifyLeftSuperClass.DataBean> data);

    void onDestory();
}
