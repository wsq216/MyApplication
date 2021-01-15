package com.shop.api


import com.example.myapplication.data.HomeData
import com.example.myapplication.data.brand.BrandData
import com.example.myapplication.data.hot.HotData
import com.example.myapplication.data.newgoods.NewGoodsData
import com.shop.net.BaseResp
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.HashMap

interface ServiceApi {


    @POST("auth/refreshToken")  //刷新token
    suspend fun refreshToken():BaseResp<String>

    @GET("index")
    suspend fun getHome():BaseResp<HomeData>   // BaseResp抽取的一个bean类的外层结构 homeData当前接口返回的具体

    //https://cdwan.cn/api/brand/list?page=$page&size=$size
    @GET("brand/list?")
    suspend fun getBrand(@Query("page") page:String,@Query("size") size : String):BaseResp<BrandData>

    @GET("goods/hot")
    suspend fun getHot():BaseResp<com.example.myapplication.data.newgoods.HotData>
    //商品列表详情
    @GET("goods/list")
    suspend fun getGoodList(@QueryMap map: HashMap<String, String>):BaseResp<NewGoodsData>
//    //http://cdwan.cn:7000/tongpao/discover/hot.json
//    @GET("tongpao/discover/hot.json")
//    suspend fun getHot():BaseResp<HotData>

//    //专题
//    @GET("topic/list?page=1&size=10")
//    suspend fun getTopic():BaseResp<TopicData>


}