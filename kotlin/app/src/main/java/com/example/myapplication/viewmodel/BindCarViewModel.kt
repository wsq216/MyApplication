package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.adapter.car.*
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class BindCarViewModel : BaseViewModel(Injection.repository) {

    var gallery : MutableLiveData<List<Gallery>> = MutableLiveData(listOf<Gallery>())
    var info : MutableLiveData<Info> = MutableLiveData()
    var issue : MutableLiveData<List<Issue>> = MutableLiveData(listOf<Issue>())
    var attribute : MutableLiveData<List<Attribute>> = MutableLiveData(listOf())
    var comment : MutableLiveData<Comment> = MutableLiveData()

    var goodsList : MutableLiveData<List<Goods>> = MutableLiveData(listOf())

    fun getDetails(id:String){
        viewModelScope.launch {
            var result = repository.getDetails(id)
            if (result!=null){
                gallery.postValue(result.data.gallery)
                info.postValue(result.data.info)
                issue.postValue(result.data.issue)
                attribute.postValue(result.data.attribute)
                comment.postValue(result.data.comment)
            }
        }
    }

    fun getRelated(id:String){
        viewModelScope.launch {
            var result = repository.getRelated(id)
            if (result!=null){
                goodsList.postValue(result.data.goodsList)
            }
        }
    }

}