package com.example.shopping_android_app.presenter.home;


import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.catalog.ICatalog;
import com.example.shopping_android_app.interfaces.home.IBrand;
import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.model.home.BrandDatailBase;
import com.example.shopping_android_app.model.home.BrandIdBase;
import com.example.shopping_android_app.model.home.BrandModel;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;
import com.example.shopping_android_app.model.home.catalog.CatalogModel;

public class CatalogPresenter extends BasePresenter<ICatalog.View> implements ICatalog.Presenter {

    ICatalog.Model model;
    public CatalogPresenter(){
        model = new CatalogModel();
    }


    @Override
    public void getCatalog() {
        model.getCatalog(new Callback<CatalogBase>() {
            @Override
            public void success(CatalogBase data) {
                if (mView!=null){
                    mView.getCatalog(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }



    @Override
    public void getCatalogList(int id) {
        model.getCatalogList(new Callback<CatalogListBase>() {
            @Override
            public void success(CatalogListBase data) {
                if (mView!=null){
                    mView.getCatalogList(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },id);
    }
}
