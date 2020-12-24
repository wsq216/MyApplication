package com.example.shopping_android_app.presenter.home.topic;

import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.shop.ICar;
import com.example.shopping_android_app.interfaces.topic.Itopic;
import com.example.shopping_android_app.model.home.shop.CarBean;
import com.example.shopping_android_app.model.home.shop.CarModel;
import com.example.shopping_android_app.model.home.topic.TopicBean;
import com.example.shopping_android_app.model.home.topic.TopicModel;

public class TopicPresenter extends BasePresenter<Itopic.View> implements Itopic.Presenter {

    Itopic.Model model;

    public TopicPresenter(){
        model=new TopicModel();
    }


    @Override
    public void getTopicList() {
        model.getTopicList(new Callback<TopicBean>() {
            @Override
            public void success(TopicBean data) {
                mView.getTopicList(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
