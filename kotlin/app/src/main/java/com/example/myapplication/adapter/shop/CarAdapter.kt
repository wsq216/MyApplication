package com.example.myapplication.adapter.shop

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.data.shop.Cart
import com.example.myapplication.widget.NumberSelect


/**
 *购物车列表
 */
class CarAdapter(
    context: Context,
    list:List<Cart>,
    layouts:SparseArray<Int>,
    click: IItemClick<Cart>
): BaseAdapter<Cart>(context,list,layouts,click) {


    //当前界面是否是编辑页面
    var isEditor:Boolean = false
    private lateinit var changeEvt:ChangeEvt
    fun addChangeEvt(changeEvt: ChangeEvt){
        this.changeEvt = changeEvt
    }

    override fun layoutId(position: Int): Int {
        return R.layout.layout_car_item
    }

    override fun bindData(binding: ViewDataBinding, data: Cart, layId: Int) {

        var checkbox = binding.root.findViewById<CheckBox>(R.id.checkbox)

        var txtName = binding.root.findViewById<TextView>(R.id.txt_name)
        var txtNumber = binding.root.findViewById<TextView>(R.id.txt_number)

        var txtEditTitle = binding.root.findViewById<TextView>(R.id.txt_edit_title)
        var numberSelect = binding.root.findViewById<NumberSelect>(R.id.layout_change)

        //绑定界面的数据对象
        if(isEditor){
            txtName.visibility = View.GONE
            txtNumber.visibility = View.GONE
            txtEditTitle.visibility = View.VISIBLE
            numberSelect.visibility = View.VISIBLE
            checkbox.isChecked = data.select_eidt
            //设置自定义数量选择器的布局
            numberSelect.addPage(R.layout.layout_number_change)
            //设置数量变化的点击操作
            numberSelect.addChangeNumber {
                //修改后的商品的数量
                data.number = it
            }
        }else{
            txtName.visibility = View.VISIBLE
            txtNumber.visibility = View.VISIBLE
            txtEditTitle.visibility = View.GONE
            numberSelect.visibility = View.GONE
            checkbox.isChecked = data.select_normal
        }
        txtNumber.setText("X"+data.number.toString())
        numberSelect.number = data.number

        checkbox.setOnClickListener {
            if(isEditor){
                data.select_eidt = checkbox.isChecked
            }else{
                data.select_normal = checkbox.isChecked
            }
            if(changeEvt != null){
                changeEvt.click()
            }
        }


    }

    interface ChangeEvt{
        fun click()
    }

}