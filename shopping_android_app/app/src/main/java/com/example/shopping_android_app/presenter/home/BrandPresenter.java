package com.example.shopping_android_app.presenter.home;


import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.home.IBrand;
import com.example.shopping_android_app.interfaces.home.IHome;
import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.model.home.BrandDatailBase;
import com.example.shopping_android_app.model.home.BrandIdBase;
import com.example.shopping_android_app.model.home.BrandModel;
import com.example.shopping_android_app.model.home.CategoryBean;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.model.home.HomeModel;

public class BrandPresenter extends BasePresenter<IBrand.View> implements IBrand.Presenter {

    IBrand.Model model;
    public BrandPresenter(){
        model = new BrandModel();
    }


    @Override
    public void getBrand(int page, int size) {
        model.getBrand(new Callback<BrandBase>() {
            @Override
            public void success(BrandBase data) {
                if (mView!=null){
                    mView.getBrand(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },page,size);
    }

    @Override
    public void getBrandDetail(int id) {
        model.getBrandDetail(new Callback<BrandDatailBase>() {
            @Override
            public void success(BrandDatailBase data) {
                if (mView!=null){
                    mView.getBrandDetail(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },id);
    }

    @Override
    public void getBrandId(int brandId, int page, int size) {
        model.getBrandId(new Callback<BrandIdBase>() {
            @Override
            public void success(BrandIdBase data) {
                if (mView!=null){
                    mView.getBrandId(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },brandId,page,size);
    }

}
