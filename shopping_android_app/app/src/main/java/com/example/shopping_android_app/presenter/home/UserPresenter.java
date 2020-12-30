package com.example.shopping_android_app.presenter.home;



import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.me.IUser;
import com.example.shopping_android_app.model.home.me.UserInfoBean;
import com.example.shopping_android_app.model.home.me.UserModel;

import java.util.Map;

public class UserPresenter extends BasePresenter<IUser.View> implements IUser.Presenter {

    IUser.Model model;

    public UserPresenter(){
        model = new UserModel();
    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
        model.updateUserInfo(map,new Callback<UserInfoBean>() {
            @Override
            public void success(UserInfoBean data) {
                if(mView != null){
                    mView.updateUserInfoReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}
