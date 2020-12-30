package com.example.shopping_android_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    Disposable disposable;
    private TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<View> list=new ArrayList<>();
        View inflate = LayoutInflater.from(this).inflate(R.layout.fragment_blank, null);
        View inflate1 = LayoutInflater.from(this).inflate(R.layout.fragment_blank_time, null);
        list.add(inflate);
        list.add(inflate1);

        timer = inflate1.findViewById(R.id.tv_timer);


        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return  object==view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = list.get(position);
                container.addView(view);
                return view;
            }
        });


        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewPagerActivity.this,MainActivity.class));
                finish();
                disposable.dispose();
            }
        });


        //页码的点击监听
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onPageSelected(int position) {
                if(position == 1){//在最后一页执行倒计时
                    //TODO            Interval操作符(有范围)：创建一个按照固定时间发射整数序列的Observable
                    disposable = Observable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS) //起始值，发送总数量，初始延迟，固定延迟
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            //两种写法    1. lambda表达式写法：
//                            .subscribe(time -> tv_timer.setText((10 - time) + "s"),
//                                    Throwable::printStackTrace,
//                                    () -> {
//                                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                                    }
//                            );

                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    long time = 3 - aLong;
                                    timer.setText(time+"s");
                                    if(time == 0){
                                        startActivity(new Intent(ViewPagerActivity.this,MainActivity.class));
                                        finish();
                                    }
                                }
                            });
                }else{
                    cancelCallback();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    //取消订阅的方法
    private void cancelCallback() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}