package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.model.home.shop.CarBean;
import com.example.shopping_android_app.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;
import retrofit2.http.GET;

public class AcquisitionAdapter extends BaseAdapter {


    public AcquisitionAdapter(Context context, List<CarBean.DataBean.CartListBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_acquisition;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        CarBean.DataBean.CartListBean cartListBean = (CarBean.DataBean.CartListBean) data;

        ImageView image = (ImageView) vh.getViewById( R.id.tv_image );
        TextView name = (TextView) vh.getViewById( R.id.tv_name );
        TextView monkey = (TextView) vh.getViewById( R.id.tv_monkey );
        TextView number = (TextView) vh.getViewById( R.id.tv_number );

        Glide.with( context ).load( cartListBean.getList_pic_url()).into( image );
        TxtUtils.setTextView( name,cartListBean.getGoods_name() );
        TxtUtils.setTextView( monkey,cartListBean.getMarket_price()+"" );
        TxtUtils.setTextView( number,"x"+cartListBean.getNumber() );



    }
}
