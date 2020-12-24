package com.example.shopping_android_app.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.MainActivity;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.BrandListAdapter;
import com.example.shopping_android_app.adapter.CategoryListAdapter;
import com.example.shopping_android_app.adapter.HotGoodsAdapter;
import com.example.shopping_android_app.adapter.NewGoodsListAdapter;
import com.example.shopping_android_app.adapter.TopicListAdapter;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.base.BaseFragment;
import com.example.shopping_android_app.interfaces.home.IHome;
import com.example.shopping_android_app.model.home.CategoryBean;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.HomeBean;
import com.example.shopping_android_app.presenter.home.HomePresenter;
import com.example.shopping_android_app.ui.channel.CategoryActivity;
import com.example.shopping_android_app.ui.dashboard.BrandActivity;
import com.example.shopping_android_app.ui.dashboard.DashboardFragment;
import com.example.shopping_android_app.ui.details.CarActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomePresenter> implements IHome.View {

    @BindView(R.id.action_up_btn)
    Button actionUpBtn;
    @BindView(R.id.linesr)
    LinearLayout linesr;
    @BindView(R.id.liness)
    LinearLayout liness;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recy_brand)
    RecyclerView recyBrand;
    @BindView(R.id.recy_newgood)
    RecyclerView recyNewgood;
    @BindView(R.id.recy_pop)
    RecyclerView recyPop;
    @BindView(R.id.recy_tit)
    RecyclerView recyTit;
    @BindView(R.id.txt_brand_title)
    TextView txtBrandTitle;
    @BindView(R.id.txt_newgood_title)
    TextView txtNewgoodTitle;
    @BindView(R.id.txt_pop_title)
    TextView txtPopTitle;
    @BindView(R.id.txt_tit_title)
    TextView txtTitTitle;

    @Override
    protected HomePresenter createPersenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        presenter.getHome();
    }

    @Override
    protected void initView() {
        txtTitTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }

    @Override
    public void getHomeReturn(HomeBean result) {
        if (result != null) {
            HomeBean.DataBean data = result.getData();
            initBanner(data.getBanner());
            initChannel(data.getChannel());
            initBrand(data.getBrandList());
            initNewGoodsList(data.getNewGoodsList());
            initHotGoodsList(data.getHotGoodsList());
            initTopicList(data.getTopicList());
            initCategoryList(data.getCategoryList());
        }
    }

    @Override
    public void getCategory(CategoryBean categoryBean) {

    }

    @Override
    public void getCategoryList(CategoryListBean categoryListBean) {

    }

    private void initChannel(List<HomeBean.DataBean.ChannelBean> channel) {
        for (int i = 0; i < channel.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_item_ssss, null);
            TextView text = view.findViewById(R.id.txt_home_title);
            text.setText(channel.get(i).getName());
            ImageView image = view.findViewById(R.id.image);

            Glide.with(getActivity()).load(channel.get(i).getIcon_url()).into(image);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            liness.addView(view);
            view.setTag(channel.get(i));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = ((HomeBean.DataBean.ChannelBean) v.getTag()).getUrl();
                    String name = ((HomeBean.DataBean.ChannelBean) v.getTag()).getName();
                    int i1 = url.indexOf("=");
                    String substring = url.substring(i1 + 1);
                    Intent intent = new Intent(getActivity(), CategoryActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("url", substring);
                    startActivity(intent);
                }
            });
        }
    }

    private void initCategoryList(List<HomeBean.DataBean.CategoryListBean> goodsList) {
        for (int i = 0; i < goodsList.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_item_sss, null);
            TextView text = view.findViewById(R.id.txt_home_title);
            text.setText(goodsList.get(i).getName());
            RecyclerView recy_home = view.findViewById(R.id.recy_home);
            List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList1 = goodsList.get(i).getGoodsList();
            recy_home.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            CategoryListAdapter categoryListAdapter = new CategoryListAdapter(getActivity(), goodsList1);
            recy_home.setAdapter(categoryListAdapter);
            view.setTag(goodsList1);
            linesr.addView(view);
            categoryListAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsListBeans = (List<HomeBean.DataBean.CategoryListBean.GoodsListBean>) view.getTag();
                    HomeBean.DataBean.CategoryListBean.GoodsListBean goodsListBean = goodsListBeans.get(pos);
                    Intent intent = new Intent(getActivity(), CarActivity.class);
                    intent.putExtra("id", goodsListBean.getId());
                    startActivity(intent);
                }
            });
        }

    }


    private void initTopicList(List<HomeBean.DataBean.TopicListBean> topicList) {
        recyTit.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

        TopicListAdapter topicListAdapter = new TopicListAdapter(getActivity(), topicList);

        recyTit.setAdapter(topicListAdapter);
    }

    private void initHotGoodsList(List<HomeBean.DataBean.HotGoodsListBean> hotGoodsList) {
        recyPop.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyPop.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        HotGoodsAdapter hotGoodsAdapter = new HotGoodsAdapter(getActivity(), hotGoodsList);

        recyPop.setAdapter(hotGoodsAdapter);

    }

    private void initBrand(List<HomeBean.DataBean.BrandListBean> brandList) {
        recyBrand.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        BrandListAdapter brandListAdapter = new BrandListAdapter(getActivity(), brandList);

        recyBrand.setAdapter(brandListAdapter);
    }

    private void initNewGoodsList(List<HomeBean.DataBean.NewGoodsListBean> newGoodsList) {

        recyNewgood.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        NewGoodsListAdapter newGoodsListAdapter = new NewGoodsListAdapter(getActivity(), newGoodsList);

        recyNewgood.setAdapter(newGoodsListAdapter);
    }

    private void initBanner(List<HomeBean.DataBean.BannerBean> list) {
        banner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean bannerBean = (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(bannerBean.getImage_url()).into(imageView);
            }
        }).start();
    }


    @OnClick({R.id.txt_brand_title, R.id.txt_newgood_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_brand_title:
                startActivity(new Intent(getActivity(), BrandActivity.class));
                break;
            case R.id.txt_newgood_title:
                startActivity(new Intent(getActivity(), HotGoodActivity.class));
                break;
        }
    }
}