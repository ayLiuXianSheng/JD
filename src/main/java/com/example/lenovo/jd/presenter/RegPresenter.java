package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.IRegisterView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.RegisterSuperClass;
/*
 * Created by lenovo on 2018/1/6.
 */

public class RegPresenter extends BasePresenter<IRegisterView> implements IRegPresenter{

    private ITotalModle iTotalModle;

    public RegPresenter(IRegisterView iRegisterView) {
        super.attachView(iRegisterView);
        iTotalModle = new TotalModle();
    }

    public void reg(String path,String mobile, String password){
        iTotalModle.reg(path,mobile,password,this);
    }

    @Override
    public void onFailed(String msg) {
        if (view != null){
            view.onFailed(msg);
        }
    }

    @Override
    public void onSuccess(RegisterSuperClass registerSuperClass) {
        if (view != null){
            String code = registerSuperClass.getCode();
            if ("0".equals(code)){
                view.onSuccess(registerSuperClass.getMsg());
            }else{
                view.onFailed(registerSuperClass.getMsg());
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
