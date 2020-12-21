package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseAdapter;

import java.util.List;

public class CarImgAdapter extends BaseAdapter {
    private final Context context;

    public CarImgAdapter(Context context, List<String> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.car_item_img;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String url= (String) data;
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        Glide.with(context).load(url).into(image);
    }
}
