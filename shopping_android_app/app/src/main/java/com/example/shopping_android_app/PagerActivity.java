package com.example.shopping_android_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping_android_app.app.Constants;
import com.example.shopping_android_app.app.MyApp;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.app.IApp;
import com.example.shopping_android_app.model.home.app.AppBean;
import com.example.shopping_android_app.presenter.home.app.AppPresenter;
import com.example.shopping_android_app.ui.pager.PagerFragment;
import com.example.shopping_android_app.utils.DownUtils;
import com.example.shopping_android_app.utils.SystemUtils;
import com.luck.picture.lib.tools.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerActivity extends BaseActivity<IApp.Presenter> implements IApp.View {


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.img_1_normal)
    ImageView img1Normal;
    @BindView(R.id.img_1_select)
    ImageView img1Select;
    @BindView(R.id.img_2_normal)
    ImageView img2Normal;
    @BindView(R.id.img_2_select)
    ImageView img2Select;
    @BindView(R.id.img_3_normal)
    ImageView img3Normal;
    @BindView(R.id.img_3_select)
    ImageView img3Select;
    @BindView(R.id.txt_loading)
    TextView txtLoading;
    private List<PagerFragment> list;
    private boolean isUpdate;

    @Override
    protected int getLayout() {
        return R.layout.activity_pager;
    }

    @Override
    protected IApp.Presenter createPrenter() {
        return new AppPresenter();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            PagerFragment pagerFragment = new PagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", i);
            pagerFragment.setArguments(bundle);
            list.add(pagerFragment);
        }

        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetDots();
                if (position == 0) {
                    img1Normal.setVisibility(View.GONE);
                    img1Select.setVisibility(View.VISIBLE);
                } else if (position == 1) {
                    img2Normal.setVisibility(View.GONE);
                    img2Select.setVisibility(View.VISIBLE);
                } else if (position == 2) {
                    img3Normal.setVisibility(View.GONE);
                    img3Select.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void resetDots() {
        img1Normal.setVisibility(View.VISIBLE);
        img2Normal.setVisibility(View.VISIBLE);
        img3Normal.setVisibility(View.VISIBLE);
        img1Select.setVisibility(View.GONE);
        img2Select.setVisibility(View.GONE);
        img3Select.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }



    @Override
    public void getAppInfoReturn(AppBean appBean) {
        String apkPath = Constants.APK_PATH + appBean.getData().get(0).getName();
        File file = new File(apkPath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            DownUtils.downApk(appBean.getData().get(0).getUrl(), apkPath, new DownUtils.Callback() {
                @Override
                public void success() {
                    //安装apk
                    SystemUtils.installNewApk(apkPath);
                }

                @Override
                public void progress(String loading) {
                    txtLoading.post(new Runnable() {
                        @Override
                        public void run() {
                            txtLoading.setText(loading);
                        }
                    });
                }

                @Override
                public void fail(String err) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}