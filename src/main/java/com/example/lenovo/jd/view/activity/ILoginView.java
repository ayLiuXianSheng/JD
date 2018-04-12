package com.example.lenovo.jd.view.activity;

import com.example.lenovo.jd.view.bean.LoginSuperClass;

/**
 * Created by lenovo on 2018/4/12.
 */

public interface ILoginView {
    void onFailed(String msg);

    void onSccuess(LoginSuperClass loginSuperClass);
}
