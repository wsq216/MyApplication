package com.example.myapplication.adapter.sort

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.data.sort.CurrentData
import com.example.myapplication.data.sort.SubCategory

class SortDataAdapter(
    context : Context?, list: List<SubCategory>,
    layoutId: SparseArray<Int>,
    itemClick: IItemClick<SubCategory>
) : BaseAdapter<SubCategory>(context,list,layoutId,itemClick) {
    override fun layoutId(position: Int): Int {
        return R.layout.sort_item_sortlist
    }

    override fun bindData(binding: ViewDataBinding, data: SubCategory, layoutId: Int) {
        binding.setVariable(BR.vmSortListClick,itemClick)
    }

}