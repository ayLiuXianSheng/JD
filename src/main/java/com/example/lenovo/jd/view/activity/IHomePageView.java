package com.example.lenovo.jd.view.activity;

import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface IHomePageView {
    void onFailed(String str);

    void onSuccess(HomePageSuperClass homePageSuperClass);

    void onMiddleSuccess(List<ClassifyLeftSuperClass.DataBean> data);
}
