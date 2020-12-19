package com.example.shopping_android_app.interfaces;

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();

}
