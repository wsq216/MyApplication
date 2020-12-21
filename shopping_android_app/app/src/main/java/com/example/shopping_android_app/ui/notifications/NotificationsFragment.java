package com.example.shopping_android_app.ui.notifications;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseFragment;
import com.example.shopping_android_app.interfaces.catalog.ICatalog;
import com.example.shopping_android_app.model.home.catalog.CatalogBase;
import com.example.shopping_android_app.model.home.catalog.CatalogListBase;
import com.example.shopping_android_app.presenter.home.CatalogPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class NotificationsFragment extends BaseFragment<CatalogPresenter> implements ICatalog.View {
    @BindView(R.id.action_up_btn)
    Button actionUpBtn;
    @BindView(R.id.tab)
    VerticalTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    private List<Fragment> list;
    private ArrayList<String> mTitlesList;

    @Override
    protected CatalogPresenter createPersenter() {
        return new CatalogPresenter();
    }

    @Override
    protected void initData() {
        presenter.getCatalog();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_notifications;
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }

    @Override
    public void getCatalog(CatalogBase catalogBase) {
        if (catalogBase != null) {
            List<CatalogBase.DataBean.CategoryListBean> categoryList = catalogBase.getData().getCategoryList();
            if (categoryList != null) {
                initViewPager(categoryList);
            }
        } else {

        }
    }

    @Override
    public void getCatalogList(CatalogListBase catalogListBase) {

    }


    private void initViewPager(List<CatalogBase.DataBean.CategoryListBean> categoryList) {
        list = new ArrayList<>();
        mTitlesList = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i++) {
            CatalogBase.DataBean.CategoryListBean categoryListBean = categoryList.get(i);
            CatalogFragment catalogFragment = new CatalogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("id", categoryListBean.getId());
            catalogFragment.setArguments(bundle);
            list.add(catalogFragment);
        }


        vp.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return super.getItemPosition(object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return categoryList.get(position).getName();
            }

        });

        tab.setupWithViewPager(vp);
    }


}