package com.live.presenter;




import com.example.mvp.base.BasePresenter;
import com.example.mvp.interfaces.Callback;
import com.live.bean.CreateRoomBean;
import com.live.bean.EditorRoomBean;
import com.live.bean.MyroomBean;
import com.live.bean.RoomListBean;
import com.live.bean.RoomLiveUrlBean;
import com.live.bean.StartLiveBean;
import com.live.interfaces.IRoom;
import com.live.model.RoomModel;

import java.util.HashMap;

public class RoomPresenter extends BasePresenter<IRoom.View> implements IRoom.Presenter{

    IRoom.View view;
    IRoom.Model model;

    public RoomPresenter(IRoom.View view) {
        this.view = view;
        this.model=new RoomModel();
    }


    @Override
    public void createRoom(HashMap<String, String> map) {
        if(view!=null){
            this.model.createRoom( map,new Callback() {
                @Override
                public void success(Object data) {
                    view.createRoom( (CreateRoomBean) data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }


    @Override
    public void startLive(HashMap<String, String> map) {
        if(view!=null){
            this.model.startLive( map,new Callback<StartLiveBean>() {
                @Override
                public void success(StartLiveBean data) {
                    view.startLive( data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }

    @Override
    public void getRoomList() {
        if(view!=null){
            this.model.getRoomList( new Callback<RoomListBean>() {
                @Override
                public void success(RoomListBean data) {
                    view.getRoomList( data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }

    @Override
    public void myroom() {
        if(view!=null){
            this.model.myroom( new Callback<MyroomBean>() {
                @Override
                public void success(MyroomBean data) {
                    view.myroom( data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }

    @Override
    public void roomLiveUrl(int roomid) {
        if(view!=null){
            this.model.roomLiveUrl( roomid,new Callback<RoomLiveUrlBean>() {
                @Override
                public void success(RoomLiveUrlBean data) {
                    view.roomLiveUrl( data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }

    @Override
    public void editorRoom(HashMap<String, String> map) {
        if(view!=null){
            this.model.editorRoom( map,new Callback<EditorRoomBean>() {
                @Override
                public void success(EditorRoomBean data) {
                    view.editorRoom( data );
                }

                @Override
                public void fail(String err) {
                    view.showToast( err );
                }
            } );
        }
    }
}
