package com.example.shopping_android_app.model.home.shop;


import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.shop.ICar;
import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import java.util.Map;

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

    /**
     * 更新购物车
     * @param map
     * @param callback
     */
    @Override
    public void updateCar(Map<String, String> map, Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi().updateCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateCarBean>(callback) {
                    @Override
                    public void onNext(UpdateCarBean updateCarBean) {
                        callback.success(updateCarBean);
                    }
                }));
    }


    //删除购物车列表
    @Override
    public void deleteCar(String pIds, Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi().deleteCar(pIds).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                }));
    }
}
