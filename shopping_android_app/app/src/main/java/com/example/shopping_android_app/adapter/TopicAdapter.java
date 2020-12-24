package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.model.home.topic.TopicBean;

import java.util.List;

public class TopicAdapter extends BaseAdapter {

    private final Context context;

    public TopicAdapter(Context context, List<TopicBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.new_pic_url);
        TextView name = (TextView) vh.getViewById(R.id.name);
        TextView floor_price = (TextView) vh.getViewById(R.id.floor_price);
        TextView goods_brief = (TextView) vh.getViewById(R.id.goods_brief);

        TopicBean.DataBeanX.DataBean bean= (TopicBean.DataBeanX.DataBean) data;

        Glide.with(context).load(bean.getScene_pic_url()).into(image);
        name.setText(bean.getTitle());
        floor_price.setText("￥"+bean.getPrice_info());
        goods_brief.setText(bean.getSubtitle());
    }
}
