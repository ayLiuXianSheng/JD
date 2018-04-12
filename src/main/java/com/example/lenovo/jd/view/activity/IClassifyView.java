package com.example.lenovo.jd.view.activity;

import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface IClassifyView {
    void onFailed(String str);

    void onLeftSuccess(List<ClassifyLeftSuperClass.DataBean> data);

    void onRightSuccess(List<ClassifyRightSuperClass.DataBean> data);
}
