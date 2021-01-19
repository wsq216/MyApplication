package com.example.myapplication.adapter.car

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.home.HomeFragment

class GoodsListAdapter(
    context: Context?,
    list: List<Goods>,
    layouts:SparseArray<Int>,
    itemClick: IItemClick<Goods>
) : BaseAdapter<Goods>(context, list, layouts, itemClick){
    override fun layoutId(position: Int): Int {
        return R.layout.car_item_goodslist
    }

    override fun bindData(binding: ViewDataBinding, data: Goods, layoutId: Int) {
    }
}