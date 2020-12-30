package com.example.shopping_android_app.ui.channel;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.CategoryAdapter;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.base.BaseFragment;
import com.example.shopping_android_app.base.BasePresenter;
import com.example.shopping_android_app.interfaces.home.IHome;
import com.example.shopping_android_app.model.home.CategoryBean;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.presenter.home.HomePresenter;
import com.example.shopping_android_app.ui.details.CarActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;


public class CategroryFragment extends BaseFragment<HomePresenter> implements IHome.View {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_front_name)
    TextView tvFrontName;
    @BindView(R.id.rv_categroy)
    RecyclerView rv_categroy;

    @Override
    protected HomePresenter createPersenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        int id = getArguments().getInt("id", 0);
        if (id!=0){
            presenter.getCategoryList(id+"",1,100);
        }
    }

    @Override
    protected void initView() {
        CategoryBean.DataBean.BrotherCategoryBean currentCategory = (CategoryBean.DataBean.BrotherCategoryBean) getArguments().getSerializable("currentCategory");
        tvFrontName.setText(currentCategory.getFront_name());
        tvName.setText(currentCategory.getName());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_categrory;
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }

    @Override
    public void getHomeReturn(HomeBean result) {

    }

    @Override
    public void getCategory(CategoryBean categoryBean) {

    }

    @Override
    public void getCategoryList(CategoryListBean categoryListBean) {
        if (categoryListBean!=null){
            initList(categoryListBean.getData().getData());
        }
    }

    private void initList(List<CategoryListBean.DataBeanX.DataBean> data) {
        rv_categroy.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(),data);

        rv_categroy.setAdapter(categoryAdapter);

        categoryAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                CategoryListBean.DataBeanX.DataBean goodsListBean = data.get(pos);
                Intent intent = new Intent(getActivity(), CarActivity.class);
                intent.putExtra("id", goodsListBean.getId());
                startActivity(intent);
            }
        });
    }
}