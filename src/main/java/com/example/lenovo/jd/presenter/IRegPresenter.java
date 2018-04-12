package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.view.bean.RegisterSuperClass;

/**
 * Created by lenovo on 2018/1/6.
 */

public interface IRegPresenter {

    void reg(String path,String mobile, String password);

    void onFailed(String msg);

    void onSuccess(RegisterSuperClass registerSuperClass);

    void onDestory();

}
