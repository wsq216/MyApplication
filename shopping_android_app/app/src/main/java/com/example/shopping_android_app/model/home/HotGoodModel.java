package com.example.shopping_android_app.model.home;



import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.home.IHotGood;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import java.util.HashMap;

public class HotGoodModel extends BaseModel implements IHotGood.Model {
    @Override
    public void getHotGood(HashMap<String, String> map, Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi()
                .getHotGoodList(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotGoodListBean>(callback) {
                    @Override
                    public void onNext(HotGoodListBean hotGoodListBean) {
                        if (hotGoodListBean!=null) {
                            callback.success(hotGoodListBean);
                        }
                    }
                }));
    }

    @Override
    public void getHot(Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi()
                .getHot()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotBase>(callback) {
                    @Override
                    public void onNext(HotBase hotBase) {
                        if (hotBase!=null) {
                            callback.success(hotBase);
                        }
                    }
                }));
    }
}
