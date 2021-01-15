package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.brand.BrandData
import com.example.myapplication.data.brand.DataX
import com.google.gson.Gson
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class BindBrandViewModel : BaseViewModel(Injection.repository) {
    var data : MutableLiveData<List<DataX>> = MutableLiveData(listOf())


     fun getBrand(page: String,size: String){
        viewModelScope.launch {
            var result =  repository.getBrand(page, size)
            if (result.errno == 0){
                data.postValue(result.data.data)
            }else{
                refreshToken()
            }
        }
    }

//    fun brandData(page: String,size: String){
//        GlobalScope.launch {
//            loadData(page,size)
//        }
//    }
//
//    suspend fun loadData(page : String,size : String) {
//        var brandData = get("https://cdwan.cn/api/brand/list?page=$page&size=$size")
//        if (brandData!=null){
//            data.postValue(brandData.data.data)
//        }
//    }
//
//    suspend fun get(str:String) = withContext(Dispatchers.IO){
//        var result = URL(str).readText(charset("utf-8"))
//        return@withContext Gson().fromJson<BrandData>(result,BrandData::class.java)
//    }

}