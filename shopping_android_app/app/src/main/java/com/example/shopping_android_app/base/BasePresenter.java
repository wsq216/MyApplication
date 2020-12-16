package com.example.shopping_android_app.base;




import com.example.shopping_android_app.interfaces.BasePersenter;
import com.example.shopping_android_app.interfaces.BeanView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BeanView> implements BasePersenter<V> {

    V mView;
    private WeakReference<V> weakReference;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<V>(view);
        mView = weakReference.get();

    }

    @Override
    public void unAttachView() {
        mView=null;
    }
}
