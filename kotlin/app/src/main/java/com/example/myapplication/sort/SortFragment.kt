package com.example.myapplication.sort

import android.content.Intent
import android.util.SparseArray
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.adapter.sort.SortDataAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.data.sort.CurrentData
import com.example.myapplication.data.sort.IndexData
import com.example.myapplication.data.sort.SubCategory
import com.example.myapplication.databinding.FragmentSortBinding
import com.example.myapplication.home.CateGoryActivity
import com.example.myapplication.viewmodel.BindSortViewModel
import com.shop.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_sort.*
import q.rorbin.verticaltablayout.VerticalTabLayout.OnTabSelectedListener
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView
import q.rorbin.verticaltablayout.widget.QTabView
import q.rorbin.verticaltablayout.widget.TabView


class SortFragment : BaseFragment<BindSortViewModel,FragmentSortBinding>(R.layout.fragment_sort,BindSortViewModel::class.java) {
    companion object{
        val instance by lazy { SortFragment() }
    }

    private lateinit var tabAdapter:TabAdapter
    private var sortList:MutableList<IndexData.Category> = mutableListOf()

    private var sortDataList:MutableList<SubCategory> = mutableListOf()
    private lateinit var sortDataAdapter:SortDataAdapter

    override fun initView() {

        //竖导航tab的点击监听
        mDataBinding.verticaltablayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabView?, position: Int) {
                val get = mViewModel.sortData.value!!.categoryList.get(position)
                mViewModel.updateCurrentTab(get)
//                Glide.with(context!!).load(get.banner_url).into(img)
                mDataBinding.setVariable(BR.vmSortListData,mViewModel)
                var id = mViewModel.sortData.value!!.categoryList.get(position).id
                mViewModel.getSortListData(id.toString())
            }

            override fun onTabReselected(tab: TabView?, position: Int) {

            }
        })
        tabAdapter = MyTabAdapter()
        mDataBinding.verticaltablayout.setTabAdapter(tabAdapter)

        var arr = SparseArray<Int>()
        arr.put(R.layout.sort_item_sortlist, BR.vmCateGory)
        sortDataAdapter = SortDataAdapter(context!!,sortDataList,arr,ItemClick())
        mDataBinding.recySort.layoutManager = GridLayoutManager(context,3)
        mDataBinding.recySort.adapter = sortDataAdapter


    }

    override fun initVM() {
        //竖导航 加载数据回来的监听
        mViewModel.sortData.observe(this, Observer {
            //动态的添加竖导航tab
            for (item in it.categoryList) {
                //创建显示tab的样式组件
                var tabTitle = ITabView.TabTitle.Builder().setContent(item.name).build()
                //创建tab的显示View，并且添加到竖导航
                mDataBinding.verticaltablayout.addTab(QTabView(context).setTitle(tabTitle))
            }
            mDataBinding.setVariable(BR.vmSortListData,mViewModel)
            //默认请求第一个tab对应的列表数据
            if(it.categoryList.size > 0){
                mViewModel.updateCurrentTab(mViewModel.sortData.value!!.categoryList.get(0))
                mViewModel.getSortListData(it.categoryList.get(0).id.toString())
            }
        })

        //竖导航列表详情数据
        mViewModel.sortDataList.observe(this, Observer {
            sortDataList.clear()
            sortDataList.addAll(it.currentCategory.subCategoryList)
            sortDataAdapter.notifyDataSetChanged()
        })

    }

    override fun initData() {
        mViewModel.getSortData("0")
    }

    override fun initVariable() {
    }

    //adapter 和 viewpager结合使用
    inner class MyTabAdapter:TabAdapter {
        override fun getCount(): Int {
            return sortList.size
        }

        override fun getBadge(position: Int): ITabView.TabBadge {
            TODO("Not yet implemented")
        }

        override fun getIcon(position: Int): ITabView.TabIcon {
            TODO("Not yet implemented")
        }

        override fun getTitle(position: Int): ITabView.TabTitle {
            TODO("Not yet implemented")
        }

        override fun getBackground(position: Int): Int {
            TODO("Not yet implemented")
        }

    }

    inner class ItemClick:IItemClick<SubCategory>{
        override fun itemClick(data: SubCategory) {
            var intent = Intent(context,CateGoryActivity::class.java)
            intent.putExtra("id",data.id.toString())
            intent.putExtra("name",data.name)
            startActivity(intent)
        }

    }
}