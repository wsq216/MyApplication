package com.live.model;



import com.example.mvp.base.BaseModel;
import com.example.mvp.interfaces.Callback;
import com.example.mvp.net.CommonSubscriber;
import com.example.mvp.utils.RxUtils;
import com.live.bean.CreateRoomBean;
import com.live.bean.EditorRoomBean;
import com.live.bean.MyroomBean;
import com.live.bean.RoomListBean;
import com.live.bean.RoomLiveUrlBean;
import com.live.bean.StartLiveBean;
import com.live.interfaces.IRoom;
import com.live.net.HttpmManager;

import java.util.HashMap;

public class RoomModel extends BaseModel implements IRoom.Model {

    @Override
    public void createRoom(HashMap<String, String> map, Callback callback) {
        addDisposible( HttpmManager.getHttpmManager().getShopApi().createRoom(map)
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<CreateRoomBean>( callback ) {
                    @Override
                    public void onNext(CreateRoomBean createRoomBean) {
                        callback.success( createRoomBean );
                    }
                } ));
    }

    @Override
    public void startLive(HashMap<String, String> map, Callback callback) {
        addDisposible( HttpmManager.getHttpmManager().getShopApi()
                .startLive(map)
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<StartLiveBean>( callback ) {
                    @Override
                    public void onNext(StartLiveBean createRoomBean) {
                        callback.success( createRoomBean );
                    }
                } ));
    }

    @Override
    public void getRoomList(Callback callback) {
        addDisposible( HttpmManager.getHttpmManager().getShopApi()
                .getRoomList()
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<RoomListBean>( callback ) {
                    @Override
                    public void onNext(RoomListBean createRoomBean) {
                        callback.success( createRoomBean );
                    }
                } ));
    }

    @Override
    public void myroom(Callback callback) {
        addDisposible( HttpmManager.getHttpmManager().getShopApi()
                .myroom()
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<MyroomBean>( callback ) {
                    @Override
                    public void onNext(MyroomBean createRoomBean) {
                        callback.success( createRoomBean );
                    }
                } ));
    }

    @Override
    public void roomLiveUrl(HashMap<String,String> map, Callback callback) {
        addDisposible( HttpmManager.getHttpmManager().getShopApi()
                .roomLiveUrl(map)
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<RoomLiveUrlBean>( callback ) {
                    @Override
                    public void onNext(RoomLiveUrlBean createRoomBean) {
                        callback.success( createRoomBean );
                    }
                } ));
    }

    @Override
    public void editorRoom(HashMap<String, String> map, Callback callback) {
        addDisposible( HttpmManager.getHttpmManager().getShopApi()
                .editorRoom(map)
                .compose( RxUtils.rxScheduler() )
                .subscribeWith( new CommonSubscriber<EditorRoomBean>( callback ) {
                    @Override
                    public void onNext(EditorRoomBean createRoomBean) {
                        callback.success( createRoomBean );
                    }
                } ));
    }
}
