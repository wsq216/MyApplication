package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.HotGoods
import com.example.myapplication.data.newgoods.*
import com.google.gson.Gson
import com.shop.base.BaseViewModel
import com.shop.net.Injection

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class BindNewGoodsViewModel : BaseViewModel(Injection.repository) {

    var hotdata: MutableLiveData<BannerInfo> = MutableLiveData()

    fun getHot() {
        viewModelScope.launch {
            var result = repository.getHot()
            if (result != null) {
                hotdata.postValue(result.data.bannerInfo)
            } else {
                refreshToken()
            }
        }
    }


//    fun  newGoodsData(){
//        GlobalScope.launch {
//            loadData()
//        }
//    }
//
//    suspend fun loadData(){
//        var hotData = get("https://cdwan.cn/api/goods/hot")
//        if(hotData != null){
//            hotdata!!.postValue(hotData.data.bannerInfo)
//        }else{
//        }
//    }
//
//    /**
//     * 网络请求
//     */
//    suspend fun get(str:String) = withContext(Dispatchers.IO){
//        var result = URL(str).readText(charset("utf-8"))
//        return@withContext Gson().fromJson<HotData>(result, HotData::class.java)
//    }

    var dataX: MutableLiveData<List<DataX>> = MutableLiveData(listOf())
    var filterCategory: MutableLiveData<List<FilterCategory>> = MutableLiveData(listOf())

    fun getGoodList(map: HashMap<String, String>) {
        viewModelScope.launch {
            var result = repository.getGoodList(map)
            if (result != null) {
                dataX.postValue(result.data.data)
                filterCategory.postValue(result.data.filterCategory)
            } else {
                refreshToken()
            }
        }
    }

//    fun  newGoodsListData(order : String,sort :String,categoryId : Int){
//        GlobalScope.launch {
//            loadListData(order,sort, categoryId)
//        }
//    }
//
//    suspend fun loadListData(order : String,sort :String,categoryId : Int){
//        var newGoodsData = getList("https://cdwan.cn/api/goods/list?isNew=1&page=1&size=1000&order=$order&sort=$sort&categoryId=$categoryId")
//        if(newGoodsData != null){
//            dataX.postValue(newGoodsData.data.data)
////            filterCategory.postValue(newGoodsData.data.filterCategory)
//        }else{
//        }
//    }
//
//    /**
//     * 网络请求
//     */
//    suspend fun getList(str:String) = withContext(Dispatchers.IO){
//        var result = URL(str).readText(charset("utf-8"))
//        return@withContext Gson().fromJson<NewGoodsData>(result, NewGoodsData::class.java)
//    }
}