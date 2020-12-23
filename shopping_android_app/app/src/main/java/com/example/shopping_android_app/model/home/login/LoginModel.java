package com.example.shopping_android_app.model.home.login;


import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.login.ILogin;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {
    @Override
    public void login(String username,String pw, Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi().login(username,pw).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }

    @Override
    public void getRegister(String username, String pw, Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi()
                .getreister(username,pw)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(callback) {
                    @Override
                    public void onNext(RegisterBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }
}
