package com.example.shopping_android_app.presenter.home;



import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.home.IHotGood;
import com.example.shopping_android_app.model.home.HotBase;
import com.example.shopping_android_app.model.home.HotGoodListBean;
import com.example.shopping_android_app.model.home.HotGoodModel;

import java.util.HashMap;

public class HotGoodPresenter extends BasePresenter<IHotGood.View> implements IHotGood.Presenter {
    IHotGood.Model model;
    public HotGoodPresenter(){
        model = new HotGoodModel();
    }

    @Override
    public void getHotGood(HashMap<String,String> map) {
        model.getHotGood(map,new Callback<HotGoodListBean>() {
            @Override
            public void success(HotGoodListBean data) {
                if(mView != null){
                    mView.getHotGood(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getHot() {
        model.getHot(new Callback<HotBase>() {
            @Override
            public void success(HotBase data) {
                if(mView != null){
                    mView.getHot(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
