package com.example.shopping_android_app.ui.pager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopping_android_app.MainActivity;
import com.example.shopping_android_app.R;

import java.lang.ref.WeakReference;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PagerFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.txt_time)
    TextView txtTime;
    @BindView(R.id.txt_comein)
    TextView txtComein;
    private int timer;
    private Unbinder bind;

    private boolean live;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        live=isVisibleToUser;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_pager, container, false);
        // Inflate the layout for this fragment
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        initListener();
        timer = 15;
        int id = getArguments().getInt("id", 0);
        int mip=0;
        if (id == 1) {
            mip=R.mipmap.start_one;
        } else if (id == 2) {
            mip=R.mipmap.start_two;

        } else if (id == 3) {
            mip=R.mipmap.start_three;

        }
        img.setImageResource(mip);
        if(id == 3){
            txtTime.setVisibility(View.VISIBLE);
            //开启倒计时
            startTime();
        }else{
            txtTime.setVisibility(View.GONE);
        }
    }

    private MyHandle myHandle=new MyHandle(this);

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            timer--;
            if (timer<0){
                goMain();
            }else{
                Message message=new Message();
                message.what=1;
                if (timer<10){
                    // 倒计时 跳过+time 立即体验按钮显示
                    message.obj = "跳过"+timer;
                    message.arg1 = 10;
                }else{
                    message.obj=timer;

                }
                myHandle.sendMessage(message);
                if (live){
                    myHandle.postDelayed(this,1000);
                }
            }
        }
    };

    private void startTime() {
        myHandle.postDelayed(runnable,1000);
    }



    /**
     * 用来解决handler引起的内存泄露问题 原因：内部类默认持有外部类的引用
     */
    static class MyHandle extends Handler{

        private final WeakReference<PagerFragment> pagerFragmentWeakReference;
        private final PagerFragment pagerFragment;

        public MyHandle(PagerFragment fragment){
            pagerFragmentWeakReference = new WeakReference<>(fragment);
            pagerFragment = pagerFragmentWeakReference.get();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    pagerFragment.setTime(String.valueOf(msg.obj));
                    if(msg.arg1 == 10){
                        pagerFragment.txtComein.setVisibility(View.VISIBLE);
                        pagerFragment.txtComein.setEnabled(true);
                        pagerFragment.txtTime.setEnabled(true);
                    }else{
                        pagerFragment.txtComein.setVisibility(View.GONE);
                        pagerFragment.txtComein.setEnabled(false);
                        pagerFragment.txtTime.setEnabled(false);
                    }
                    break;
            }
        }
    }

    /**
     * 设置倒计时的显示
     * @param str
     */
    private void setTime(String str){
        txtTime.setText(str);
    }


    private void initListener(){
        txtComein.setOnClickListener(this);
        txtTime.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_comein:
                goMain();
                break;
            case R.id.txt_time:
                goMain();
                break;
        }
    }

    private void goMain() {
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
        myHandle.removeCallbacks(runnable);
    }
}