package com.example.shopping_android_app.interfaces.me;


import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.me.UserInfoBean;

import java.util.Map;

public interface IUser {
    interface View extends IBaseView {
        void updateUserInfoReturn(UserInfoBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void updateUserInfo(Map<String, String> map);
    }


    interface Model extends IBaseModel {
        void updateUserInfo(Map<String, String> map, Callback callback);
    }
}
