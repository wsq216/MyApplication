package com.example.shopping_android_app.interfaces;

import io.reactivex.disposables.Disposable;

public interface IBaseModel {

    void addDisposible(Disposable disposable);

    void clear();

}
