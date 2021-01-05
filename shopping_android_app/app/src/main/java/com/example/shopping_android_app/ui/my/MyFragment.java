package com.example.shopping_android_app.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.MeAdapter;
import com.example.shopping_android_app.base.BaseFragment;
import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.dao.DbActivity;
import com.example.shopping_android_app.ui.login.LoginActivity;
import com.example.shopping_android_app.utils.SpUtils;
import com.live.RoomActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyFragment extends BaseFragment {
    @BindView(R.id.login_head)
    ImageView loginHead;
    @BindView(R.id.login_name)
    TextView loginName;
    @BindView(R.id.jump_login)
    ImageView jumpLogin;
    @BindView(R.id.channel)
    GridView channel;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_mark)
    TextView txtMark;
    @BindView(R.id.layout_userinfo)
    ConstraintLayout layoutUserinfo;

    @Override
    protected BasePresenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {

        int[] img = new int[]{
                R.mipmap.dingdan,
                R.mipmap.youhui,
                R.mipmap.lipin,
                R.mipmap.ic_menu_shoping_nor,
                R.mipmap.anquan,
                R.mipmap.kefu,
                R.mipmap.dingwei,
                R.mipmap.anquan,
                R.mipmap.denglu,
                R.mipmap.bangzhu,
                R.mipmap.yijian,
                R.mipmap.zhido

        };
        String[] title = new String[]{
                "我的订单",
                "优惠券",
                "礼品卡",
                "我的收藏",
                "我的足迹",
                "会员福利",
                "地址管理",
                "账号安全",
                "联系客服",
                "帮助中心",
                "意见反馈",
                "直播"
        };
        List<MyBean> list = new ArrayList<MyBean>();
        for (int i = 0; i < title.length; i++) {
            MyBean myBean = new MyBean(title[i], img[i]);
            list.add(myBean);
        }
        MeAdapter meAdapter = new MeAdapter(getActivity(), list);
        channel.setAdapter(meAdapter);

        channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 3:
                        startActivity(new Intent(getActivity(), DbActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(getActivity(), RoomActivity.class));
                        break;
                }
            }
        });

    }

    @Override
    protected void initView() {

        layoutUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UserDetailActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        loginName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri uri = data.getData();
            if (uri != null) {
                loginHead.setImageURI(uri);
            }
        }
        if (requestCode == 2 && resultCode == 100) {
            loginName.setText(data.getStringExtra("name"));
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }


    @Override
    public void onResume() {
        super.onResume();
        String username = SpUtils.getInstance().getString("url");
        String name = SpUtils.getInstance().getString("username");
        if (!TextUtils.isEmpty(username)){
            Glide.with(getActivity()).load(username).apply(new RequestOptions().circleCrop()).into(loginHead);
        }else{
            Glide.with(getActivity()).load(R.mipmap.denglu).apply(new RequestOptions().circleCrop()).into(loginHead);
        }
        if (!TextUtils.isEmpty(name)){
            loginName.setText(name);
        }else{
            loginName.setText("请登录");

        }
    }
}