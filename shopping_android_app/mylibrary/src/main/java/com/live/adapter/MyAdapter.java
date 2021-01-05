package com.live.adapter;

import android.content.Context;
import android.widget.TextView;


import com.example.mvp.base.BaseAdapter;
import com.example.mvp.utils.TxtUtils;
import com.live.R;
import com.live.bean.RoomListBean;

import java.util.List;


public class MyAdapter extends BaseAdapter {

    public MyAdapter(Context context, List<RoomListBean.DataBean> data) {
        super( context, data );
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_room_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        RoomListBean.DataBean dataBean= (RoomListBean.DataBean) data;

        //ImageView ivRoomListImg = (ImageView) vh.getViewById( R.id.iv_room_list_img );
        TextView tv_room_list_title = (TextView) vh.getViewById( R.id.tv_room_list_title );
        TextView tvRoomListName = (TextView) vh.getViewById( R.id.tv_room_list_name );
        TextView tvRoomListOwner = (TextView) vh.getViewById( R.id.tv_room_list_owner );


        TxtUtils.setTextView( tv_room_list_title,dataBean.getTitle() );
        TxtUtils.setTextView( tvRoomListName,dataBean.getName() );
        TxtUtils.setTextView( tvRoomListOwner,dataBean.getId()+"" );

    }
}
