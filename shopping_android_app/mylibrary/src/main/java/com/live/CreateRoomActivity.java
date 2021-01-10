package com.live;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.mvp.base.BaseActivity;
import com.example.mvp.utils.SpUtils;
import com.live.bean.CreateRoomBean;
import com.live.bean.EditorRoomBean;
import com.live.bean.MyroomBean;
import com.live.bean.RoomListBean;
import com.live.bean.RoomLiveUrlBean;
import com.live.bean.StartLiveBean;
import com.live.interfaces.IRoom;
import com.live.presenter.RoomPresenter;

import java.util.HashMap;

public class CreateRoomActivity extends BaseActivity<RoomPresenter> implements IRoom.View {

    private ImageView mImgBack;
    private TextView mTvRoom;
    /**
     * 成功/失败
     */
    private TextView mTvSucceed;
    /**
     * 创建的房间名
     */
    private EditText mCreateRoomName;
    /**
     * 创建的房间列表默认的背景图
     */
    private EditText mCreateRoomIcon;
    /**
     * 公开
     */
    private RadioButton mCreatePublicity;
    /**
     * 私密
     */
    private RadioButton mCreatePrivacy;
    private RadioGroup mCreateRg;
    /**
     * 输入房间的密码
     */
    private EditText mCreatePsd;
    private LinearLayout mCreateLl;
    /**
     * 创建房间
     */
    private Button mCreateBtnOk;
    private ConstraintLayout mCreateCon;
    private int isopen=1;
    private String pwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_create_room;
    }

    @Override
    protected RoomPresenter createPrenter() {
        return new RoomPresenter( this );
    }

    protected void initView() {
        mImgBack = (ImageView) findViewById( R.id.img_back );
        mTvRoom = (TextView) findViewById( R.id.tv_room );
        mCreateRoomName = (EditText) findViewById( R.id.create_room_name );
        mCreateRoomIcon = (EditText) findViewById( R.id.create_room_icon );
        mCreatePublicity = (RadioButton) findViewById( R.id.create_publicity );
        mCreatePrivacy = (RadioButton) findViewById( R.id.create_privacy );
        mCreateRg = (RadioGroup) findViewById( R.id.create_rg );
        mCreatePsd = (EditText) findViewById( R.id.create_psd );
        mCreateLl = (LinearLayout) findViewById( R.id.create_ll );
        mCreateBtnOk = (Button) findViewById( R.id.create_btn_ok );
        mCreateCon = (ConstraintLayout) findViewById( R.id.create_con );


        mCreateBtnOk.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initClick();
            }
        } );

        mCreateRg.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.create_publicity) {
                    isopen=1;
                    mCreatePsd.setVisibility(View.GONE);

                }else  if (i == R.id.create_privacy) {
                    isopen=2;
                    mCreatePsd.setVisibility(View.VISIBLE);

                }
            }
        } );

        mImgBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

    }

    @Override
    protected void initData() {

    }

    private void initClick() {
        String room_name = mCreateRoomName.getText().toString();
        String room_icon = mCreateRoomIcon.getText().toString();
        pwd = mCreatePsd.getText().toString();

        HashMap<String, String> map = new HashMap<>();



        if (!TextUtils.isEmpty(room_name) && !TextUtils.isEmpty(room_icon) && isopen > 0) {
            if (!TextUtils.isEmpty(pwd)) {
                map.put("password", pwd);
            }
            map.put("room_name", room_name);
            map.put("room_icon", room_icon);
            map.put("isopen", String.valueOf(isopen));
            Log.e( "isopen", "isopen: " +isopen);
            presenter.createRoom(map);
        } else {
            Toast.makeText(this, "房间id、房间背景图、选择房间公开/私密不可以为空", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void createRoom(CreateRoomBean createRoomBean) {
        if (createRoomBean.getErrno() == 0) {
            CreateRoomBean.DataBean data = createRoomBean.getData();
            mTvRoom.setVisibility(View.VISIBLE);
            mTvRoom.setText(data.getName());
            SpUtils.getInstance().setValue("isopen",isopen);
            SpUtils.getInstance().setValue("password",pwd);
            Toast.makeText( this, "创建房间成功！", Toast.LENGTH_SHORT ).show();
            finish();
            startActivity(new Intent(CreateRoomActivity.this,RoomActivity.class));
        } else {
            mTvSucceed.setVisibility(View.VISIBLE);
            Toast.makeText(this, createRoomBean.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
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

    }


}