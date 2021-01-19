package com.example.myapplication.home


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapter.home.*
import com.example.myapplication.base.IItemClick
import com.example.myapplication.data.*
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.tongpao.HotActivity
import com.example.myapplication.viewmodel.BindHomeViewModel
import com.shop.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.home_item_category.view.*
import kotlinx.android.synthetic.main.home_item_channel.view.*
import kotlinx.android.synthetic.main.home_item_channel.view.txt_home_title


class HomeFragment : BaseFragment<BindHomeViewModel, FragmentHomeBinding>(
    R.layout.fragment_home,
    BindHomeViewModel::class.java
),View.OnClickListener {


    //点击事件
    private fun initClick() {
        //品牌制作商直供  点击事件
//        mDataBinding!!.txtBrandTitle.setOnClickListener(this)
//        mDataBinding!!.txtNewgoodTitle.setOnClickListener(this)
//        mDataBinding!!.txtTitTitle.setOnClickListener(this)
    }

    //点击监听
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txt_brand_title -> startActivity(Intent(context, BrandActivity::class.java))
            R.id.txt_newgood_title -> startActivity(Intent(context, NewGoodsActivity::class.java))
            R.id.txt_tit_title -> startActivity(Intent(context, HotActivity::class.java))

        }
    }

    override fun initView() {
        mViewModel!!.getHome()
        //banner图
        showBanner()
        //商品类型
        showChannel()
        //品牌制造商
        showBrand()
        //新品首发
        showNewGoodsList()
        //人气推荐
        showHotGoodsList()
        //专题
        showTopicList()
        //居家
        showCategoryList()
        mDataBinding!!.setVariable(BR.homeClick, TitleClick())
    }

    //居家
    private fun showCategoryList() {
        mViewModel!!.category.observe(this, {
            for (item in it) {
                var view = LayoutInflater.from(context).inflate(R.layout.home_item_category, null)
                var binging: ViewDataBinding? = DataBindingUtil.bind(view)
                binging?.setVariable(BR.vmCateGory, item)
                view!!.recy_category.layoutManager = StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
                var layouts = SparseArray<Int>()
                layouts.put(R.layout.home_item_categorygoods,BR.vmGoods)

                var categoryListAdapter = CategoryListAdapter(context, item.goodsList,layouts,ItemClick())
                view!!.recy_category.adapter = categoryListAdapter
                initCateGory(item.goodsList, categoryListAdapter)
                view!!.setTag(item.goodsList)
                mDataBinding!!.linesr.addView(view)
            }
        })
    }

    private fun initCateGory(goodsList: List<Goods>, categoryListAdapter: CategoryListAdapter) {
        categoryListAdapter.refreshData(goodsList)
    }


    //专题
    private fun showTopicList() {
        var layout = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        mDataBinding!!.recyTopic.layoutManager = layout
        var topicListAdapter = TopicListAdapter(context)
        mDataBinding!!.recyTopic.adapter=topicListAdapter
        initTopic(topicListAdapter)
    }

    private fun initTopic(topicListAdapter: TopicListAdapter) {
        mViewModel!!.topic.observe(this, Observer {
            topicListAdapter.refreshData(it)
        })
    }

    //人气推荐
    private fun showHotGoodsList() {
        var layout = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mDataBinding!!.recyHotgoods.layoutManager = layout
        var hotGoodsAdapter = HotGoodsAdapter(context)
        mDataBinding!!.recyHotgoods.adapter=hotGoodsAdapter
        initHotGoods(hotGoodsAdapter)
    }

    private fun initHotGoods(hotGoodsAdapter: HotGoodsAdapter) {
        mViewModel!!.hot.observe(this, Observer {
            hotGoodsAdapter.refreshData(it)
        })
    }

    //新品首发
    private fun showNewGoodsList() {
        var layout = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mDataBinding!!.recyNewgood.layoutManager = layout
        var newGoodsAdapter = NewGoodsAdapter(context)
        mDataBinding!!.recyNewgood.adapter=newGoodsAdapter
        initNewGoods(newGoodsAdapter)
    }

    private fun initNewGoods(newGoodsAdapter: NewGoodsAdapter) {
        mViewModel!!.newGoods.observe(this, Observer {
            newGoodsAdapter.refreshData(it)
        })
    }

    //品牌制造商
    private fun showBrand() {

        var layout = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mDataBinding!!.recyBrand.layoutManager =layout
        var brandAdapter = BrandAdapter(context)
        mDataBinding!!.recyBrand.adapter = brandAdapter
        initVM(brandAdapter)
    }

    //刷新适配器
    private fun initVM(baseAdapter: BrandAdapter) {
        mViewModel!!.brand.observe(this, Observer {
            baseAdapter.refreshData(it)
        })
    }


    //商品类型
    private fun showChannel() {
        mViewModel!!.channel.observe(this, {
            for (i in it.indices) {
                //获取布局
                val view: View = LayoutInflater.from(activity).inflate(
                    R.layout.home_item_channel,
                    null
                )
                Glide.with(this).load(it.get(i).icon_url).into(view.image)
                view.txt_home_title.setText(it.get(i).name)

                view.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
                )

                mDataBinding!!.linerChannel.addView(view)

                view.setTag(it.get(i))

                //条目点击事件
                view.setOnClickListener { v ->
                    //强转 as
                    val views = view.tag as Channel
                    val url: String = views.url
                    val name: String = views.name
                    val i1 = url.indexOf("=")
                    val substring = url.substring(i1 + 1)
                    Toast.makeText(context, substring, Toast.LENGTH_LONG).show()
                    var intent = Intent(context,CateGoryActivity::class.java)
                    intent.putExtra("id",substring)
                    intent.putExtra("name",name)
                    startActivity(intent)
                }
            }
        })



    }

    //banner图
    private fun showBanner() {
        mViewModel!!.bannner.observe(this, Observer {
            mDataBinding!!.bannerHome.setImages(it)
            mDataBinding!!.bannerHome.setImageLoader(MyBannerAdapter())
            mDataBinding!!.bannerHome.start()
        })

    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {
    }

    inner class ItemClick : IItemClick<Goods>{
        override fun itemClick(data: Goods) {
            var intent = Intent(context,CarActivity::class.java)
            intent.putExtra("id",data.id.toString())
            startActivity(intent)
        }

    }

    inner class TitleClick{
        //品牌直供
        fun clickBrand(){
            startActivity(Intent(context, BrandActivity::class.java))
        }
        //新品发布
        fun clickNewGood(){
            startActivity(Intent(context, NewGoodsActivity::class.java))
        }
        //人气推荐
        fun clickHotGood(){

        }
        //专题
        fun clickTopic(){
            startActivity(Intent(context, HotActivity::class.java))
        }
    }
}