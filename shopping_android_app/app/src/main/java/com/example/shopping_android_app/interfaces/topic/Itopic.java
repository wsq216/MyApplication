package com.example.shopping_android_app.interfaces.topic;


import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.topic.TopicBean;

public interface Itopic {
    interface View extends IBaseView {
        void getTopicList(TopicBean topicBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getTopicList();
    }


    interface Model extends IBaseModel {
        void getTopicList(Callback callback);
    }

}
