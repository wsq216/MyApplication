package com.example.shopping_android_app.presenter.home.shop;

import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.shop.ICar;
import com.example.shopping_android_app.model.home.details.DetailsBase;
import com.example.shopping_android_app.model.home.shop.CarBean;
import com.example.shopping_android_app.model.home.shop.CarModel;
import com.example.shopping_android_app.model.home.shop.DeleteCarBean;
import com.example.shopping_android_app.model.home.shop.UpdateCarBean;

import java.util.Map;

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

    @Override
    public void updateCar(Map<String, String> map) {
        model.updateCar(map,new Callback<UpdateCarBean>() {
            @Override
            public void success(UpdateCarBean data) {
                mView.updateCarReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void deleteCar(String pIds) {
        model.deleteCar(pIds,new Callback<DeleteCarBean>() {
            @Override
            public void success(DeleteCarBean data) {
                mView.deleteCarReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
