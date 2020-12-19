package com.example.shopping_android_app.interfaces.home;


import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.CategoryBean;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.HomeBean;

public interface IHome {

    interface View extends IBaseView {
        void getHomeReturn(HomeBean result);
        void getCategory(CategoryBean categoryBean);
        void getCategoryList(CategoryListBean categoryListBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHome();
        void getCategory(String url);
        void getCategoryList(String url,int page,int size);
    }

    interface Model extends IBaseModel {
        void getHome(Callback callback);
        void getCategory(Callback callback,String url);
        void getCategoryList(Callback callback,String url,int page,int size);
    }

}
