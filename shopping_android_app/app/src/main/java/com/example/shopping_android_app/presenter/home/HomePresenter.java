package com.example.shopping_android_app.presenter.home;


import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.home.IHome;
import com.example.shopping_android_app.model.home.CategoryBean;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.model.home.HomeModel;

public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter {

    IHome.Model model;
    public HomePresenter(){
        model = new HomeModel();
    }

    @Override
    public void getHome() {
        model.getHome(new Callback<HomeBean>() {
            @Override
            public void success(HomeBean data) {
                if(mView != null){
                    mView.getHomeReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getCategory(String url) {
        model.getCategory(new Callback<CategoryBean>() {

            @Override
            public void success(CategoryBean categoryBean) {
                if (mView!=null){
                    mView.getCategory(categoryBean);
                }
            }

            @Override
            public void fail(String err) {

            }
        },url);
    }

    @Override
    public void getCategoryList(String url,int page,int size) {
        model.getCategoryList(new Callback<CategoryListBean>() {

            @Override
            public void success(CategoryListBean categoryBean) {
                if (mView!=null){
                    mView.getCategoryList(categoryBean);
                }
            }

            @Override
            public void fail(String err) {

            }
        },url,page,size);
    }
}
