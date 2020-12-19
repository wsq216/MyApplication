package com.example.shopping_android_app.interfaces.home;

import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.model.home.BrandDatailBase;
import com.example.shopping_android_app.model.home.BrandIdBase;


public interface IBrand {
    interface View extends IBaseView {
        void getBrand(BrandBase brandBase);
        void getBrandId(BrandIdBase brandIdBase);
        void getBrandDetail(BrandDatailBase brandDatailBase);

    }

    interface Presenter extends IBasePresenter<View> {
        void getBrand(int page,int size);
        void getBrandDetail(int id);
        void getBrandId(int brandId,int page,int size);
    }

    interface Model extends IBaseModel {
        void getBrand(Callback callback,int page,int size);
        void getBrandDetail(Callback callback,int id);
        void getBrandId(Callback callback,int brandId,int page,int size);
    }
}
