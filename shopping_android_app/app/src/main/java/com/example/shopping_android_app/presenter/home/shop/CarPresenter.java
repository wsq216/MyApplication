package com.example.shopping_android_app.presenter.home.shop;

import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.shop.ICar;
import com.example.shopping_android_app.model.home.shop.CarBean;
import com.example.shopping_android_app.model.home.shop.CarModel;

public class CarPresenter extends BasePresenter<ICar.View> implements ICar.Presenter {

    ICar.Model model;

    public CarPresenter(){
        model=new CarModel();
    }

    @Override
    public void getCarList() {
        model.getCarList(new Callback<CarBean>() {
            @Override
            public void success(CarBean data) {
                mView.getCarListReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
