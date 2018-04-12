package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface IClassifyPresenter {
    void left(String path);

    void right(String path,String cid);

    void onFailed(String str);

    void onLeftSuccess(List<ClassifyLeftSuperClass.DataBean> data);

    void onRightSuccess(List<ClassifyRightSuperClass.DataBean> data);

    void onDestory();
}
