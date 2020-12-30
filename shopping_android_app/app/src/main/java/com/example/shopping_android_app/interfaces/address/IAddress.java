package com.example.shopping_android_app.interfaces.address;

import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.IBaseModel;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.interfaces.IBaseView;
import com.example.shopping_android_app.model.home.address.AddressBase;
import com.example.shopping_android_app.model.home.address.AddressListBase;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;

public interface IAddress {

    interface View extends IBaseView{
        void getAddressList(AddressListBase addressListBase);
        void getAddress(AddressBase addressBase);
    }

    interface Presenter extends IBasePresenter<View> {
        void getAddressList();
        void getAddress(int id);
    }

    interface Model extends IBaseModel {
        void getAddressList(Callback callback);
        void getAddress(Callback callback, int id);
    }
}
