package com.example.lenovo.jd.modle;

import com.example.lenovo.jd.presenter.IClassifyPresenter;
import com.example.lenovo.jd.presenter.IHomePagePresenter;
import com.example.lenovo.jd.presenter.IListPresenter;
import com.example.lenovo.jd.presenter.ILoginPresenter;
import com.example.lenovo.jd.presenter.IParticularsPresenter;
import com.example.lenovo.jd.presenter.IRegPresenter;
import com.example.lenovo.jd.presenter.IShoppingCartPresenter;
import com.example.lenovo.jd.view.api.ApiService;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;
import com.example.lenovo.jd.view.bean.ListSuperClass;
import com.example.lenovo.jd.view.bean.LoginSuperClass;
import com.example.lenovo.jd.view.bean.ParticularsSuperClass;
import com.example.lenovo.jd.view.bean.RegisterSuperClass;
import com.example.lenovo.jd.view.bean.ShoppingCartSuperClass;
import com.example.lenovo.jd.view.utils.RetrofitUtils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/4/9.
 */

public class TotalModle implements ITotalModle {

    private RetrofitUtils retrofitUtils;

    @Override
    public void left(String path, final IClassifyPresenter iClassifyPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ClassifyLeftSuperClass> observable = apiService.getLeftData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyLeftSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iClassifyPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ClassifyLeftSuperClass classifyLeftSuperClass) {
                        iClassifyPresenter.onLeftSuccess(classifyLeftSuperClass.getData());
                    }
                });
    }

    @Override
    public void right(String path, String cid, final IClassifyPresenter iClassifyPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ClassifyRightSuperClass> observable = apiService.getRightData(cid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyRightSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iClassifyPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ClassifyRightSuperClass classifyRightSuperClass) {
                        iClassifyPresenter.onRightSuccess(classifyRightSuperClass.getData());
                    }
                });
    }

    @Override
    public void homePage(String path, final IHomePagePresenter iHomePagePresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<HomePageSuperClass> observable = apiService.getHomePageData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePageSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iHomePagePresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(HomePageSuperClass homePageSuperClass) {
                        iHomePagePresenter.onSuccess(homePageSuperClass);
                    }
                });
    }

    @Override
    public void middle(String path, final IHomePagePresenter iHomePagePresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ClassifyLeftSuperClass> observable = apiService.getLeftData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyLeftSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iHomePagePresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ClassifyLeftSuperClass classifyLeftSuperClass) {
                        iHomePagePresenter.onMiddleSuccess(classifyLeftSuperClass.getData());
                    }
                });
    }

    @Override
    public void list(String path, String pscid, String page, final IListPresenter iListPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ListSuperClass> observable = apiService.getListData(pscid,page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iListPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ListSuperClass listSuperClass) {
                        iListPresenter.onSuccess(listSuperClass.getData());
                    }
                });
    }

    @Override
    public void particulars(String path, String pid, String android, final IParticularsPresenter iParticularsPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ParticularsSuperClass> observable = apiService.getParticularsData(pid, android);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ParticularsSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iParticularsPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ParticularsSuperClass particularsSuperClass) {
                        iParticularsPresenter.onSuccess(particularsSuperClass.getData());
                    }
                });
    }

    @Override
    public void shoppingCart(String path, String uid, String android, final IShoppingCartPresenter iShoppingCartPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ShoppingCartSuperClass> observable = apiService.getShoppingCartData(uid, android);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingCartSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iShoppingCartPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ShoppingCartSuperClass shoppingCartSuperClass) {
                        iShoppingCartPresenter.onSuccess(shoppingCartSuperClass.getData());
                    }
                });
    }

    @Override
    public void login(String path, String mobile, String password, final ILoginPresenter iLoginPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        final Observable<LoginSuperClass> observable = apiService.getLoginData(mobile, password);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iLoginPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginSuperClass loginSuperClass) {
                        iLoginPresenter.onSccuess(loginSuperClass);
                    }
                });
    }

    @Override
    public void reg(String path, String mobile, String password, final IRegPresenter iRegPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<RegisterSuperClass> observable = apiService.getRegisterData(mobile, password);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iRegPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(RegisterSuperClass registerSuperClass) {
                        iRegPresenter.onSuccess(registerSuperClass);
                    }
                });
    }
}
