package com.example.shopping_android_app.model.home.topic;


import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.shop.ICar;
import com.example.shopping_android_app.interfaces.topic.Itopic;
import com.example.shopping_android_app.model.home.shop.CarBean;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class TopicModel extends BaseModel implements Itopic.Model {

    @Override
    public void getTopicList(Callback callback) {
        Disposable disposable=HttpmManager.getHttpmManager().getShopApi()
                .getTopicList()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicBean>(callback) {
                    @Override
                    public void onNext(TopicBean topicBean) {
                        callback.success(topicBean);
                    }
                });
        addDisposible(disposable);
    }
}
