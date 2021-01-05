package com.example.shopping_android_app.interfaces.app;


import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.app.AppBean;

public interface IApp {
    interface View extends IBaseView {
        void getAppInfoReturn(AppBean appBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getAppInfo();
    }


    interface Model extends IBaseModel {
        void getAppInfo(Callback callback);
    }
}
