package com.live;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.BaseAdapter;
import com.example.mvp.utils.SpUtils;
import com.live.adapter.MyAdapter;
import com.live.bean.CreateRoomBean;
import com.live.bean.EditorRoomBean;
import com.live.bean.MyroomBean;
import com.live.bean.RoomListBean;
import com.live.bean.RoomLiveUrlBean;
import com.live.bean.StartLiveBean;
import com.live.interfaces.IRoom;
import com.live.presenter.RoomPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomActivity extends BaseActivity<RoomPresenter> implements IRoom.View {

    RecyclerView recyRoom;
    ImageView imgBack;
    ImageView imgStartLive;
    List<RoomListBean.DataBean> roomListBeans;
    MyAdapter myAdapter;

    private ImageView mUpInfoImg;
    private ImageView mCreateRoomImg;
    private int errno;
    private int myId;
    private String password = SpUtils.getInstance().getString("password");
    private int isopen = SpUtils.getInstance().getInt("isopen");

    @Override
    protected int getLayout() {
        return R.layout.activity_room;
    }

    @Override
    protected RoomPresenter createPrenter() {
        return new RoomPresenter(this);
    }

    protected void initView() {
        recyRoom = findViewById(R.id.recy_room);
        imgBack = findViewById(R.id.img_back);
        imgStartLive = findViewById(R.id.img_startLive);
        mUpInfoImg = (ImageView) findViewById(R.id.img_upInfo);
        mCreateRoomImg = (ImageView) findViewById(R.id.img_createRoom);

        Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        roomListBeans = new ArrayList<>();
        myAdapter = new MyAdapter(this, roomListBeans);
        recyRoom.setLayoutManager(new GridLayoutManager(this, 2));
        recyRoom.setAdapter(myAdapter);

        myAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int isopen = roomListBeans.get(pos).getIsopen();
                Toast.makeText(RoomActivity.this, ""+isopen, Toast.LENGTH_SHORT).show();
                int id = roomListBeans.get(pos).getId();
                if (isopen == 2) {
                    View inflate = LayoutInflater.from(RoomActivity.this).inflate(R.layout.pw_mm, null);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RoomActivity.this);
                    EditText et_mm = (EditText) inflate.findViewById(R.id.et_mm);
                    builder1.setView(inflate);
                    //确定按钮监听
                    builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String s = et_mm.getText().toString();
                            if (!TextUtils.isEmpty(s)) {
                                if (s.equals(password)) {
                                    //进行观看
                                    Intent intent = new Intent(RoomActivity.this, LiveActivity.class);
                                    intent.putExtra("id", id);
                                    intent.putExtra("isopen", isopen);
                                    intent.putExtra("password", password);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(RoomActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RoomActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    //取消按钮监听
                    builder1.setNegativeButton("取消", null);
                    //展示出AlertDialog
                    builder1.show();
                }else{
                    //进行观看
                    Intent intent = new Intent(RoomActivity.this, LiveActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("isopen", isopen);
                    startActivity(intent);
                }
            }
        });

        //监听
        OnClickListener onClickListener = new OnClickListener();
        imgStartLive.setOnClickListener(onClickListener);
        imgBack.setOnClickListener(onClickListener);
        mUpInfoImg.setOnClickListener(onClickListener);

    }


    @Override
    protected void initData() {
        //加载列表
        presenter.getRoomList();
        //创建房间
        createRoom();


    }

    @Override
    public void createRoom(CreateRoomBean createRoomBean) {

        String name = createRoomBean.getData().getName();
        int id = createRoomBean.getData().getId();

        //判断是否创建
        int errno = createRoomBean.getErrno();
        if (errno == 0) {

            Toast.makeText(this, "您已创建直播间，准备开始直播！", Toast.LENGTH_SHORT).show();
            if (!TextUtils.isEmpty(name) && id != 0) {
                //获取自己信息
                presenter.myroom();
            }
        } else {
            //创建房间
            createRoom();
        }
    }

    //创建
    private void createRoom() {
        mCreateRoomImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RoomActivity.this, CreateRoomActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void startLive(StartLiveBean startLiveBean) {
        int errno = startLiveBean.getErrno();
        if (errno == 0) {

        }
    }

    @Override
    public void getRoomList(RoomListBean roomListBean) {
        int errno = roomListBean.getErrno();
        if (errno == 0) {
            roomListBeans.clear();
            List<RoomListBean.DataBean> data = roomListBean.getData();
            Toast.makeText(this, "访问列表成功!", Toast.LENGTH_SHORT).show();
            roomListBeans.addAll(data);
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void myroom(MyroomBean myroomBean) {
        errno = myroomBean.getErrno();

        //获取自己信息成功
        if (errno == 0) {


            myId = myroomBean.getData().getId();
            int tag = (int) imgStartLive.getTag();
            if (tag == 0) {
                //开启直播
                Intent intent = new Intent(RoomActivity.this, PushActivity.class);
                intent.putExtra("password", password);
                intent.putExtra("id", myroomBean.getData().getId());
                intent.putExtra("isopen", isopen);
                startActivity(intent);
            } else {
                //修改信息
                Intent intent = new Intent(RoomActivity.this, UpInfoActivity.class);
                intent.putExtra("createId", myId);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "自己信息未获取到,请您去创建", Toast.LENGTH_SHORT).show();
            //创建房间
            createRoom();
        }
    }

    @Override
    public void roomLiveUrl(RoomLiveUrlBean roomLiveUrlBean) {

    }

    @Override
    public void editorRoom(EditorRoomBean editorRoomBean) {

    }

    class OnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.img_startLive) {

                imgStartLive.setTag(0);
                //查找个人信息
                presenter.myroom();

            } else if (id == R.id.img_back) {

                finish();

            } else if (id == R.id.img_upInfo) {

                imgStartLive.setTag(1);
                //修改
                presenter.myroom();
            }
        }
    }


}
