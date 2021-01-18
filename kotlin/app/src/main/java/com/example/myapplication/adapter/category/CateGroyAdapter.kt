package com.example.myapplication.adapter.category

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.home.CateGoryActivity
import com.example.myapplication.model.zt.DataX

class CateGroyAdapter(
    context: Context,
    list: List<DataX>,
    layout: SparseArray<Int>,
    itemClick: IItemClick<DataX>
) : BaseAdapter<DataX>(context,list,layout,itemClick) {
    override fun layoutId(position: Int): Int {
        return R.layout.category_item_list
    }

    override fun bindData(binding: ViewDataBinding, data: DataX, layoutId: Int) {

    }
}