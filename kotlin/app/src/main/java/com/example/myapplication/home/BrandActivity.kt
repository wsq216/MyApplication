package com.example.myapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.brand.BrandAdapter
import com.example.myapplication.viewmodel.BindBrandViewModel
import kotlinx.android.synthetic.main.activity_brand.*

class BrandActivity : AppCompatActivity() {

    var viewModel : BindBrandViewModel? = null
    var brandAdapter : BrandAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand)
        viewModel = ViewModelProvider(this).get(BindBrandViewModel::class.java)
        initView()
    }

    private fun initView() {
        viewModel!!.brandData("1","1000")
        showBrand()
    }

    private fun showBrand() {
        rv_brand!!.layoutManager = LinearLayoutManager(this)
        rv_brand!!.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        brandAdapter = BrandAdapter(this)
        rv_brand!!.adapter = brandAdapter
        initBrand()
    }

    private fun initBrand() {
        viewModel!!.data.observe(this,{
            brandAdapter!!.refreshData(it)
        })
    }
}