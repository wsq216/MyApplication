package com.example.shopping_android_app.model.home.details;

import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.home.IDetail;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.model.home.shop.AddCarBean;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import java.util.Map;

import io.reactivex.disposables.Disposable;

public class DetailsModel extends BaseModel implements IDetail.Model {
    @Override
    public void getDetails(Callback callback,int id) {
        Disposable disposable= HttpmManager.getHttpmManager().getShopApi()
                .getDetails(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DetailsBase>(callback) {
                    @Override
                    public void onNext(DetailsBase homeBean) {
                        callback.success(homeBean);
                    }
                });
        addDisposible(disposable);
    }

    @Override
    public void getRelated(Callback callback,int id) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getRelated(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RelatedBase>(callback) {
                    @Override
                    public void onNext(RelatedBase homeBean) {
                        callback.success(homeBean);
                    }
                });
        addDisposible(disposable);
    }

    @Override
    public void addGoodCar(Map<String, String> map, Callback callback) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .addCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                    @Override
                    public void onNext(AddCarBean homeBean) {
                        callback.success(homeBean);
                    }
                });
        addDisposible(disposable);

    }
}
