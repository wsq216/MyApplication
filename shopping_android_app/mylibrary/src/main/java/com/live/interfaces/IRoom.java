package com.live.interfaces;




import com.example.mvp.interfaces.Callback;
import com.example.mvp.interfaces.IBaseModel;
import com.example.mvp.interfaces.IBasePresenter;
import com.example.mvp.interfaces.IBaseView;
import com.live.bean.CreateRoomBean;
import com.live.bean.EditorRoomBean;
import com.live.bean.MyroomBean;
import com.live.bean.RoomListBean;
import com.live.bean.RoomLiveUrlBean;
import com.live.bean.StartLiveBean;

import java.util.HashMap;

public interface IRoom {
    interface View extends IBaseView {
        void createRoom(CreateRoomBean createRoomBean);
        void startLive(StartLiveBean startLiveBean);
        void getRoomList(RoomListBean roomListBean);
        void myroom(MyroomBean myroomBean);
        void roomLiveUrl(RoomLiveUrlBean roomLiveUrlBean);
        void editorRoom(EditorRoomBean editorRoomBean);

    }

    interface Presenter extends IBasePresenter<View> {
        void createRoom(HashMap<String, String> map);
        void startLive(HashMap<String, String> map);
        void getRoomList();
        void myroom();
        void roomLiveUrl(int roomid);
        void editorRoom(HashMap<String, String> map);

    }


    interface Model extends IBaseModel {
        void createRoom(HashMap<String, String> map, Callback callback);
        void startLive(HashMap<String, String> map, Callback callback);
        void getRoomList( Callback callback);
        void myroom( Callback callback);
        void roomLiveUrl(int roomid, Callback callback);
        void editorRoom(HashMap<String, String> map, Callback callback);

    }
}
