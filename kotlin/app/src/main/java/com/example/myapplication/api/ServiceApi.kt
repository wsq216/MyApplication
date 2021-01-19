package com.shop.api


import com.example.myapplication.adapter.car.DetailsBase
import com.example.myapplication.adapter.car.RelatedBase
import com.example.myapplication.data.HomeData
import com.example.myapplication.data.brand.BrandData
import com.example.myapplication.data.login.LoginData
import com.example.myapplication.data.newgoods.NewGoodsData
import com.example.myapplication.data.shop.CarData
import com.example.myapplication.data.sort.CurrentData
import com.example.myapplication.data.sort.IndexData
import com.example.myapplication.model.zt.CategoryData
import com.example.myapplication.model.zt.CategroyListData
import com.shop.net.BaseResp
import io.reactivex.Flowable
import retrofit2.http.*
import java.util.*

interface ServiceApi {


    @POST("auth/refreshToken")  //刷新token
    suspend fun refreshToken():BaseResp<String>

    @GET("index")
    suspend fun getHome():BaseResp<HomeData>   // BaseResp抽取的一个bean类的外层结构 homeData当前接口返回的具体

    //https://cdwan.cn/api/brand/list?page=$page&size=$size
    @GET("brand/list?")
    suspend fun getBrand(@Query("page") page: String, @Query("size") size: String):BaseResp<BrandData>

    @GET("goods/hot")
    suspend fun getHot():BaseResp<com.example.myapplication.data.newgoods.HotData>
    //商品列表详情
    @GET("goods/list")
    suspend fun getGoodList(@QueryMap map: HashMap<String, String>):BaseResp<NewGoodsData>

    //分类竖着导航
    @GET("catalog/index?")
    suspend fun getIndex(@Query("id") id: String):BaseResp<IndexData>
    //分类右边对应的当前分类的数据
    @GET("catalog/current?")
    suspend fun getCurrent(@Query("id") id: String):BaseResp<CurrentData>

    //频道商品分类的顶部导航数据接口
    @GET("goods/category")
    suspend fun getCategoryTab(@Query("id") id: String):BaseResp<CategoryData>

    //频道商品获取当前分类的列表数据
    @GET("goods/list")
    suspend fun getCategoryList(@Query("categoryId") id: String) :BaseResp<CategroyListData>


    //https://cdplay.cn/api/goods/detail?id=1009024
    //商品购买详情页
    @GET("goods/detail?")
    suspend fun getDetails(@Query("id") id: String): BaseResp<DetailsBase>

    //https://cdplay.cn/api/goods/related?id=1009024
    //商品购买详情页底部商品列表数据
    @GET("goods/related?")
    suspend fun getRelated(@Query("id") id: String): BaseResp<RelatedBase>


    //登录
    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username:String,@Field("password") password:String):BaseResp<LoginData>

    //获取购物车数据
    @GET("cart/index")
    suspend fun getCar():BaseResp<CarData>
//    //添加到购物车
//    @POST("api/cart/add")
//    @FormUrlEncoded
//    suspend fun addCar(@FieldMap map: Map<String?, String?>): Flowable<AddCarBean>


//    //http://cdwan.cn:7000/tongpao/discover/hot.json
//    @GET("tongpao/discover/hot.json")
//    suspend fun getHot():BaseResp<HotData>

//    //专题
//    @GET("topic/list?page=1&size=10")
//    suspend fun getTopic():BaseResp<TopicData>


}