package com.example.lenovo.jd.presenter;


import com.example.lenovo.jd.view.bean.AddToCarSuperClass;
import com.example.lenovo.jd.view.bean.ParticularsSuperClass;

/**
 * Created by lenovo on 2018/4/3.
 */

public interface IParticularsPresenter {
    void particulars(String path, String pid, String android);

    void addToCar(String path, String uid, String pid, String android);

    void onFailed(String str);

    void onSuccess(ParticularsSuperClass.DataBean data);

    void onAddToCarSuccess(AddToCarSuperClass addToCarSuperClass);

    void onDestory();
}
