package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.model.home.BrandIdBase;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.model.home.HotGoodListBean;

import java.util.List;

public class HotGoodAdapter extends BaseAdapter {
    public HotGoodAdapter(Context context, List<HotGoodListBean.DataBeanX.DataBean> data) {
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

        HotGoodListBean.DataBeanX.DataBean bean= (HotGoodListBean.DataBeanX.DataBean) data;

        Glide.with(context).load(bean.getList_pic_url()).into(image);
        name.setText(bean.getName());
        floor_price.setText("￥"+bean.getRetail_price());
    }
}
