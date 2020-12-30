package com.example.shopping_android_app.model.home.address;

import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.address.IAddress;
import com.example.shopping_android_app.interfaces.catalog.ICatalog;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class AddressModel extends BaseModel implements IAddress.Model {

    @Override
    public void getAddressList(Callback callback) {
        Disposable disposable= HttpmManager.getHttpmManager().getShopApi()
                .getAddressList()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddressListBase>(callback) {
                    @Override
                    public void onNext(AddressListBase homeBean) {
                        callback.success(homeBean);
                    }
                });
        addDisposible(disposable);
    }

    @Override
    public void getAddress(Callback callback, int id) {
        Disposable disposable= HttpmManager.getHttpmManager().getShopApi()
                .getAddress(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddressBase>(callback) {
                    @Override
                    public void onNext(AddressBase homeBean) {
                        callback.success(homeBean);
                    }
                });
        addDisposible(disposable);
    }
}
