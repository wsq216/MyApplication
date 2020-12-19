package com.example.shopping_android_app.ui.dashboard;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.BrandItemAdapter;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.home.IBrand;
import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.model.home.BrandDatailBase;
import com.example.shopping_android_app.model.home.BrandIdBase;
import com.example.shopping_android_app.presenter.home.BrandPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BreadItemActivity extends BaseActivity<IBrand.Presenter> implements IBrand.View {


    @BindView(R.id.img_app_list_pic_url)
    ImageView imgAppListPicUrl;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.layout_info)
    ConstraintLayout layoutInfo;
    @BindView(R.id.txt_simple_desc)
    TextView txtSimpleDesc;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private BrandItemAdapter brandItemAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_bread_item;
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
        int id = getIntent().getIntExtra("id", 0);
        if (id != 0) {
            presenter.getBrandDetail(id);
            presenter.getBrandId(id, 1, 1000);
        }
    }

    @Override
    public void getBrand(BrandBase brandBase) {

    }

    @Override
    public void getBrandId(BrandIdBase brandIdBase) {
        List<BrandIdBase.DataBeanX.DataBean> data = brandIdBase.getData().getData();

        rvList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        brandItemAdapter = new BrandItemAdapter(this,data);

        rvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        rvList.setAdapter(brandItemAdapter);
    }

    @Override
    public void getBrandDetail(BrandDatailBase brandDatailBase) {
        BrandDatailBase.DataBean.BrandBean brand = brandDatailBase.getData().getBrand();
        Glide.with(this).load(brand.getApp_list_pic_url()).into(imgAppListPicUrl);
        txtName.setText(brand.getName());
        txtSimpleDesc.setText(brand.getSimple_desc());
    }

}