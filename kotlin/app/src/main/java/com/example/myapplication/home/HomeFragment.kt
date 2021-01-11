package com.example.myapplication.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapter.home.*
import com.example.myapplication.data.*
import com.example.myapplication.databinding.FragmentHomeBinding


import com.example.myapplication.viewmodel.BindHomeViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.home_item_category.view.*
import kotlinx.android.synthetic.main.home_item_channel.view.*
import kotlinx.android.synthetic.main.home_item_channel.view.txt_home_title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class HomeFragment : Fragment(),View.OnClickListener {

    var mBinging : FragmentHomeBinding? = null
    var viewModel: BindHomeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mBinging =  DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home,container,false);
//        mBinging = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(BindHomeViewModel::class.java)
        initView()
        initClick()
        return mBinging?.root
    }
    //点击事件
    private fun initClick() {
        //品牌制作商直供  点击事件
        mBinging!!.txtBrandTitle.setOnClickListener(this)
        mBinging!!.txtNewgoodTitle.setOnClickListener(this)
    }

    //点击监听
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txt_brand_title->startActivity(Intent(context,BrandActivity::class.java))
            R.id.txt_newgood_title->startActivity(Intent(context,NewGoodsActivity::class.java))
        }
    }

    private fun initView() {
        viewModel!!.homeData()
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
    }

    //居家
    private fun showCategoryList() {
        viewModel!!.category.observe(this, {
            for (item in it){
                var view = LayoutInflater.from(context).inflate(R.layout.home_item_category,null)
                var binging : ViewDataBinding? = DataBindingUtil.bind(view)
                binging?.setVariable(BR.vmCateGory,item)
                view!!.recy_category.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                var categoryListAdapter = CategoryListAdapter(context,item.goodsList)
                view!!.recy_category.adapter=categoryListAdapter
                initCateGory(item.goodsList,categoryListAdapter)
                view!!.setTag(item.goodsList)
                mBinging!!.linesr.addView(view)
            }
        })
    }

    private fun initCateGory(goodsList: List<Goods>, categoryListAdapter: CategoryListAdapter) {
        categoryListAdapter.refreshData(goodsList)
    }


    //专题
    private fun showTopicList() {
        var layout = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)
        mBinging!!.recyTopic.layoutManager = layout
        var topicListAdapter = TopicListAdapter(context)
        mBinging!!.recyTopic.adapter=topicListAdapter
        initTopic(topicListAdapter)
    }

    private fun initTopic(topicListAdapter: TopicListAdapter) {
        viewModel!!.topic.observe(this, Observer{
            topicListAdapter.refreshData(it)
        })
    }

    //人气推荐
    private fun showHotGoodsList() {
        var layout = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        mBinging!!.recyHotgoods.layoutManager = layout
        var hotGoodsAdapter = HotGoodsAdapter(context)
        mBinging!!.recyHotgoods.adapter=hotGoodsAdapter
        initHotGoods(hotGoodsAdapter)
    }

    private fun initHotGoods(hotGoodsAdapter: HotGoodsAdapter) {
        viewModel!!.hot.observe(this,{
            hotGoodsAdapter.refreshData(it)
        })
    }

    //新品首发
    private fun showNewGoodsList() {
        var layout = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        mBinging!!.recyNewgood.layoutManager = layout
        var newGoodsAdapter = NewGoodsAdapter(context)
        mBinging!!.recyNewgood.adapter=newGoodsAdapter
        initNewGoods(newGoodsAdapter)
    }

    private fun initNewGoods(newGoodsAdapter: NewGoodsAdapter) {
        viewModel!!.newGoods.observe(this,{
            newGoodsAdapter.refreshData(it)
        })
    }

    //品牌制造商
    private fun showBrand() {

        var layout = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        mBinging!!.recyBrand.layoutManager =layout
        var brandAdapter = BrandAdapter(context)
        mBinging!!.recyBrand.adapter = brandAdapter
        initVM(brandAdapter)
    }

    //刷新适配器
    private fun initVM(baseAdapter: BrandAdapter) {
        viewModel!!.brand.observe(this,{
            baseAdapter.refreshData(it)
        })
    }


    //商品类型
    private fun showChannel() {
        viewModel!!.channel.observe(this,{
            for (i in it.indices) {
                //获取布局
                val view: View = LayoutInflater.from(activity).inflate(R.layout.home_item_channel, null)
                Glide.with(this).load(it.get(i).icon_url).into(view.image)
                view.txt_home_title.setText(it.get(i).name)

                view.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
                )

                mBinging!!.linerChannel.addView(view)

                view.setTag(it.get(i))

                //条目点击事件
                view.setOnClickListener { v ->
                    //强转 as
                    val views = view.tag as Channel
                    val url: String = views.url
                    val name: String = views.name
                    val i1 = url.indexOf("=")
                    val substring = url.substring(i1 + 1)
                    Toast.makeText(context, name, Toast.LENGTH_LONG).show()
//                val intent = Intent(activity, CategoryActivity::class.java)
//                intent.putExtra("name", name)
//                intent.putExtra("url", substring)
//                startActivity(intent)
                }
            }
        })



    }

    //banner图
    private fun showBanner() {
        viewModel!!.bannner.observe(this,{
            mBinging!!.bannerHome.setImages(it)
            mBinging!!.bannerHome.setImageLoader(MyBannerAdapter())
            mBinging!!.bannerHome.start()
        })

    }



}