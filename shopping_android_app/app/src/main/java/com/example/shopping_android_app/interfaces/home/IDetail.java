package com.example.shopping_android_app.interfaces.home;

import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.details.DetailsBase;
import com.example.shopping_android_app.model.home.details.RelatedBase;
import com.example.shopping_android_app.model.home.shop.AddCarBean;

import java.util.Map;


public interface IDetail {
    interface View extends IBaseView {
        void getDetails(DetailsBase detailsBase);
        void getRelated(RelatedBase brandIdBase);
        void addGoodCarReturn(AddCarBean addCarBean);

    }

    interface Presenter extends IBasePresenter<View> {
        void getDetails(int id);
        void getRelated(int id);
        //添加进购物车
        void addGoodCar(Map<String,String> map);
    }

    interface Model extends IBaseModel {
        void getDetails(Callback callback,int id);
        void getRelated(Callback callback,int id);
        //添加进购物车
        void addGoodCar(Map<String,String> map,Callback callback);
    }
}
