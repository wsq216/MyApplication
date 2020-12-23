package com.example.shopping_android_app.interfaces.shop;


import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.shop.CarBean;

public interface ICar {
    interface View extends IBaseView {
        void getCarListReturn(CarBean carBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCarList();
    }


    interface Model extends IBaseModel {
        void getCarList(Callback callback);
    }

}
