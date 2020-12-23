package com.example.shopping_android_app.model.home.shop;


import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.shop.ICar;
import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class CarModel extends BaseModel implements ICar.Model {
    @Override
    public void getCarList(Callback callback) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getCarList()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CarBean>(callback) {
                    @Override
                    public void onNext(CarBean categoryBean) {
                        callback.success(categoryBean);
                    }
                });
        addDisposible(disposable);
    }
}
