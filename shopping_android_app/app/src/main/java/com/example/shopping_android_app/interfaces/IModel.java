package com.example.shopping_android_app.interfaces;

import io.reactivex.disposables.Disposable;

public interface IModel {
    void addDisposable(Disposable disposable);

    void close();
}
