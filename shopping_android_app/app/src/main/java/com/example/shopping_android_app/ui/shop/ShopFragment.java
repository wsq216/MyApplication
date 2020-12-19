package com.example.shopping_android_app.ui.shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseFragment;
import com.example.shopping_android_app.base.BasePresenter;


public class ShopFragment extends BaseFragment {

    @Override
    protected BasePresenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }
}