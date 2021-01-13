package com.example.myapplication.adapter.tp

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.data.hot.Discover


class TpAdapter(context: Context, list: List<Discover>, layouts: SparseArray<Int>,clickEvts: IItemClick<Discover>) :
    BaseAdapter<Discover>(context, list, layouts,clickEvts) {
    override fun layoutId(position: Int): Int {
        var url = list.get(position).filePathList
        if (url.size == 0) {
            return R.layout.adapter_tp1
        } else if (url.size == 1) {
            return R.layout.adapter_tp2
        } else if (url.size == 3) {
            return R.layout.adapter_tp3
        }
        return R.layout.adapter_tp1
    }



    /**
     * 加载完数据刷新到界面的data
     */
    fun refreshData(it: List<Discover>) {
        list = it
        notifyDataSetChanged()
    }

    override fun bindData(binding: ViewDataBinding, data: Discover, layoutId: Int) {
        var url = data.filePathList
        if (context != null) {
            if (url.size == 0) {

            } else if (url.size == 1) {
                Glide.with(context).load(url.get(0).filePath)
                    .into(binding.root.findViewById(R.id.img12))
            } else if (url.size == 3) {
                Glide.with(context).load(url.get(0).filePath)
                    .into(binding.root.findViewById(R.id.img11))
                Glide.with(context).load(url.get(1).filePath)
                    .into(binding.root.findViewById(R.id.img22))
                Glide.with(context).load(url.get(2).filePath)
                    .into(binding.root.findViewById(R.id.img33))
            }
        }
    }

}