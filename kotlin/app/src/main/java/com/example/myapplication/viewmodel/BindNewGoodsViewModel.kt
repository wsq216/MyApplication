package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.HotGoods
import com.example.myapplication.data.newgoods.*
import com.google.gson.Gson

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class BindNewGoodsViewModel:ViewModel() {

    var hotdata:MutableLiveData<BannerInfo> = MutableLiveData()



    fun  newGoodsData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        var hotData = get("https://cdwan.cn/api/goods/hot")
        if(hotData != null){
            hotdata!!.postValue(hotData.data.bannerInfo)
        }else{
        }
    }

    /**
     * 网络请求
     */
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson<HotData>(result, HotData::class.java)
    }

    var dataX : MutableLiveData<List<DataX>> = MutableLiveData(listOf())
    var filterCategory : MutableLiveData<List<FilterCategory>> = MutableLiveData(listOf())


    fun  newGoodsListData(order : String,sort :String,categoryId : Int){
        GlobalScope.launch {
            loadListData(order,sort, categoryId)
        }
    }

    suspend fun loadListData(order : String,sort :String,categoryId : Int){
        var newGoodsData = getList("https://cdwan.cn/api/goods/list?isNew=1&page=1&size=1000&order=$order&sort=$sort&categoryId=$categoryId")
        if(newGoodsData != null){
            dataX.postValue(newGoodsData.data.data)
            filterCategory.postValue(newGoodsData.data.filterCategory)
        }else{
        }
    }

    /**
     * 网络请求
     */
    suspend fun getList(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson<NewGoodsData>(result, NewGoodsData::class.java)
    }
}