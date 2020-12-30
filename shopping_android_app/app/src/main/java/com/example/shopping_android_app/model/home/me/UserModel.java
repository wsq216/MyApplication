package com.example.shopping_android_app.model.home.me;



import com.example.shopping_android_app.base.BaseModel;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.me.IUser;
import com.example.shopping_android_app.model.home.login.LogoutBase;
import com.example.shopping_android_app.net.CommonSubscriber;
import com.example.shopping_android_app.net.HttpmManager;
import com.example.shopping_android_app.utils.RxUtils;

import java.util.Map;

public class UserModel extends BaseModel implements IUser.Model {
    @Override
    public void updateUserInfo(Map<String, String> map, Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi().updateUserInfo(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserInfoBean>(callback) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        callback.success(userInfoBean);
                    }
                }));
    }
    @Override
    public void logout(Callback callback) {
        addDisposible(HttpmManager.getHttpmManager().getShopApi()
                .logout()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LogoutBase>(callback) {
                    @Override
                    public void onNext(LogoutBase userInfoBean) {
                        callback.success(userInfoBean);
                    }
                }));
    }
}
