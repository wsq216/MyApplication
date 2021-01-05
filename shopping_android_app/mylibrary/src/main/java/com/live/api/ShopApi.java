package com.live.api;

import com.live.bean.CreateRoomBean;
import com.live.bean.EditorRoomBean;
import com.live.bean.MyroomBean;
import com.live.bean.RoomListBean;
import com.live.bean.RoomLiveUrlBean;
import com.live.bean.StartLiveBean;

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


    //创建房间
    //https://cdplay.cn/api/live/createRoom
    @POST("api/live/createRoom")
    @FormUrlEncoded
    Flowable<CreateRoomBean> createRoom(@FieldMap Map<String,String> map);

    //开启直播
    @POST("api/live/startLive")
    @FormUrlEncoded
    Flowable<StartLiveBean> startLive(@FieldMap Map<String,String> map);

    //获取房间列表
    @GET("api/live/getRoomList")
    Flowable<RoomListBean> getRoomList();

    //获取自己房间信息
    @POST("api/live/myroom")
    Flowable<MyroomBean> myroom();

    //房间播放地址获取
    @POST("api/live/roomLiveUrl")
    @FormUrlEncoded
    Flowable<RoomLiveUrlBean> roomLiveUrl(@Field("roomid") int roomid );

    //房间播放地址获取
    //https://cdplay.cn/api/live/editorRoom
    @POST("api/live/editorRoom")
    @FormUrlEncoded
    Flowable<EditorRoomBean> editorRoom(@FieldMap Map<String,String> map );


}
