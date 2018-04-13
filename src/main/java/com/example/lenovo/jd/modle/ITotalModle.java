package com.example.lenovo.jd.modle;

import com.example.lenovo.jd.presenter.IClassifyPresenter;
import com.example.lenovo.jd.presenter.IDiscoverPresenter;
import com.example.lenovo.jd.presenter.IHomePagePresenter;
import com.example.lenovo.jd.presenter.IListPresenter;
import com.example.lenovo.jd.presenter.ILoginPresenter;
import com.example.lenovo.jd.presenter.IParticularsPresenter;
import com.example.lenovo.jd.presenter.IRegPresenter;
import com.example.lenovo.jd.presenter.IShoppingCartPresenter;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface ITotalModle {
    void left(String path, IClassifyPresenter iClassifyPresenter);

    void right(String path,String cid, IClassifyPresenter iClassifyPresenter);

    void homePage(String path,IHomePagePresenter iHomePagePresenter);

    void middle(String path,IHomePagePresenter iHomePagePresenter);

    void list(String path,String pscid,String page, IListPresenter iListPresenter);

    void particulars(String path, String pid, String android, IParticularsPresenter iParticularsPresenter);

    void addToCar(String path, String uid, String pid, String android, IParticularsPresenter iParticularsPresenter);

    void discover(String path,String keywords,String page,String android, IDiscoverPresenter iDiscoverPresenter);

    void shoppingCart(String path, String uid, String android, IShoppingCartPresenter iShoppingCartPresenter);

    void login(String path,String mobile,String password, ILoginPresenter iLoginPresenter);

    void reg(String path,String mobile,String password, IRegPresenter iRegPresenter);
}
