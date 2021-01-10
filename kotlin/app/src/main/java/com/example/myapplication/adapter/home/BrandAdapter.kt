package com.example.myapplication.adapter.home

import android.content.Context
import android.database.DatabaseUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Brand
import kotlinx.android.synthetic.main.home_item_brand.view.*

class BrandAdapter(var context: Context?,var list: List<Brand> = listOf<Brand>()):RecyclerView.Adapter<BaseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),viewType,parent,false))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.home_item_brand
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var bindingUtil:ViewDataBinding = holder.dataBinding
        val get = list.get(position)
        with(holder!!.itemView) {
            Glide.with(context).load(get.new_pic_url).into(new_pic_url)
        }
        bindingUtil.setVariable(BR.vmBrand,get)
    }

    override fun getItemCount(): Int = list.size

    /**
     * 加载完数据刷新到界面的data
     */
    fun refreshData(lt:List<Brand>){
        list = lt
        notifyDataSetChanged()
    }

}
