package com.example.shopping_android_app.interfaces;

public interface BasePersenter<V extends BeanView> {
    void attachView(V view);
    void unAttachView();

}
