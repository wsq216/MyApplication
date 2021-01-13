package com.example.myapplication.viewmodel.tongpao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.brand.BrandData
import com.example.myapplication.data.hot.Data
import com.example.myapplication.data.hot.Discover
import com.example.myapplication.data.hot.HotData
import com.google.gson.Gson
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class BindHotViewModel : BaseViewModel(Injection.repository) {
    var discover: MutableLiveData<List<Discover>> = MutableLiveData(listOf())

    fun getTp() {
        GlobalScope.launch {
           Load()
        }
    }

    suspend fun Load() {
        var result = getHot("http://cdwan.cn:7000/tongpao/discover/hot.json")
        if (result != null) {
            discover.postValue(result.data.list)
        } else if (result == null) {

        }
    }

    suspend fun getHot(uri : String) = withContext(Dispatchers.IO) {
        var result = URL(uri).readText(charset("utf-8"))
        return@withContext Gson().fromJson<HotData>(result, HotData::class.java)

    }
}