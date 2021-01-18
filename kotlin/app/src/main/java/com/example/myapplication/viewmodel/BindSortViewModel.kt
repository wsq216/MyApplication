package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.sort.CurrentData
import com.example.myapplication.data.sort.IndexData
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class BindSortViewModel : BaseViewModel(Injection.repository) {
    var sortData: MutableLiveData<IndexData> = MutableLiveData()  //竖导航数据

    var sortDataList: MutableLiveData<CurrentData> = MutableLiveData()


    lateinit var bannerUrl:String //当前竖导航选中的tab所对应的banner数据
    lateinit var desc:String
    lateinit var name:String


    /**
     * 刷新当前的tab所对应的数据
     */
    fun updateCurrentTab(data: IndexData.Category){
        bannerUrl = data.wap_banner_url
        desc = data.front_desc
        name=data.name
    }

    /**
     * 获取竖导航列表
     */
    fun getSortData(id:String){
        viewModelScope.launch {
            var result = repository.getIndex(id)
            if(result.errno == 0){
                sortData.postValue(result.data)
            }
        }
    }

    /**
     * 竖导航列表数据
     */
    fun getSortListData(id:String){
        viewModelScope.launch {
            var result = repository.getCurrent(id)
            if(result.errno == 0){
                sortDataList.postValue(result.data)
            }
        }
    }
}