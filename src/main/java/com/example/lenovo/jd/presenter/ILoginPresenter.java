package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.view.bean.LoginSuperClass;

/**
 * Created by lenovo on 2018/1/6.
 */

public interface ILoginPresenter {

    void login(String path, String mobile, String password);

    void onFailed(String msg);

    void onSccuess(LoginSuperClass loginSuperClass);

    void onDestory();

}
