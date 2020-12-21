package com.example.shopping_android_app.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.IBasePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BigImageActivity extends BaseActivity {


    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.num)
    TextView num;

    @Override
    protected int getLayout() {
        return R.layout.activity_big_image;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        List<String> listUrl = (List<String>) intent.getSerializableExtra("listUrl");
        int id = intent.getIntExtra("id", 0);
        pager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return listUrl.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(BigImageActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(BigImageActivity.this).load(listUrl.get(position)).into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        num.setText((id+1)+"/"+listUrl.size());

        pager.setCurrentItem(id);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                num.setText((position+1)+"/"+listUrl.size());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}