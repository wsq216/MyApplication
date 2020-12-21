package com.example.shopping_android_app.ui.notifications;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.CatalogAdapter;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.base.BaseFragment;
import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.catalog.ICatalog;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;
import com.example.shopping_android_app.presenter.home.CatalogPresenter;
import com.example.shopping_android_app.ui.channel.CategoryActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class CatalogFragment extends BaseFragment<CatalogPresenter> implements ICatalog.View {


    @BindView(R.id.wap_banner_url)
    ImageView wapBannerUrl;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.rv_catalog)
    RecyclerView rvCatalog;
    @BindView(R.id.front_name)
    TextView frontName;
    private CatalogAdapter catalogAdapter;

    @Override
    protected CatalogPresenter createPersenter() {
        return new CatalogPresenter();
    }

    @Override
    protected void initData() {
        int id = getArguments().getInt("id", 0);
        presenter.getCatalogList(id);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_catalog;
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }

    @Override
    public void getCatalog(CatalogBase catalogBase) {

    }

    @Override
    public void getCatalogList(CatalogListBase catalogListBase) {
        CatalogListBase.DataBean.CurrentCategoryBean currentCategory = catalogListBase.getData().getCurrentCategory();
        List<CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = catalogListBase.getData().getCurrentCategory().getSubCategoryList();
        Glide.with(getActivity()).load(currentCategory.getWap_banner_url()).into(wapBannerUrl);
        frontName.setText(currentCategory.getFront_name());
        txtName.setText(currentCategory.getName()+"分类");
        rvCatalog.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        catalogAdapter = new CatalogAdapter(getActivity(),subCategoryList);
        rvCatalog.setAdapter(catalogAdapter);

        catalogAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean subCategoryListBean = subCategoryList.get(pos);
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("name",subCategoryListBean.getName());
                intent.putExtra("id", subCategoryListBean.getId()+"");
                startActivity(intent);
            }
        });
    }
}