package com.example.shopping_android_app.model.home.app;

import android.util.Log;

import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.app.IApp;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;


public class AppModel extends BaseModel implements IApp.Model {
    @Override
    public void getAppInfo(Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi().getAppInfo().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AppBean>(callback) {
                    @Override
                    public void onNext(AppBean appBean) {
                        Log.i("TAG","model onNext register");
                        callback.success(appBean);
                    }
                }));
    }
}
