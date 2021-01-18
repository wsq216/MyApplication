package com.example.myapplication.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Goods
import kotlinx.android.synthetic.main.home_item_topic.view.*

class CategoryListAdapter(var context: Context?, var list: List<Goods>)
    : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),viewType,parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val get = list.get(position)
        var bindingUtil : ViewDataBinding = holder.dataBinding
        bindingUtil.setVariable(BR.vmGoods,get)
        with(holder!!.itemView){
            Glide.with(context).load(get.list_pic_url)?.into(new_pic_url)
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.home_item_categorygoods
    }

    /**
     * 加载完数据刷新到界面的data
     */
    fun refreshData(it:List<Goods>){
        list=it
        notifyDataSetChanged()
    }
}