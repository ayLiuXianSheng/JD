package com.example.lenovo.jd.view.base;

/**
 * Created by lenovo on 2018/4/9.
 */

public class BasePresenter<V> {
    public V view;

    public void attachView(V view) {
        this.view = view;
    }
}
