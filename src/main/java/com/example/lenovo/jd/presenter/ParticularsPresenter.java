package com.example.lenovo.jd.presenter;


import com.example.lenovo.jd.modle.ITotalModle;
import com.example.lenovo.jd.modle.TotalModle;
import com.example.lenovo.jd.view.activity.IParticularsView;
import com.example.lenovo.jd.view.base.BasePresenter;
import com.example.lenovo.jd.view.bean.ParticularsSuperClass;

/**
 * Created by lenovo on 2018/4/3.
 */

public class ParticularsPresenter extends BasePresenter<IParticularsView> implements IParticularsPresenter {
    private ITotalModle iTotalModle;

    public ParticularsPresenter(IParticularsView iParticularsView) {
        super.attachView(iParticularsView);
        iTotalModle = new TotalModle();
    }

    @Override
    public void particulars(String path, String pid, String android) {
        iTotalModle.particulars(path,pid,android,this);
    }

    @Override
    public void onFailed(String str) {
        if (view != null){
            view.onFailed(str);
        }
    }

    @Override
    public void onSuccess(ParticularsSuperClass.DataBean data) {
        if (view != null){
            view.onSuccess(data);
        }
    }

    @Override
    public void onDestory() {
        if (view != null){
            view = null;
        }
    }
}
