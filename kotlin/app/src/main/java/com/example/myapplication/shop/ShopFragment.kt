package com.example.myapplication.shop


import android.util.SparseArray
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.shop.CarAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.data.shop.Cart
import com.example.myapplication.databinding.FragmentShopBinding

import com.shop.base.BaseFragment
import com.shop.viewmodel.shop.ShopViewModel
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment( val Iid: Int) : BaseFragment<ShopViewModel, FragmentShopBinding>(Iid,ShopViewModel::class.java) {


    companion object{
        val instance by lazy { ShopFragment(R.layout.fragment_shop) }
    }

    lateinit var list:MutableList<Cart>
    lateinit var carAdapter:CarAdapter

    var isEditor:Boolean = false

    override fun initView() {
        list = mutableListOf()
        var arr = SparseArray<Int>()
        arr.put(R.layout.layout_car_item,BR.car)
        carAdapter = CarAdapter(context!!,list,arr,ClickItem())
        recy_car.layoutManager = LinearLayoutManager(context)
        recy_car.adapter = carAdapter

        mDataBinding.setVariable(BR.shopClick,ClickEvt())
        carAdapter.addChangeEvt(ItemChangeSelect())
    }

    override fun initVM() {
        mViewModel.carData.observe(this, Observer {
            list.clear()
            list.addAll(it.cartList)
            carAdapter.notifyDataSetChanged()
        })
    }

    override fun initData() {
        mViewModel.getCar()
    }

    override fun initVariable() {
    }

    fun changeEditUI(){
        carAdapter.isEditor = isEditor
        carAdapter.notifyDataSetChanged()
    }

    /**
     * 修改列表条目选择状态更新总价和数量
     */
    inner class ItemChangeSelect:CarAdapter.ChangeEvt{
        override fun click() {
            var arr = mViewModel.getCarTotalNormal()
            if(arr.size == 3){
                mDataBinding.checkboxAll.setText("全选("+arr[0]+")")
                mDataBinding.txtPrice.setText("￥"+arr[1])
                mDataBinding.checkboxAll.isChecked = if(arr[2] == 0) true else false
            }
        }

    }

    inner class ClickItem:IItemClick<Cart>{
        override fun itemClick(data: Cart) {
            TODO("Not yet implemented")
        }

    }

    /**
     * 当前界面的点击事件
     */
    inner class ClickEvt{

        /**
         * 条目上的选中状态的点击事件
         */
        fun itemCheckboxClick(view:View){
            if(view is CheckBox){
                var checkbox = view as CheckBox
                var car:Cart = checkbox.tag as Cart
                if(isEditor){
                    car.select_eidt = checkbox.isChecked
                }else{
                    car.select_normal = checkbox.isChecked
                }
            }

        }

        /**
         * boolean true权限  false 非全选
         */
        fun clickAll(view:View){
            if(view is CheckBox){
                var checkbox = view as CheckBox
                /*if(checkbox.isChecked){
                    mDataBinding.checkboxAll.isChecked = true
                }else{
                    mDataBinding.checkboxAll.isChecked = false
                }*/
                if(isEditor){
                    mViewModel.updateCarStateEidtor(checkbox.isChecked)
                }else{
                    mViewModel.updateCarStateNormal(checkbox.isChecked)
                }
                //刷新总价和总数量
                var arr = mViewModel.getCarTotalNormal()
                if(arr.size == 2){
                    mDataBinding.checkboxAll.setText("全选("+arr[0]+")")
                    mDataBinding.txtPrice.setText("￥"+arr[1])
                }
                carAdapter.notifyDataSetChanged()
            }

        }

        /**
         * boolean true编辑 false完成
         */
        fun clickEdit(view:View){
            if(view is TextView){
                var txt = view as TextView
                if(txt.text.toString() === "编辑"){
                    mDataBinding.txtEdit.setText("完成")
                    mDataBinding.txtSubmit.setText("删除所选")
                    isEditor = true
                }else{
                    mDataBinding.txtEdit.setText("编辑")
                    mDataBinding.txtSubmit.setText("下单")
                    isEditor = false
                }
                //控制界面在编辑和非编辑状态下的显示
                changeEditUI()
            }

        }

        /**
         * 是否是提交
         */
        fun clickSubit(view:View){
            if(view is TextView){
                var txt = view as TextView
                if(txt.text.toString() === "下单"){
                    //下单
                }else{
                    //删除对应的数据
                }
            }

        }


    }


}
