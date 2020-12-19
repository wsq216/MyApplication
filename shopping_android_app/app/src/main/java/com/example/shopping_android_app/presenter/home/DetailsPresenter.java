package com.example.shopping_android_app.presenter.home;

import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.home.IDetail;
import com.example.shopping_android_app.model.home.details.DetailsBase;
import com.example.shopping_android_app.model.home.details.DetailsModel;
import com.example.shopping_android_app.model.home.details.RelatedBase;

public class DetailsPresenter extends BasePresenter<IDetail.View> implements IDetail.Presenter {

    private final IDetail.Model detailsModel;

    public DetailsPresenter(){
        detailsModel = new DetailsModel();
    }

    @Override
    public void getDetails(int id) {
        detailsModel.getDetails(new Callback<DetailsBase>() {
            @Override
            public void success(DetailsBase data) {
                if (mView!=null){
                    mView.getDetails(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },id);
    }

    @Override
    public void getRelated(int id) {
        detailsModel.getRelated(new Callback<RelatedBase>() {
            @Override
            public void success(RelatedBase data) {
                if (mView!=null){
                    mView.getRelated(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },id);
    }
}
