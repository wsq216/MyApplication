package com.example.myapplication.adapter.brand

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapter.home.BaseViewHolder
import com.example.myapplication.data.Goods
import com.example.myapplication.data.brand.DataX
import kotlinx.android.synthetic.main.brand_item_list.view.*

class BrandAdapter(var context: Context?,var list: List<DataX> = listOf<DataX>())
    : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),viewType,parent,false))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.brand_item_list
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var bindingUtil = holder.dataBinding
        val get = list.get(position)
        bindingUtil!!.setVariable(BR.vmBrandActivity,get)
        with(holder!!.itemView){
            Glide.with(context).load(get.app_list_pic_url).into(app_list_pic_url)
        }
    }

    override fun getItemCount(): Int = list.size

    /**
     * 加载完数据刷新到界面的data
     */
    fun refreshData(it:List<DataX>){
        list=it
        notifyDataSetChanged()
    }
}