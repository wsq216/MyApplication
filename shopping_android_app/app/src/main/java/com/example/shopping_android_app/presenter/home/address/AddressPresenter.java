package com.example.shopping_android_app.presenter.home.address;


import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.Callback;
import com.example.shopping_android_app.interfaces.address.IAddress;
import com.example.shopping_android_app.interfaces.home.IBrand;
import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.model.home.BrandDatailBase;
import com.example.shopping_android_app.model.home.BrandIdBase;
import com.example.shopping_android_app.model.home.BrandModel;
import com.example.shopping_android_app.model.home.address.AddressBase;
import com.example.shopping_android_app.model.home.address.AddressListBase;
import com.example.shopping_android_app.model.home.address.AddressModel;

public class AddressPresenter extends BasePresenter<IAddress.View> implements IAddress.Presenter {

    IAddress.Model model;
    public AddressPresenter(){
        model = new AddressModel();
    }


    @Override
    public void getAddressList() {
        model.getAddressList(new Callback<AddressListBase>() {
            @Override
            public void success(AddressListBase data) {
                if (mView!=null){
                    mView.getAddressList(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getAddress(int id) {
        model.getAddress(new Callback<AddressBase>() {
            @Override
            public void success(AddressBase data) {
                if (mView!=null){
                    mView.getAddress(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },id);
    }
}
