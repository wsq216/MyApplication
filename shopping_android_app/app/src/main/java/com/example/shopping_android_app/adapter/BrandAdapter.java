package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.model.home.BrandBase;

import java.util.List;

public class BrandAdapter extends BaseAdapter {
    public BrandAdapter(Context context, List<BrandBase.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_item_list;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandBase.DataBeanX.DataBean dataBean = (BrandBase.DataBeanX.DataBean) data;
        ImageView app_list_pic_url = (ImageView) vh.getViewById(R.id.app_list_pic_url);
        TextView name = (TextView) vh.getViewById(R.id.name);

        Glide.with(context).load(dataBean.getApp_list_pic_url()).into(app_list_pic_url);
        name.setText(dataBean.getName()+"\t|\t"+dataBean.getFloor_price());
    }
}
