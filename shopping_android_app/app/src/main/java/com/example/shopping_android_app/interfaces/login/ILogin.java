package com.example.shopping_android_app.interfaces.login;


import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.login.LoginBean;
import com.example.shopping_android_app.model.home.login.RegisterBean;

public interface ILogin {
    interface View extends IBaseView {
        void loginReturn(LoginBean loginBean);
        void getRegister(RegisterBean registerBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void login(String username, String pw);
        void getRegister(String username, String pw);
    }


    interface Model extends IBaseModel {
        void login(String username, String pw, Callback callback);
        void getRegister(String username, String pw, Callback callback);
    }
}
