package com.example.myapplication.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapter.newgoods.NewGoodsAdapter
import com.example.myapplication.app.Constants
import com.example.myapplication.data.newgoods.FilterCategory
import com.example.myapplication.databinding.ActivityNewGoodsBinding
import com.example.myapplication.viewmodel.BindNewGoodsViewModel
import com.shop.base.BaseActivity
import kotlinx.android.synthetic.main.hot_pop.*
import kotlinx.android.synthetic.main.hot_pop.view.*

class NewGoodsActivity : BaseActivity<BindNewGoodsViewModel, ActivityNewGoodsBinding>(
    R.layout.activity_new_goods,
    BindNewGoodsViewModel::class.java
), View.OnClickListener {
    //是否是新品
    var isNew = 1
    var page = 1
    var size = 100
    var order: String? = null
    var sort: String? = null
    var categoryId = 0

    var view: View? = null

    var bindingUtil: ViewDataBinding? = null

    var popupWindow: PopupWindow? = null


    var newGoodsAdapter: NewGoodsAdapter? = null

    var it: List<FilterCategory>? = null

    /**
     * 组装当前的接口参数
     * @return
     */
    private fun getParam(): HashMap<String, String>? {
        val map = HashMap<String, String>()
        map["isNew"] = isNew.toString()
        map["page"] = page.toString()
        map["size"] = size.toString()
        map["order"] = order!!
        map["sort"] = sort!!
        map["categoryId"] = categoryId.toString()
        return map
    }


    private fun showNewGoods() {
        mDataBinding!!.recyGoodList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        newGoodsAdapter = NewGoodsAdapter(this)
        mDataBinding!!.recyGoodList.adapter = newGoodsAdapter
        initVm()
    }

    fun initVm() {
        mViewModel!!.dataX.observe(this, {
            newGoodsAdapter!!.refreshData(it)
        })
    }

    private fun initClick() {
        mDataBinding!!.layoutPrice.setOnClickListener(this)
        mDataBinding!!.txtAll.setOnClickListener(this)
        mDataBinding!!.txtSort.setOnClickListener(this)
    }

    override fun initView() {
        mViewModel!!.getHot()
        showHot()
        order = Constants.ASC
        sort = Constants.DEFAULT
        getParam()?.let { mViewModel.getGoodList(it) }
        showNewGoods()
        initClick()
        mDataBinding!!.layoutPrice.setTag(0)
        mDataBinding!!.txtAll.setTextColor(this.resources.getColor(R.color.red))
        newPop()
    }

    private fun showHot() {
        mViewModel!!.hotdata.observe(this, Observer {
            mDataBinding!!.setVariable(BR.vmNewGoodsActivity, it)
            Toast.makeText(this, it.img_url, Toast.LENGTH_SHORT).show()
            Glide.with(this).load(it.img_url).into(mDataBinding!!.imgHotgood)
        })
    }

    fun newPop() {
        mViewModel!!.filterCategory.observe(this, Observer { this.it = it })
        view = LayoutInflater.from(this).inflate(R.layout.hot_pop, null)
        bindingUtil = DataBindingUtil.bind(this.view!!)
        popupWindow = PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 300)
        popupWindow!!.isAttachedInDecor = true
    }

    fun pop() {
        popupWindow!!.showAsDropDown(mDataBinding!!.txtSort)
        bindingUtil!!.setVariable(BR.popClick, PopClick())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.layout_price -> {
                val tag = mDataBinding!!.layoutPrice.getTag()
                if (tag == 0) {
                    resetPriceState();
                    priceStateUp();
                    mDataBinding!!.layoutPrice.setTag(1);
                    order = Constants.ASC
                } else if (tag == 1) {
                    resetPriceState();
                    priceStateDown();
                    mDataBinding!!.layoutPrice.setTag(0);
                    order = Constants.DESC
                }
                sort = Constants.PRICE
                Toast.makeText(this, "$tag", Toast.LENGTH_SHORT).show()
                getParam()?.let { mViewModel.getGoodList(it) }
                if (popupWindow != null) {
                    popupWindow!!.dismiss();
                }
            }
            R.id.txt_all -> {
                //恢复到默认状态
                resetPriceState();
                mDataBinding!!.txtAll.setTextColor(this.resources.getColor(R.color.red))
                sort = Constants.DEFAULT
                categoryId = 0
                getParam()?.let { mViewModel.getGoodList(it) }
                initVm()
                if (popupWindow != null) {
                    popupWindow!!.dismiss();
                }
            }
            R.id.txt_sort -> {
                //恢复到默认状态
                resetPriceState();
                mDataBinding!!.txtSort.setTextColor(this.resources.getColor(R.color.red))
                //弹出弹框
                pop()
            }
        }
    }

    private fun resetPriceState() {
        mDataBinding!!.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal)
        mDataBinding!!.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal)
        mDataBinding!!.txtPrice.setTextColor(this.resources.getColor(R.color.black))
        mDataBinding!!.txtAll.setTextColor(this.resources.getColor(R.color.black))
        mDataBinding!!.txtSort.setTextColor(this.resources.getColor(R.color.black))
        mDataBinding!!.layoutPrice.setTag(0)
    }

    /**
     * 按价格升序排序
     */
    private fun priceStateUp() {
        mDataBinding!!.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select)
        mDataBinding!!.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal)
        mDataBinding!!.txtPrice.setTextColor(this.resources.getColor(R.color.red))
    }

    /**
     * 价格的降序排列
     */
    private fun priceStateDown() {
        mDataBinding!!.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal)
        mDataBinding!!.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select)
        mDataBinding!!.txtPrice.setTextColor(this.resources.getColor(R.color.red))
    }


    inner class PopClick {
        fun all() {
            val name1: String = view!!.all.getText().toString()
            initList(name1, view!!.all)
        }

        fun home() {
            val name1: String = view!!.home.getText().toString()

            initList(name1, view!!.home)
        }

        fun dinner() {
            val name1: String = view!!.dinner.getText().toString()
            initList(name1, view!!.dinner)
        }

        fun chider() {
            val name1: String = view!!.chider.getText().toString()
            initList(name1, view!!.chider)
        }

        fun cargo() {
            val name1: String = view!!.cargo.getText().toString()
            initList(name1, view!!.cargo)
        }

        fun diet() {
            val name1: String = view!!.diet.getText().toString()
            initList(name1, view!!.diet)
        }
    }

    protected fun initList(name1: String, txt: TextView) {
        initColor()
        for (item in it!!) {
            if (item.name == name1) {
                sort = Constants.CATEGORY
                order = Constants.ASC
                categoryId = item.id
                getParam()?.let { mViewModel.getGoodList(it) }
                initVm()
                popupWindow!!.dismiss()
                txt.setTextColor(this.resources.getColor(R.color.red))
                txt.setBackgroundResource(R.drawable.shap)
                break
            }
        }
    }


    @SuppressLint("ResourceType")
    private fun initColor() {
        view!!.all.setTextColor(this.resources.getColor(R.color.black))
        view!!.home.setTextColor(this.resources.getColor(R.color.black))
        view!!.dinner.setTextColor(this.resources.getColor(R.color.black))
        view!!.chider.setTextColor(this.resources.getColor(R.color.black))
        view!!.cargo.setTextColor(this.resources.getColor(R.color.black))
        view!!.diet.setTextColor(this.resources.getColor(R.color.black))

        view!!.all.setBackgroundResource(R.drawable.shap1)
        view!!.home.setBackgroundResource(R.drawable.shap1)
        view!!.dinner.setBackgroundResource(R.drawable.shap1)
        view!!.chider.setBackgroundResource(R.drawable.shap1)
        view!!.cargo.setBackgroundResource(R.drawable.shap1)
        view!!.diet.setBackgroundResource(R.drawable.shap1)
    }


    override fun initData() {
    }

    override fun initVariable() {
    }

    override fun initVM() {

    }
}
