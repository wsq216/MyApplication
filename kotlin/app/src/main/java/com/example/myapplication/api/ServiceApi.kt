package com.shop.api


import com.example.myapplication.data.HomeData
import com.example.myapplication.data.hot.HotData
import com.shop.net.BaseResp
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApi {


    @POST("auth/refreshToken")  //刷新token
    suspend fun refreshToken():BaseResp<String>

    @GET("index")
    suspend fun getHome():BaseResp<HomeData>   // BaseResp抽取的一个bean类的外层结构 homeData当前接口返回的具体


//    //http://cdwan.cn:7000/tongpao/discover/hot.json
//    @GET("tongpao/discover/hot.json")
//    suspend fun getHot():BaseResp<HotData>

//    //专题
//    @GET("topic/list?page=1&size=10")
//    suspend fun getTopic():BaseResp<TopicData>


}