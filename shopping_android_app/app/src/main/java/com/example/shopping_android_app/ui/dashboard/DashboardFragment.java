package com.example.shopping_android_app.ui.dashboard;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseFragment;
import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.IBasePresenter;

public class DashboardFragment extends BaseFragment {
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
        return R.layout.fragment_dashboard;
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }


//    @Override
//    protected int getLayout() {
//        return R.layout.fragment_dashboard;
//    }
//
//    @Override
//    protected IBasePresenter createPrenter() {
//        return null;
//    }
//
//    @Override
//    protected void initView() {
//
//    }
//
//    @Override
//    protected void initData() {
//
//    }
}