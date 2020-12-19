package com.example.shopping_android_app.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.BrandAdapter;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.interfaces.home.IBrand;
import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.model.home.BrandDatailBase;
import com.example.shopping_android_app.model.home.BrandIdBase;
import com.example.shopping_android_app.presenter.home.BrandPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandActivity extends BaseActivity<IBrand.Presenter> implements IBrand.View {


    private static int page = 1;
    private static int size = 1000;
    @BindView(R.id.rv_brand)
    RecyclerView rvBrand;
    private List<BrandBase.DataBeanX.DataBean> list;
    private BrandAdapter brandAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_board;
    }

    @Override
    protected IBrand.Presenter createPrenter() {
        return new BrandPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();

        rvBrand.setLayoutManager(new LinearLayoutManager(this));

        rvBrand.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        brandAdapter = new BrandAdapter(this,list);

        rvBrand.setAdapter(brandAdapter);

        initClick();

        presenter.getBrand(page, size);
    }

    private void initClick() {
        brandAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                BrandBase.DataBeanX.DataBean dataBean = list.get(pos);
                Intent intent = new Intent(BrandActivity.this,BreadItemActivity.class);
                intent.putExtra("id",dataBean.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getBrand(BrandBase brandBase) {
        List<BrandBase.DataBeanX.DataBean> data = brandBase.getData().getData();
        list.addAll(data);
        brandAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBrandId(BrandIdBase brandIdBase) {

    }

    @Override
    public void getBrandDetail(BrandDatailBase brandDatailBase) {

    }

}