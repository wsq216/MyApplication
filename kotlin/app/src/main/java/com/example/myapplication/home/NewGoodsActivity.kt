package com.example.myapplication.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapter.newgoods.NewGoodsAdapter
import com.example.myapplication.databinding.ActivityNewGoodsBinding
import com.example.myapplication.viewmodel.BindNewGoodsViewModel

class NewGoodsActivity : AppCompatActivity(), View.OnClickListener {
    //接口参数
    val ASC = "asc"
    val DESC = "desc"
    val DEFAULT = "default"
    val PRICE = "price"
    val CATEGORY = "category"
    var CATEGORYID = 0

    var view: View? = null

    var popupWindow: PopupWindow? = null

    var mBinging: ActivityNewGoodsBinding? = null

    var viewModel: BindNewGoodsViewModel? = null

    var newGoodsAdapter : NewGoodsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinging = DataBindingUtil.setContentView(this, R.layout.activity_new_goods)

        viewModel = ViewModelProvider(this).get(BindNewGoodsViewModel::class.java)

        initView()
        initViewList(ASC, DEFAULT, CATEGORYID)
        showNewGoods()
        initClick()
    }

    private fun showNewGoods() {
        mBinging!!.recyGoodList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        newGoodsAdapter = NewGoodsAdapter(this)
        mBinging!!.recyGoodList.adapter = newGoodsAdapter
        initVm()
    }

    private fun initVm() {
        viewModel!!.dataX.observe(this,{
            newGoodsAdapter!!.refreshData(it)
        })
    }

    private fun initClick() {
        mBinging!!.layoutPrice.setOnClickListener(this)
        mBinging!!.txtAll.setOnClickListener(this)
        mBinging!!.txtSort.setOnClickListener(this)
    }

    private fun initViewList(order: String, sort: String, categoryId: Int) {
        viewModel!!.newGoodsListData(order, sort, categoryId)
    }

    private fun initView() {
        viewModel!!.newGoodsData()
        showHot()
        mBinging!!.layoutPrice.setTag(0)
        mBinging!!.txtAll.setTextColor(this.resources.getColor(R.color.red))
        newPop()
    }

    private fun showHot() {
        viewModel!!.hotdata.observe(this, Observer{
            mBinging!!.txtInfo.setText(it.name)
            Glide.with(this).load(it.img_url).into(mBinging!!.imgHotgood)
        })
    }

    fun newPop() {
        view = LayoutInflater.from(this).inflate(R.layout.hot_pop, null)
        popupWindow = PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 300)
        popupWindow!!.isAttachedInDecor = true
    }

    fun pop() {
        popupWindow!!.showAsDropDown(mBinging!!.txtSort)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.layout_price -> {
                val tag = mBinging!!.layoutPrice.getTag()
                if (tag == 0) {
                    resetPriceState();
                    priceStateUp();
                    mBinging!!.layoutPrice.setTag(1);
                    viewModel!!.newGoodsListData(ASC, DEFAULT, CATEGORYID)
                    initViewList(ASC, PRICE, CATEGORYID)
                    initVm()
                } else if (tag == 1) {
                    resetPriceState();
                    priceStateDown();
                    mBinging!!.layoutPrice.setTag(0);
                    viewModel!!.newGoodsListData(DESC, DEFAULT, CATEGORYID)
                    initViewList(DESC, PRICE, CATEGORYID)
                    initVm()
                }
                if (popupWindow != null) {
                    popupWindow!!.dismiss();
                }
            }
            R.id.txt_all -> {
                //恢复到默认状态
                resetPriceState();
                mBinging!!.txtAll.setTextColor(this.resources.getColor(R.color.red))
                viewModel!!.newGoodsListData(ASC, DEFAULT, CATEGORYID)
                initVm()
                if (popupWindow != null) {
                    popupWindow!!.dismiss();
                }
            }
            R.id.txt_sort -> {
                //恢复到默认状态
                resetPriceState();
                mBinging!!.txtSort.setTextColor(this.resources.getColor(R.color.red))
                //弹出弹框
                pop()
            }
        }
    }

    private fun resetPriceState() {
        mBinging!!.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal)
        mBinging!!.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal)
        mBinging!!.txtPrice.setTextColor(this.resources.getColor(R.color.black))
        mBinging!!.txtAll.setTextColor(this.resources.getColor(R.color.black))
        mBinging!!.txtSort.setTextColor(this.resources.getColor(R.color.black))
        mBinging!!.layoutPrice.setTag(0)
    }

    /**
     * 按价格升序排序
     */
    private fun priceStateUp() {
        mBinging!!.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select)
        mBinging!!.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal)
        mBinging!!.txtPrice.setTextColor(this.resources.getColor(R.color.red))
    }

    /**
     * 价格的降序排列
     */
    private fun priceStateDown() {
        mBinging!!.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal)
        mBinging!!.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select)
        mBinging!!.txtPrice.setTextColor(this.resources.getColor(R.color.red))
    }
}
