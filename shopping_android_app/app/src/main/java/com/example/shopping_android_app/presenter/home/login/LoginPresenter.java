package com.example.shopping_android_app.presenter.home.login;


import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.login.ILogin;
import com.example.shopping_android_app.model.home.login.LoginBean;
import com.example.shopping_android_app.model.home.login.LoginModel;
import com.example.shopping_android_app.model.home.login.RegisterBean;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter {
    ILogin.Model model;
    public LoginPresenter(){
        model = new LoginModel();
    }
    @Override
    public void login(String username,String pw) {
        model.login(username,pw,new Callback<LoginBean>() {
            @Override
            public void success(LoginBean data) {
                if(mView != null){
                    mView.loginReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getRegister(String username, String pw) {
        model.getRegister(username,pw,new Callback<RegisterBean>() {
            @Override
            public void success(RegisterBean data) {
                if(mView != null){
                    mView.getRegister(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
