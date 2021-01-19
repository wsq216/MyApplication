package com.example.myapplication.adapter.home

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.data.Category
import com.example.myapplication.data.Goods
import com.example.myapplication.home.HomeFragment
import com.shop.base.BaseActivity
import kotlinx.android.synthetic.main.home_item_topic.view.*

class CategoryListAdapter(
    context: Context?,
    list: List<Goods>,
    layouts : SparseArray<Int>,
    itemClick: IItemClick<Goods>
) : BaseAdapter<Goods>(context, list,layouts,itemClick) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
//        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
//            viewType,
//            parent,
//            false))
//    }
//
//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//        val get = list.get(position)
//        var bindingUtil: ViewDataBinding = holder.dataBinding
//        bindingUtil.setVariable(BR.vmGoods, get)
//        with(holder!!.itemView) {
//            Glide.with(context).load(get.list_pic_url)?.into(new_pic_url)
//        }
//    }
//
//    override fun getItemCount(): Int = list.size
//
//    override fun getItemViewType(position: Int): Int {
//        return R.layout.home_item_categorygoods
//    }

    /**
     * 加载完数据刷新到界面的data
     */
    fun refreshData(it: List<Goods>) {
        list = it
        notifyDataSetChanged()
    }

    override fun layoutId(position: Int): Int {
        return R.layout.home_item_categorygoods
    }

    override fun bindData(binding: ViewDataBinding, data: Goods, layoutId: Int) {
        binding.setVariable(BR.vmGodosClick,itemClick)
    }
}