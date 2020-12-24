package com.example.shopping_android_app.ui.shop;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.model.home.shop.CarBean;
import com.example.shopping_android_app.utils.ImageLoader;
import com.example.shopping_android_app.widget.NumberSelect;

import java.util.List;

public class CarListAdapter extends BaseAdapter<CarBean.DataBean.CartListBean> {

    private final Context context;
    private boolean isEdit; //是否是编辑状态

    private UpdateItem updateItem;
    public void setUpdateItem(UpdateItem item){
        this.updateItem = item;
    }

    //修改接口回调
    public interface UpdateItem{
        void updateItemDate(CarBean.DataBean.CartListBean data);
    }

    //设置是否是编译状态
    public void setEditState(boolean bool){
        isEdit = bool;
    }

    public CarListAdapter(Context context, List<CarBean.DataBean.CartListBean> data) {
        super(context, data);
        this.context = context;
    }


    @Override
    protected int getLayout(int type) {
        return R.layout.layout_car_item;
    }

    @Override
    protected void bindData(CarBean.DataBean.CartListBean data, VH vh) {

        CheckBox checkBox = (CheckBox) vh.getViewById(R.id.checkbox);
        ImageView imgItem = (ImageView) vh.getViewById(R.id.img_item);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_price);
        TextView txtNumber = (TextView) vh.getViewById(R.id.txt_number);
        TextView txtEditTitle = (TextView) vh.getViewById(R.id.txt_edit_title);
        NumberSelect numberSelect = (NumberSelect) vh.getViewById(R.id.layout_change);


        txtName.setVisibility(isEdit?View.GONE:View.VISIBLE);
        txtNumber.setVisibility(isEdit?View.GONE:View.VISIBLE);
        txtEditTitle.setVisibility(isEdit?View.VISIBLE:View.GONE);
        numberSelect.setVisibility(isEdit?View.VISIBLE:View.GONE);


        // 设置选中状态
        checkBox.setChecked(isEdit?data.selectEdit:data.selectOrder);
//        ImageLoader.loadImage(data.getList_pic_url(),imgItem);
        Glide.with(context).load(data.getList_pic_url()).into(imgItem);
        txtName.setText(data.getGoods_name());
        //获取总价
        txtPrice.setText("￥"+data.getRetail_price());
        //获取总数量
        txtNumber.setText("X"+String.valueOf(data.getNumber()));
        //添加设置数量的布局
        numberSelect.addPage(R.layout.layout_number_change);
        //给自定义布局设置总数
        numberSelect.setNumber(data.getNumber());
        //自定义布局的接口回调
        numberSelect.addChangeNumber(new NumberSelect.ChangeNumber() {
            @Override
            public void change(int number) {
                //修改本地数据得值
                data.setNumber(number);
                //设置删除数据
                if(updateItem != null){
                    updateItem.updateItemDate(data);
                }
            }
        });

        //设置条目数据的id
        checkBox.setTag(data.getId());
        //多选框点击事件
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //适配器多布局
                if(iItemViewClick != null){
                    //获取绑定的id
                    int id = (int) buttonView.getTag();
                    iItemViewClick.itemViewClick(id,isChecked);
                }
            }
        });
       /* if(isEdit){
            txtName.setVisibility(View.GONE);
            txtNumber.setVisibility(View.GONE);
            txtEditTitle.setVisibility(View.VISIBLE);
            numberSelect.setVisibility(View.VISIBLE);
        }else{
            txtName.setVisibility(View.VISIBLE);
            txtNumber.setVisibility(View.VISIBLE);
            txtEditTitle.setVisibility(View.GONE);
            numberSelect.setVisibility(View.GONE);
        }*/



    }
}
