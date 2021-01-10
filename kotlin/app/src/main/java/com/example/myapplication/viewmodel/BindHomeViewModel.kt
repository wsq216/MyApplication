package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.BR
import com.example.myapplication.data.*
import com.google.gson.Gson

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class BindHomeViewModel:ViewModel() {

    var bannner:MutableLiveData<List<Banner>> = MutableLiveData(listOf())
    var brand:MutableLiveData<List<Brand>> = MutableLiveData(listOf())
    var channel:MutableLiveData<List<Channel>> = MutableLiveData(listOf())
    var newGoods:MutableLiveData<List<NewGoods>> = MutableLiveData(listOf())
    var topic:MutableLiveData<List<Topic>> = MutableLiveData(listOf())
    var hot:MutableLiveData<List<HotGoods>> = MutableLiveData(listOf())
    var category:MutableLiveData<List<Category>> = MutableLiveData(listOf())


    fun  homeData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        var homeData = get("https://cdplay.cn/api/index")
        if(homeData != null){
            //banner图
            bannner.postValue(homeData.data.banner)
            //品牌制造商
            brand.postValue(homeData.data.brandList)
            //商品类型
            channel.postValue(homeData.data.channel)
            //新品首发
            newGoods.postValue(homeData.data.newGoodsList)
            //人气推荐
            hot.postValue(homeData.data.hotGoodsList)
            //专题
            topic.postValue(homeData.data.topicList)
            //居家
            category.postValue(homeData.data.categoryList)
        }else{
        }
    }

    /**
     * 网络请求
     */
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson<HomeData>(result, HomeData::class.java)
    }
}