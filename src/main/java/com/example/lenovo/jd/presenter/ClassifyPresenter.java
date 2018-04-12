package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.IClassifyView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public class ClassifyPresenter extends BasePresenter<IClassifyView> implements IClassifyPresenter {
    private ITotalModle iTotalModle;

    public ClassifyPresenter(IClassifyView classifyView) {
        super.attachView(classifyView);
        iTotalModle = new TotalModle();
    }

    @Override
    public void left(String path) {
        iTotalModle.left(path,this);
    }

    @Override
    public void right(String path, String cid) {
        iTotalModle.right(path,cid,this);
    }

    @Override
    public void onFailed(String str) {
        if (view != null){
            view.onFailed(str);
        }
    }

    @Override
    public void onLeftSuccess(List<ClassifyLeftSuperClass.DataBean> data) {
        if (view != null){
            view.onLeftSuccess(data);
        }
    }

    @Override
    public void onRightSuccess(List<ClassifyRightSuperClass.DataBean> data) {
        if (view != null){
            view.onRightSuccess(data);
        }
    }

    @Override
    public void onDestory() {
        if (view != null){
            view = null;
        }
    }
}
