package com.example.shopping_android_app.model.home;


import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.home.IBrand;
import com.example.shopping_android_app.interfaces.home.IHome;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class BrandModel extends BaseModel implements IBrand.Model {

    @Override
    public void getBrand(Callback callback, int page, int size) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getBrand(page,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandBase>(callback) {
                    @Override
                    public void onNext(BrandBase categoryBean) {
                        callback.success(categoryBean);
                    }
                });
        addDisposible(disposable);
    }

    @Override
    public void getBrandDetail(Callback callback, int id) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getBrandDetail(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandDatailBase>(callback) {
                    @Override
                    public void onNext(BrandDatailBase categoryBean) {
                        callback.success(categoryBean);
                    }
                });
        addDisposible(disposable);
    }

    @Override
    public void getBrandId(Callback callback, int brandId, int page, int size) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getBrandId(brandId,page,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandIdBase>(callback) {
                    @Override
                    public void onNext(BrandIdBase categoryBean) {
                        callback.success(categoryBean);
                    }
                });
        addDisposible(disposable);
    }
}
