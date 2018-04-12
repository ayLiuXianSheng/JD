package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.ILoginView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.LoginSuperClass;

/**
 * Created by lenovo on 2018/1/6.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter{
    private ITotalModle iTotalModle;


    public LoginPresenter(ILoginView iLoginView) {
        super.attachView(iLoginView);
        iTotalModle = new TotalModle();
    }

    @Override
    public void login(String path, String mobile, String password) {
        iTotalModle.login(path,mobile,password,this);
    }

    @Override
    public void onFailed(String msg) {
        if (view != null){
            view.onFailed(msg);
        }
    }

    @Override
    public void onSccuess(LoginSuperClass loginSuperClass) {
        if (view != null){
            String code = loginSuperClass.getCode();
            if ("0".equals(code)){
                view.onSccuess(loginSuperClass.getMsg());
            }else{
                view.onFailed(loginSuperClass.getMsg());
            }
        }
    }

    @Override
    public void   onDestory() {
        if (view != null){
            view = null;
        }
    }
}
