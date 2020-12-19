package com.example.shopping_android_app.interfaces.home;



import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.HotBase;
import com.example.shopping_android_app.model.home.HotGoodListBean;

import java.util.HashMap;

public interface IHotGood {
    interface View extends IBaseView {
        void getHotGood(HotGoodListBean result);
        void getHot(HotBase hotBase);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHotGood(HashMap<String, String> map);
        void getHot();
    }

    interface Model extends IBaseModel {
        void getHotGood(HashMap<String, String> map, Callback callback);
        void getHot( Callback callback);
    }
}
