package com.example.shopping_android_app.model.home;


import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.home.IHome;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel implements IHome.Model {


    @Override
    public void getHome(Callback callback) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getHome()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeBean>(callback) {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        callback.success(homeBean);
                    }
                });
        addDisposible(disposable);
    }

    @Override
    public void getCategory(Callback callback,String url) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getCategory(url)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryBean>(callback) {
                    @Override
                    public void onNext(CategoryBean categoryBean) {
                        callback.success(categoryBean);
                    }
                });
        addDisposible(disposable);
    }

    @Override
    public void getCategoryList(Callback callback, String url,int page,int size) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getCategoryList(url,page,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryListBean>(callback) {
                    @Override
                    public void onNext(CategoryListBean categoryBean) {
                        callback.success(categoryBean);
                    }
                });
        addDisposible(disposable);
    }
}
