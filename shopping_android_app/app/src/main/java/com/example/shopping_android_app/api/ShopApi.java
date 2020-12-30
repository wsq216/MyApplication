package com.example.shopping_android_app.api;


import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.model.home.BrandDatailBase;
import com.example.shopping_android_app.model.home.BrandIdBase;
import com.example.shopping_android_app.model.home.CategoryBean;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.model.home.HotBase;
import com.example.shopping_android_app.model.home.HotGoodListBean;
import com.example.shopping_android_app.model.home.address.AddressBase;
import com.example.shopping_android_app.model.home.address.AddressListBase;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;
import com.example.shopping_android_app.model.home.details.DetailsBase;
import com.example.shopping_android_app.model.home.details.RelatedBase;
import com.example.shopping_android_app.model.home.login.LoginBean;
import com.example.shopping_android_app.model.home.login.RegisterBean;
import com.example.shopping_android_app.model.home.me.UserInfoBean;
import com.example.shopping_android_app.model.home.shop.AddCarBean;
import com.example.shopping_android_app.model.home.shop.CarBean;
import com.example.shopping_android_app.model.home.shop.DeleteCarBean;
import com.example.shopping_android_app.model.home.shop.UpdateCarBean;
import com.example.shopping_android_app.model.home.topic.TopicBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopApi {

    String BASE_URL = "https://cdplay.cn/";

    //首页
    @GET("api/index")
    Flowable<HomeBean> getHome();

    //分页导航
    //http://cdplay.cn/goods/category?id=1005007
    @GET("goods/category?")
    Flowable<CategoryBean> getCategory(@Query("id") String url);

    //商品分类的顶部导航数据接口
    //&page=1&size=100
    @GET("api/goods/list?")
    Flowable<CategoryListBean> getCategoryList(@Query("categoryId") String url, @Query("page") int page, @Query("size") int size);

    //商品详情列表数据
    //    https://cdplay.cn/api/brand/list?page=1&size=1000
    @GET("api/brand/list?")
    Flowable<BrandBase> getBrand(@Query("page") int page, @Query("size") int size);

    //制造商详情页面数据接口
    //    https://cdplay.cn/api/brand/detail?id=1001000
    @GET("api/brand/detail?")
    Flowable<BrandDatailBase> getBrandDetail(@Query("id") int id);

    //制造商详情页商品列表的数据接口
    //    https://cdplay.cn/api/goods/list?brandId=1001002&page=1&size=1000
    @GET("api/goods/list?")
    Flowable<BrandIdBase> getBrandId(@Query("brandId") int brandId, @Query("page") int page, @Query("size") int size);

    //新品页面商品顶部热门商品信息
    //https://cdplay.cn/api/goods/hot
    @GET("api/goods/hot")
    Flowable<HotBase> getHot();

    //新品页面商品列表数据
    //https://cdplay.cn/api/goods/list?isNew=1&page=1&size =1000&order=asc&sort=default&categoryId=0
    @GET("api/goods/list?")
    Flowable<HotGoodListBean> getHotGoodList(@QueryMap HashMap<String, String> map);

    //https://cdplay.cn/api/topic/list
    //专题列表数据
    @GET("api/topic/list")
    Flowable<TopicBean> getTopicList();

    //https://cdplay.cn/api/goods/detail?id=1009024
    //商品购买详情页
    @GET("api/goods/detail?")
    Flowable<DetailsBase> getDetails(@Query("id") int id);

    //https://cdplay.cn/api/goods/related?id=1009024
    //商品购买详情页底部商品列表数据
    @GET("api/goods/related?")
    Flowable<RelatedBase> getRelated(@Query("id") int id);


    //https://cdplay.cn/api/catalog/index
    //竖导航数据
    @GET("api/catalog/index")
    Flowable<CatalogBase> getCatalog();

    //https://cdplay.cn/api/catalog/current?id=1005001
    @GET("api/catalog/current?")
    Flowable<CatalogListBase> getCatalogList(@Query("id") int id);

    //登录
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String pw);

    //注册
    //https://cdplay.cn/api/auth/register
    @POST("api/auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> getreister(@Field("username") String username, @Field("password") String pw);




    //购物车列表
    @GET("api/cart/index")
    Flowable<CarBean> getCarList();


    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap Map<String,String> map);

    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);

    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);

    //收货地址列表
    //https://cdplay.cn/api/address/list
    @GET("api/address/list")
    Flowable<AddressListBase> getAddressList();

    //收货地址列表
    //https://cdplay.cn/api/address/detail?id=4
    @GET("api/address/detail?")
    Flowable<AddressBase> getAddress(@Query("id") int id);

    //添加地址
    //https://cdplay.cn/api/address/save
    @POST("api/address/save")
    @FormUrlEncoded
    Flowable<AddressListBase> addAddress(@FieldMap Map<String,String> map);

    //删除地址
    //https://cdplay.cn/api/address/delete
    @POST("api/address/delete")
    @FormUrlEncoded
    Flowable<AddressListBase> deleteAddress(@Field("id") int id);

    //用户信息更新
    @POST("api/user/updateUserInfo")
    @FormUrlEncoded
    Flowable<UserInfoBean> updateUserInfo(@FieldMap Map<String,String> map);

}
