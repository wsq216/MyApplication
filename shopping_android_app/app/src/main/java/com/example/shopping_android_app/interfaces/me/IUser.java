package com.example.shopping_android_app.interfaces.me;


import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.login.LogoutBase;
import com.example.shopping_android_app.model.home.me.UserInfoBean;

import java.util.Map;

public interface IUser {
    interface View extends IBaseView {
        void updateUserInfoReturn(UserInfoBean result);
        void logout(LogoutBase logoutBase);
    }

    interface Presenter extends IBasePresenter<View> {
        void updateUserInfo(Map<String, String> map);
        void logout();
    }


    interface Model extends IBaseModel {
        void updateUserInfo(Map<String, String> map, Callback callback);
        void logout(Callback callback);
    }
}
