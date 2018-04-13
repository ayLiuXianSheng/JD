package com.example.lenovo.jd.view.activity;


import com.example.lenovo.jd.view.bean.ParticularsSuperClass;

/**
 * Created by lenovo on 2018/4/3.
 */

public interface IParticularsView {
    void onFailed(String str);

    void onSuccess(ParticularsSuperClass.DataBean data);

    void onAddToCarSuccess(String msg);
}
