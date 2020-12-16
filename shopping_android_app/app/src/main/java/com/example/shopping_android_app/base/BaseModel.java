package com.example.shopping_android_app.base;




import com.example.shopping_android_app.interfaces.IModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel implements IModel {
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void close() {
        compositeDisposable.clear();
    }
}
