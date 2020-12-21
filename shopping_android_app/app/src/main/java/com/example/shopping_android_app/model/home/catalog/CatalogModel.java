package com.example.shopping_android_app.model.home.catalog;

import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.catalog.ICatalog;
import com.example.shopping_android_app.model.home.details.DetailsBase;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class CatalogModel extends BaseModel implements ICatalog.Model {
    @Override
    public void getCatalog(Callback callback) {
        Disposable disposable= HttpmManager.getHttpmManager().getShopApi()
                .getCatalog()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CatalogBase>(callback) {
                    @Override
                    public void onNext(CatalogBase homeBean) {
                        callback.success(homeBean);
                    }
                });
        addDisposible(disposable);
    }

    @Override
    public void getCatalogList(Callback callback, int id) {
        Disposable disposable= HttpmManager.getHttpmManager().getShopApi()
                .getCatalogList(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CatalogListBase>(callback) {
                    @Override
                    public void onNext(CatalogListBase homeBean) {
                        callback.success(homeBean);
                    }
                });
        addDisposible(disposable);
    }
}
