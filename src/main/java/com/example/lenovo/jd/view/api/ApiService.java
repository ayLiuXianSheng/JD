package com.example.lenovo.jd.view.api;

import com.example.lenovo.jd.view.bean.AddToCarSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;
import com.example.lenovo.jd.view.bean.ListSuperClass;
import com.example.lenovo.jd.view.bean.LoginSuperClass;
import com.example.lenovo.jd.view.bean.ParticularsSuperClass;
import com.example.lenovo.jd.view.bean.RegisterSuperClass;
import com.example.lenovo.jd.view.bean.ShoppingCartSuperClass;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface ApiService {
    //首页
    @GET("ad/getAd")
    Observable<HomePageSuperClass> getHomePageData();
    //分类左侧列表
    @GET("product/getCatagory")
    Observable<ClassifyLeftSuperClass> getLeftData();
    //分类右侧数据
    @GET("product/getProductCatagory")
    Observable<ClassifyRightSuperClass> getRightData(@Query("cid")String cid);
    //分类点击详情列表
    @GET("product/getProducts")
    Observable<ListSuperClass> getListData(@Query("pscid")String pscid,@Query("page")String page);
    //商品详情
    @GET("product/getProductDetail")
    Observable<ParticularsSuperClass> getParticularsData(@Query("pid")String pid, @Query("source")String android);
    //添加购物车
    @GET("product/addCart")
    Observable<AddToCarSuperClass> getAddToCarData(@Query("uid")String uid, @Query("pid")String pid, @Query("source")String android);
    //查询购物车
    @GET("product/getCarts")
    Observable<ShoppingCartSuperClass> getShoppingCartData(@Query("uid")String uid, @Query("source")String android);
    //登录
    @GET("user/login")
    Observable<LoginSuperClass> getLoginData(@Query("mobile")String mobile, @Query("password")String password);
    //注册
    @GET("user/reg")
    Observable<RegisterSuperClass> getRegisterData(@Query("mobile")String mobile, @Query("password")String password);
}
