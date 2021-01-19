package com.example.myapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.category.CateGroyAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.databinding.ActivityCateGoryBinding
import com.example.myapplication.model.zt.BrotherCategory
import com.example.myapplication.model.zt.CurrentCategory
import com.example.myapplication.model.zt.DataX
import com.example.myapplication.viewmodel.BindCateGoryViewModel
import com.google.android.material.tabs.TabLayout
import com.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_cate_gory.*

class CateGoryActivity :
    BaseActivity<BindCateGoryViewModel, ActivityCateGoryBinding>(R.layout.activity_cate_gory,
        BindCateGoryViewModel::class.java) {


    var brotherCategory: List<BrotherCategory> = mutableListOf()

    var list: MutableList<DataX> = mutableListOf()

    var name: String? = null

    var cateGoryAdaptetr: CateGroyAdapter? = null

    var id: Int? = null

    var count: Int? = 0

    override fun initView() {
        mDataBinding!!.tabMode.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                mViewModel.getCategoryTab(mViewModel!!.brotherCategory.value!!.get(p0!!.position).id.toString())
                id = p0!!.position
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

        })

        mDataBinding!!.recyCateList.layoutManager = GridLayoutManager(this, 2)
        var layouts = SparseArray<Int>()
        layouts!!.put(R.layout.category_item_list, BR.vmCateGoryList)
        cateGoryAdaptetr = CateGroyAdapter(this, list, layouts, ItemClick())
        mDataBinding!!.recyCateList.adapter = cateGoryAdaptetr

    }

    class ItemClick : IItemClick<DataX> {
        override fun itemClick(data: DataX) {

        }

    }

    override fun initVM() {
        mViewModel!!.brotherCategory.observe(this, Observer {
            if (count == 0) {
                for (i in it) {
                    mDataBinding!!.tabMode.addTab(tabMode.newTab().setText(i.name))
                    count = 1
                }
            }
            for ((i, item) in it.withIndex()) {
                if (item.name == name) {
                    val tabAt = mDataBinding!!.tabMode.getTabAt(i)
                    tabAt!!.select()
                    mViewModel!!.getCategoryList(item.id.toString())
                    name = null
                    break
                }
            }
            if (it.size > 0) {
                mViewModel!!.getCategoryList(it.get(id!!).id.toString())
            }
        })

        mViewModel!!.currentCategory.observe(this, Observer {
            mDataBinding.setVariable(BR.vmCateGoryActivity, it)
        })

        mViewModel!!.dataX.observe(this, Observer {
            list.clear()
            list.addAll(it)
            cateGoryAdaptetr!!.notifyDataSetChanged()
        })

    }

    override fun initData() {
        val id = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        Toast.makeText(this, "$name", Toast.LENGTH_SHORT).show()
        mViewModel.getCategoryTab(id!!)
    }

    override fun initVariable() {
    }

}