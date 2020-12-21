package com.example.shopping_android_app.interfaces.catalog;

import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;

public interface ICatalog {

    interface View extends IBaseView{
        void getCatalog(CatalogBase catalogBase);
        void getCatalogList(CatalogListBase catalogListBase);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCatalog();
        void getCatalogList(int id);
    }

    interface Model extends IBaseModel {
        void getCatalog(Callback callback);
        void getCatalogList(Callback callback,int id);
    }
}
