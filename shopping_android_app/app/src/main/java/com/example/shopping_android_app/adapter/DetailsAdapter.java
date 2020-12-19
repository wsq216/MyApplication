package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.model.home.HotGoodListBean;
import com.example.shopping_android_app.model.home.details.RelatedBase;

import java.util.List;

public class DetailsAdapter extends BaseAdapter {

    public DetailsAdapter(Context context, List<RelatedBase.DataBean.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_item_deatil;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.new_pic_url);
        TextView name = (TextView) vh.getViewById(R.id.name);
        TextView floor_price = (TextView) vh.getViewById(R.id.floor_price);

        RelatedBase.DataBean.GoodsListBean bean= (RelatedBase.DataBean.GoodsListBean) data;

        Glide.with(context).load(bean.getList_pic_url()).into(image);
        name.setText(bean.getName());
        floor_price.setText("￥"+bean.getRetail_price());
    }
}
