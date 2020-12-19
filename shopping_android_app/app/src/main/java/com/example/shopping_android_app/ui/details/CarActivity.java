package com.example.shopping_android_app.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.DetailsAdapter;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.home.IDetail;
import com.example.shopping_android_app.model.home.details.DetailsBase;
import com.example.shopping_android_app.model.home.details.RelatedBase;
import com.example.shopping_android_app.presenter.home.DetailsPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarActivity extends BaseActivity<IDetail.Presenter> implements IDetail.View {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_assess)
    TextView txtAssess;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.layout_collect)
    FrameLayout layoutCollect;
    @BindView(R.id.img_car)
    ImageView imgCar;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.layout_car)
    FrameLayout layoutCar;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.txt_addCar)
    TextView txtAddCar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.goods_brief)
    TextView goodsBrief;
    @BindView(R.id.retail_price)
    TextView retailPrice;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.linear_common_problem)
    LinearLayout linearCommonProblem;
    @BindView(R.id.layout_shop)
    ConstraintLayout layoutShop;


    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";

    @Override
    protected int getLayout() {
        return R.layout.activity_car;
    }

    @Override
    protected IDetail.Presenter createPrenter() {
        return new DetailsPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            int id = getIntent().getIntExtra("id", 0);
            if (id > 0) {
                presenter.getDetails(id);
                presenter.getRelated(id);
            } else {
                showToast(getString(R.string.tips_error_goodid));
            }
        }


    }


    /**
     * 商品详情数据  h5
     *
     * @param webData
     */
    private void initGoodDetail(String webData) {
        String content = h5.replace("word", webData);
        Log.i("TAG", content);
        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    @Override
    public void getDetails(DetailsBase detailsBase) {
        initBanner(detailsBase.getData().getGallery());
        initText(detailsBase.getData().getInfo());
        //h5 商品详情
        initGoodDetail(detailsBase.getData().getInfo().getGoods_desc());
        initCommonProblem(detailsBase.getData().getIssue());

    }

    private void initCommonProblem(List<DetailsBase.DataBeanX.IssueBean> productList) {
        for (int i = 0; i < productList.size(); i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.details_item_problem,null);
            TextView answer = inflate.findViewById(R.id.answer);
            TextView question = inflate.findViewById(R.id.question);
            DetailsBase.DataBeanX.IssueBean productListBean = productList.get(i);
            answer.setText(productListBean.getAnswer());
            question.setText(productListBean.getQuestion());
            linearCommonProblem.addView(inflate);
        }
    }

    private void initText(DetailsBase.DataBeanX.InfoBean info) {
        name.setText(info.getName());
        goodsBrief.setText(info.getGoods_brief());
        retailPrice.setText("￥" + info.getRetail_price());
    }

    private void initBanner(List<DetailsBase.DataBeanX.GalleryBean> gallery) {
        banner.setImages(gallery).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                DetailsBase.DataBeanX.GalleryBean galleryBean = (DetailsBase.DataBeanX.GalleryBean) path;
                Glide.with(context).load(galleryBean.getImg_url()).into(imageView);
            }
        }).start();
    }

    @Override
    public void getRelated(RelatedBase brandIdBase) {
        rvList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        DetailsAdapter detailsAdapter = new DetailsAdapter(this, brandIdBase.getData().getGoodsList());

        rvList.setAdapter(detailsAdapter);
    }



}
