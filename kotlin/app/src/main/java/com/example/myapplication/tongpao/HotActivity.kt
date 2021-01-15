package com.example.myapplication.tongpao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.util.SparseArray
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.tp.TpAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.data.hot.Discover
import com.example.myapplication.databinding.ActivityHotBinding
import com.example.myapplication.viewmodel.tongpao.BindHotViewModel
import com.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_hot.*
import java.util.*

class HotActivity() : BaseActivity<BindHotViewModel,ActivityHotBinding>(R.layout.activity_hot,BindHotViewModel::class.java) {
    var list :List<Discover> = listOf<Discover>()

    var brandAdapter : TpAdapter? = null

    override fun initView() {
        rv_hot!!.layoutManager = LinearLayoutManager(this)
        rv_hot!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun initTp() {

    }

    override fun initVM() {
        mViewModel!!.discover.observe(this, androidx.lifecycle.Observer{
            brandAdapter!!.refreshData(it)
        })
    }

    override fun initData() {
        mViewModel!!.getTp()

        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var array = SparseArray<Int>()
        array.put(R.layout.adapter_tp1, BR.vmTp1)
        array.put(R.layout.adapter_tp2,BR.vmTp2)
        array.put(R.layout.adapter_tp3,BR.vmTp3)
        brandAdapter = TpAdapter(this,list,array,ItemClick())
        rv_hot!!.adapter = brandAdapter
        initVM()

    }

    override fun initVariable() {

    }

    inner class ItemClick: IItemClick<Discover> {

        override fun itemClick(data: Discover) {
            Toast.makeText(this@HotActivity,data.title,Toast.LENGTH_SHORT).show()
        }
    }

}