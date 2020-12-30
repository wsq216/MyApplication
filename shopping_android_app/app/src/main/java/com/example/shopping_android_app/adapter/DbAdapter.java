package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.dao.DbUtils;
import com.example.shopping_android_app.model.home.shop.CarBean;
import com.example.shopping_android_app.utils.TxtUtils;

import java.util.List;

public class DbAdapter extends BaseAdapter {
    public DbAdapter(Context context, List<DbUtils> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_acquisition;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        DbUtils cartListBean = (DbUtils) data;

        ImageView image = (ImageView) vh.getViewById( R.id.tv_image );
        TextView name = (TextView) vh.getViewById( R.id.tv_name );
        TextView monkey = (TextView) vh.getViewById( R.id.tv_monkey );
        TextView number = (TextView) vh.getViewById( R.id.tv_number );

        Glide.with( context ).load( cartListBean.getUrl()).into( image );
        TxtUtils.setTextView( name,cartListBean.getName() );
        TxtUtils.setTextView( monkey,cartListBean.getPrice()+"" );
        TxtUtils.setTextView( number,"x"+1 );

    }
}
