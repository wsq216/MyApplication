package com.example.shopping_android_app.ui.channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.home.IHome;
import com.example.shopping_android_app.model.home.CategoryBean;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.presenter.home.HomePresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity<HomePresenter> implements IHome.View {


    @BindView(R.id.tabMode)
    TabLayout tabMode;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_tool_title)
    TextView tvToolTitle;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    private String url;

    @Override
    protected int getLayout() {
        return R.layout.activity_category;

    }

    @Override
    protected HomePresenter createPrenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        LayoutInflater.from(this).inflate(R.layout.pop_item);
        PopupWindow popupWindow = new PopupWindow();

    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
        if (url != null) {
            presenter.getCategory(url);
        }
    }

    @Override
    public void getHomeReturn(HomeBean result) {
    }

    @Override
    public void getCategory(CategoryBean categoryBean) {
        List<CategoryBean.DataBean.BrotherCategoryBean> brotherCategory = categoryBean.getData().getBrotherCategory();
        if (categoryBean != null) {
            initTab(brotherCategory);
        }
    }

    private void initTab(List<CategoryBean.DataBean.BrotherCategoryBean> brotherCategory) {
        List<Fragment> list = new ArrayList<>();

        for (int i = 0; i < brotherCategory.size(); i++) {
            CategoryBean.DataBean.BrotherCategoryBean brotherCategoryBean = brotherCategory.get(i);
            int id = brotherCategoryBean.getId();
            CategroryFragment categroryFragment = new CategroryFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("currentCategory", brotherCategoryBean);
            bundle.putInt("id", id);
            categroryFragment.setArguments(bundle);
            list.add(categroryFragment);
        }

        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

        });

        String name1 = getIntent().getStringExtra("name");
        tabMode.setupWithViewPager(pager);

        for (int i = 0; i < brotherCategory.size(); i++) {
            String name = brotherCategory.get(i).getName();
            tabMode.getTabAt(i).setText(name);
            if (name.equals(name1)) {
                TabLayout.Tab tabAt = tabMode.getTabAt(i);
                tabMode.selectTab(tabAt);
            }

        }


    }

    @Override
    public void getCategoryList(CategoryListBean categoryListBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}