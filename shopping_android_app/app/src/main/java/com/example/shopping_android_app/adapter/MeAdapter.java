package com.example.shopping_android_app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.ui.my.MyBean;


import java.util.List;

public class MeAdapter extends BaseAdapter {

    private Context context;
    private List<MyBean> beans;

    public MeAdapter(Context context, List<MyBean> data) {
        this.context = context;
        this.beans = data;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            //加载布局
            convertView = View.inflate(context, R.layout.item_me, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置图标和文字
        if (beans != null) {
            Glide.with(context).load(beans.get(position).getImg()).into(holder.imgChannel);
            holder.decChannel.setText(beans.get(position).getTitle());
        }
        return convertView;
    }

    public static
    class ViewHolder {
        public View rootView;
        public ImageView imgChannel;
        public TextView decChannel;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.imgChannel = (ImageView) rootView.findViewById(R.id.imgChannel);
            this.decChannel = (TextView) rootView.findViewById(R.id.decChannel);
        }

    }
}
