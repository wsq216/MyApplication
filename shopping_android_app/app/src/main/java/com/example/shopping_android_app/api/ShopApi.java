package com.example.shopping_android_app.api;


import com.example.shopping_android_app.model.home.BrandBase;
import com.example.shopping_android_app.model.home.BrandDatailBase;
import com.example.shopping_android_app.model.home.BrandIdBase;
import com.example.shopping_android_app.model.home.CategoryBean;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.model.home.HotBase;
import com.example.shopping_android_app.model.home.HotGoodListBean;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;
import com.example.shopping_android_app.model.home.details.DetailsBase;
import com.example.shopping_android_app.model.home.details.RelatedBase;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

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
}
