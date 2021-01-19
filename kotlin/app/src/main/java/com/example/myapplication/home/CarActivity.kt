package com.example.myapplication.home

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.adapter.car.Gallery
import com.example.myapplication.adapter.car.Goods
import com.example.myapplication.adapter.car.GoodsListAdapter
import com.example.myapplication.base.IItemClick
import com.example.myapplication.databinding.ActivityCarBinding
import com.example.myapplication.viewmodel.BindCarViewModel
import com.shop.base.BaseActivity
import com.youth.banner.loader.ImageLoader

class CarActivity : BaseActivity<BindCarViewModel, ActivityCarBinding>(R.layout.activity_car,
    BindCarViewModel::class.java) {

    private val h5 = """<html>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
            <head>
                <style>
                    p{
                        margin:0px;
                    }
                    img{
                        width:100%;
                        height:auto;
                    }
                </style>
            </head>
            <body>
                word
            </body>
        </html>"""

    var gallery: MutableList<List<Gallery>> = mutableListOf()

    var goodsListAdapter: GoodsListAdapter? = null
    var list: MutableList<Goods> = mutableListOf()
    override fun initView() {
        mDataBinding!!.rvList.layoutManager = GridLayoutManager(this, 2)
        var layouts = SparseArray<Int>()
        layouts.put(R.layout.car_item_goodslist, BR.vmCarGoods)
        goodsListAdapter = GoodsListAdapter(this, list, layouts, ItemClickGoodsList())
        mDataBinding!!.rvList.adapter = goodsListAdapter
    }

    override fun initVM() {
        mViewModel!!.gallery.observe(this, Observer {
            mDataBinding!!.banner.setImages(it)
            mDataBinding!!.banner.setImageLoader(MyBannerAdapter())
            mDataBinding!!.banner.start()
        })

        mViewModel!!.info.observe(this, Observer {
            mDataBinding!!.setVariable(BR.vmCarActivity, it)
            mDataBinding!!.webView.loadDataWithBaseURL("about:blank",
                h5.replace("word", it.goods_desc),
                "text/html",
                "utf-8",
                null);
        })

        mViewModel!!.issue.observe(this, Observer {
            for (item in it) {
                var view = LayoutInflater.from(this).inflate(R.layout.details_item_problem, null)
                var binding: ViewDataBinding? = DataBindingUtil.bind(view)
                binding!!.setVariable(BR.vmCarIss, item)
                view.setTag(item)
                mDataBinding!!.linearCommonProblem.addView(view)

            }
        })

        mViewModel!!.attribute.observe(this, Observer {
            for (item in it) {
                var view = LayoutInflater.from(this).inflate(R.layout.details_item_attribute, null)
                var binding: ViewDataBinding? = DataBindingUtil.bind(view)
                binding!!.setVariable(BR.vmCarAtt, item)
                view.setTag(item)
                mDataBinding!!.linearAttribute.addView(view)
            }
        })

        mViewModel!!.goodsList.observe(this, Observer {
            list.clear()
            list.addAll(it)
            goodsListAdapter!!.notifyDataSetChanged()
        })
    }


    override fun initData() {
        val id = intent.getStringExtra("id")
        mViewModel!!.getDetails(id!!)
        mViewModel!!.getRelated(id!!)
    }

    override fun initVariable() {
    }

    inner class ItemClickGoodsList : IItemClick<Goods> {
        override fun itemClick(data: Goods) {
            Toast.makeText(this@CarActivity, "${data.name}", Toast.LENGTH_SHORT).show()
        }

    }

    inner class MyBannerAdapter : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            var item = path as Gallery
            Glide.with(context!!).load(item.img_url).into(imageView!!)
        }
    }

}