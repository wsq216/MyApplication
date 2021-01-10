package com.live;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.mvp.base.BaseActivity;
import com.live.bean.CreateRoomBean;
import com.live.bean.EditorRoomBean;
import com.live.bean.MyroomBean;
import com.live.bean.RoomListBean;
import com.live.bean.RoomLiveUrlBean;
import com.live.bean.StartLiveBean;
import com.live.interfaces.IRoom;
import com.live.presenter.RoomPresenter;

import java.util.HashMap;
import java.util.Map;

public class UpInfoActivity extends BaseActivity<RoomPresenter> implements IRoom.View, View.OnClickListener {

    private ImageView mIvUpdateLiveRoomReturn;
    /**
     * XX的直播间
     */
    private TextView mTvUpdateLiveRoomName;
    private ConstraintLayout mCreateRoomCon;
    /**
     * 修改的房间名
     */
    private EditText mEtUpdateLiveRoomNewname;
    /**
     * 修改的房间id
     */
    private EditText mEtUpdateLiveRoomRoomid;
    /**
     * 修改的房间背景图地址
     */
    private EditText mEtUpdateLiveRoomIcon;
    private ImageView mIvUpdateLiveRoomImg;
    /**
     * 修改的房间直播标题
     */
    private EditText mEtUpdateLiveRoomTitle;
    /**
     * 确定修改
     */
    private Button mBtnUpdateLiveRoomOk;
    private LinearLayout mMLlUpdateLiveRoom;

    @Override
    protected int getLayout() {
        return R.layout.activity_up_info;
    }

    @Override
    protected RoomPresenter createPrenter() {
        return new RoomPresenter( this );
    }

    protected void initView() {
        mIvUpdateLiveRoomReturn = (ImageView) findViewById( R.id.iv_update_live_room_return );
        mTvUpdateLiveRoomName = (TextView) findViewById( R.id.tv_update_live_room_name );
        mCreateRoomCon = (ConstraintLayout) findViewById( R.id.create_room_con );
        mEtUpdateLiveRoomNewname = (EditText) findViewById( R.id.et_update_live_room_newname );
        mEtUpdateLiveRoomRoomid = (EditText) findViewById( R.id.et_update_live_room_roomid );
        mEtUpdateLiveRoomIcon = (EditText) findViewById( R.id.et_update_live_room_icon );
        mIvUpdateLiveRoomImg = (ImageView) findViewById( R.id.iv_update_live_room_img );
        mEtUpdateLiveRoomTitle = (EditText) findViewById( R.id.et_update_live_room_title );
        mBtnUpdateLiveRoomOk = (Button) findViewById( R.id.btn_update_live_room_ok );
        mBtnUpdateLiveRoomOk.setOnClickListener( this );
        mMLlUpdateLiveRoom = (LinearLayout) findViewById( R.id.mLl_update_live_room );

        //接受值
        int createId = getIntent().getIntExtra( "createId", 0 );

        mEtUpdateLiveRoomRoomid.setText( createId + "" );


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.btn_update_live_room_ok) {
            String name = mEtUpdateLiveRoomNewname.getText().toString();
            String roomid = mEtUpdateLiveRoomRoomid.getText().toString();
            String title = mEtUpdateLiveRoomTitle.getText().toString();

            HashMap<String, String> map = new HashMap<>();
            map.put( "name", name );
            map.put( "roomid", roomid );
            map.put( "title", title );

            presenter.editorRoom( map );

            mTvUpdateLiveRoomName.setText( name + "的直播间" );


        }
    }


    @Override
    public void createRoom(CreateRoomBean createRoomBean) {

    }

    @Override
    public void startLive(StartLiveBean startLiveBean) {

    }

    @Override
    public void getRoomList(RoomListBean roomListBean) {

    }

    @Override
    public void myroom(MyroomBean myroomBean) {

    }

    @Override
    public void roomLiveUrl(RoomLiveUrlBean roomLiveUrlBean) {

    }

    @Override
    public void editorRoom(EditorRoomBean editorRoomBean) {
        int errno = editorRoomBean.getErrno();
        if (errno == 0) {
            Toast.makeText( this, "修改成功", Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText( this, editorRoomBean.getErrmsg(), Toast.LENGTH_SHORT ).show();
        }
    }
}