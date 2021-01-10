package com.example.myapplication.adapter.newgoods

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
import com.example.myapplication.adapter.home.BaseViewHolder
import com.example.myapplication.data.Brand
import com.example.myapplication.data.newgoods.DataX
import kotlinx.android.synthetic.main.home_item_brand.view.*

class NewGoodsAdapter(var context: Context?, var list: List<DataX> = listOf<DataX>()):RecyclerView.Adapter<BaseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),viewType,parent,false))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.brand_item_newgoods
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var bindingUtil:ViewDataBinding = holder.dataBinding
        val get = list.get(position)
        with(holder!!.itemView) {
            Glide.with(context).load(get.list_pic_url).into(new_pic_url)
        }
        bindingUtil.setVariable(BR.vmNewGoodsList,get)
    }

    override fun getItemCount(): Int = list.size

    /**
     * 加载完数据刷新到界面的data
     */
    fun refreshData(lt:List<DataX>){
        list = lt
        notifyDataSetChanged()
    }

}
