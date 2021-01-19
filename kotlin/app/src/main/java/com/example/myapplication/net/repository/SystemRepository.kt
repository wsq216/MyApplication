package com.shop.net.repository

import com.example.myapplication.net.RetrofitFactory
import com.shop.api.ServiceApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 数据仓库
 * 用来连接ViewModel和Model
 * 定义业务相关的网络请求接口的api
 */
class SystemRepository {

    private lateinit var serviceApi: ServiceApi

    //初始化的方法
    init {
        serviceApi = RetrofitFactory.instance.create(ServiceApi::class.java)

    }

    /**
     * 刷新token
     */
    suspend fun refreshToken() = withContext(Dispatchers.IO){
        serviceApi.refreshToken()
    }


    /**
     * 获取主页数据
     */
    suspend fun getHome() = withContext(Dispatchers.IO){
        serviceApi.getHome()
    }

    /**
     * 获取主页数据
     */
    suspend fun getBrand(page : String,size:String ) = withContext(Dispatchers.IO){
        serviceApi.getBrand(page,size)
    }


    suspend fun getHot() = withContext(Dispatchers.IO){
        serviceApi.getHot()
    }

    suspend fun getGoodList(map: HashMap<String,String>) = withContext(Dispatchers.IO){
        serviceApi.getGoodList(map)
    }

    suspend fun getIndex(id:String) = withContext(Dispatchers.IO){
        serviceApi.getIndex(id)
    }

    suspend fun getCurrent(id: String) = withContext(Dispatchers.IO){
        serviceApi.getCurrent(id)
    }

    suspend fun getCategoryTab(id: String) = withContext(Dispatchers.IO){
        serviceApi.getCategoryTab(id)
    }

    suspend fun getCategoryList(id: String) = withContext(Dispatchers.IO){
        serviceApi.getCategoryList(id)
    }

    suspend fun getDetails(id: String) = withContext(Dispatchers.IO){
        serviceApi.getDetails(id)
    }

    suspend fun getRelated(id: String) = withContext(Dispatchers.IO){
        serviceApi.getRelated(id)
    }


    /**
     * 登录接口
     */
    suspend fun login(username:String,password:String) = withContext(Dispatchers.IO){
        serviceApi.login(username,password)
    }

    /**
     * 购物车数据获取
     */
    suspend fun getCar() = withContext(Dispatchers.IO){
        serviceApi.getCar()
    }



//    /**
//     * 获取专题数据
//     */
//    suspend fun getTopic() = withContext(Dispatchers.IO){
//        serviceApi.getTopic()
//    }

}