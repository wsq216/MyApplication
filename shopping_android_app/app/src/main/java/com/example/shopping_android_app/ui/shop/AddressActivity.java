package com.example.shopping_android_app.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.address.IAddress;
import com.example.shopping_android_app.model.home.address.AddressBase;
import com.example.shopping_android_app.model.home.address.AddressListBase;
import com.example.shopping_android_app.presenter.home.address.AddressPresenter;
import com.example.shopping_android_app.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity<IAddress.Presenter> implements IAddress.View {


    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.btn_add)
    Button btnAdd;

    @Override
    protected int getLayout() {
        return R.layout.activity_address;
    }

    @Override
    protected IAddress.Presenter createPrenter() {
        return new AddressPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        if (SpUtils.getInstance().getString("token") != null) {//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2I5NjU2NTYtNzBlYi00NzI2LWI0YTctYzUyMzY2ODYxNDg1IiwiaWF0IjoxNjA5MDcxMDk3fQ.zgACXCh-PxxthyJ6K2LPTMUZi3Y3uAHREholzqHO8mk
            presenter.getAddressList();
        }
    }

    @Override
    public void getAddressList(AddressListBase addressListBase) {
        if (addressListBase != null) {
            if (addressListBase.getData() != null) {
                Toast.makeText(this, addressListBase.getData().get(0).getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void getAddress(AddressBase addressBase) {

    }


    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        startActivity(new Intent(this,UpdateActivity.class));
    }
}