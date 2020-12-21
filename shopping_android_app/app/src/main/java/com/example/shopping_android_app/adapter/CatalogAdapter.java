package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;

import java.util.List;

public class CatalogAdapter extends BaseAdapter {
    public CatalogAdapter(Context context, List<CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.catalog_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.wap_banner_url);
        TextView name = (TextView) vh.getViewById(R.id.name);

        CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean bean= (CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean) data;

        Glide.with(context).load(bean.getWap_banner_url()).into(image);
        name.setText(bean.getName());
    }
}
