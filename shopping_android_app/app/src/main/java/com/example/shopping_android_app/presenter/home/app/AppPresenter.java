package com.example.shopping_android_app.presenter.home.app;


import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.app.IApp;
import com.example.shopping_android_app.model.home.app.AppBean;
import com.example.shopping_android_app.model.home.app.AppModel;

public class AppPresenter extends BasePresenter<IApp.View> implements IApp.Presenter {

    IApp.Model model;

    public AppPresenter(){
        model = new AppModel();
    }

    @Override
    public void getAppInfo() {
        model.getAppInfo(new Callback<AppBean>() {
            @Override
            public void success(AppBean data) {
                if(mView != null){
                    mView.getAppInfoReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
