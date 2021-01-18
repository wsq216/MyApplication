package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.zt.BrotherCategory
import com.example.myapplication.model.zt.CurrentCategory
import com.example.myapplication.model.zt.DataX
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class BindCateGoryViewModel:BaseViewModel(Injection.repository) {
    var dataX : MutableLiveData<List<DataX>> = MutableLiveData(listOf<DataX>())
    var currentCategory : MutableLiveData<CurrentCategory> = MutableLiveData()
    var brotherCategory : MutableLiveData<List<BrotherCategory>> = MutableLiveData(listOf<BrotherCategory>())



    fun getCategoryTab(id : String){
        viewModelScope.launch {
            var result = repository.getCategoryTab(id)
            if (result!=null){
                brotherCategory.postValue(result.data.brotherCategory)
                currentCategory.postValue(result.data.currentCategory)
            }else{
                refreshToken()
            }
        }
    }
    fun getCategoryList(id : String){
        viewModelScope.launch {
            var result = repository.getCategoryList(id)
            if (result!=null){
                dataX.postValue(result.data.data)
            }else{
                refreshToken()
            }
        }
    }
}