package com.example.shopping_android_app.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.HotGoodAdapter;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.home.IHotGood;
import com.example.shopping_android_app.model.home.HotBase;
import com.example.shopping_android_app.model.home.HotGoodListBean;
import com.example.shopping_android_app.presenter.home.HotGoodPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HotGoodActivity extends BaseActivity<IHotGood.Presenter> implements IHotGood.View, View.OnClickListener {

    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static int ID = 0;

    @BindView(R.id.img_hotgood)
    ImageView imgHotGood;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.layout_price)
    LinearLayout layoutPrice;
    @BindView(R.id.img_arrow_up)
    ImageView imgArrowUp;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrowDown;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_sort)
    TextView txtSort;
    @BindView(R.id.recy_goodList)
    RecyclerView recyHotGood;


    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order;
    private String sort;
    private int categoryId;
    private List<HotGoodListBean.DataBeanX.DataBean> list;
    private HotGoodAdapter hotGoodAdapter;
    private PopupWindow popupWindow;
    private List<HotGoodListBean.DataBeanX.FilterCategoryBean> filterCategory;
    private TextView mAll;
    private TextView mHome;
    private TextView mDinner;
    private TextView mChider;
    private TextView mCargo;
    private TextView mDiet;
    private View inflate;


    @Override
    protected int getLayout() {
        return R.layout.activity_hotgood;
    }

    @Override
    protected IHotGood.Presenter createPrenter() {
        return new HotGoodPresenter();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        order = ASC;
        sort = DEFAULT;
        categoryId = 0;
        layoutPrice.setTag(0);
        txtAll.setTextColor(Color.parseColor(HotGoodActivity.this.getString(R.color.red)));

    }

    @Override
    protected void initData() {

        list = new ArrayList<>();

        recyHotGood.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        hotGoodAdapter = new HotGoodAdapter(this, list);

        recyHotGood.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyHotGood.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        recyHotGood.setAdapter(hotGoodAdapter);

        presenter.getHotGood(getParam());
        presenter.getHot();

        newPop();
    }

    private void newPop() {
        inflate = LayoutInflater.from(this).inflate(R.layout.hot_pop, null);


        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, 300);
        popupWindow.setAttachedInDecor(true);

    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.layout_price, R.id.txt_all, R.id.txt_sort})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.layout_price:
                int tag = (int) layoutPrice.getTag();
                if (tag == 0) {
                    resetPriceState();
                    priceStateUp();
                    layoutPrice.setTag(1);
                    order = ASC;
                } else if (tag == 1) {
                    resetPriceState();
                    priceStateDown();
                    layoutPrice.setTag(0);
                    order = DESC;
                }
                sort = PRICE;
                presenter.getHotGood(getParam());
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                break;
            case R.id.txt_all:
                resetPriceState();
                txtAll.setTextColor(Color.parseColor(HotGoodActivity.this.getString(R.color.red)));
                sort = DEFAULT;
                presenter.getHotGood(getParam());
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                break;
            case R.id.txt_sort:
                resetPriceState();
                txtSort.setTextColor(Color.parseColor(HotGoodActivity.this.getString(R.color.red)));

                initPop();
                break;
        }
    }

    @SuppressLint("ResourceType")
    private void initPop() {
        popupWindow.showAsDropDown(txtSort);


        mAll = (TextView)inflate.findViewById(R.id.all);
        mHome = (TextView) inflate.findViewById(R.id.home);
        mDinner = (TextView) inflate.findViewById(R.id.dinner);
        mChider = (TextView) inflate.findViewById(R.id.chider);
        mCargo = (TextView) inflate.findViewById(R.id.cargo);
        mDiet = (TextView) inflate.findViewById(R.id.diet);



        mAll.setOnClickListener(this::onClickItem);
        mHome.setOnClickListener(this::onClickItem);
        mDinner.setOnClickListener(this::onClickItem);
        mChider.setOnClickListener(this::onClickItem);
        mCargo.setOnClickListener(this::onClickItem);
        mDiet.setOnClickListener(this::onClickItem);
    }

    @SuppressLint("ResourceType")
    @OnClick
    public void onClickItem(View view) {
        switch (view.getId()) {
            case R.id.all:
                String name1 = mAll.getText().toString();
                initList(name1,mAll);

                break;
            case R.id.home:
                String name2 = mHome.getText().toString();
                initList(name2,mHome);
                break;
            case R.id.dinner:
                String name3 = mDinner.getText().toString();
                initList(name3,mDinner);
                break;
            case R.id.chider:
                String name4 = mChider.getText().toString();
                initList(name4,mChider);
                break;
            case R.id.cargo:
                String name5 = mCargo.getText().toString();
                initList(name5,mCargo);
                break;
            case R.id.diet:
                String name6 = mDiet.getText().toString();
                initList(name6,mDiet);
                break;
        }
    }

    @SuppressLint("ResourceType")
    private void initColor() {
        mAll.setTextColor(Color.parseColor(getString(R.color.black)));
        mHome.setTextColor(Color.parseColor(getString(R.color.black)));
        mDinner.setTextColor(Color.parseColor(getString(R.color.black)));
        mChider.setTextColor(Color.parseColor(getString(R.color.black)));
        mCargo.setTextColor(Color.parseColor(getString(R.color.black)));
        mDiet.setTextColor(Color.parseColor(getString(R.color.black)));
        mAll.setBackgroundResource(R.drawable.shap1);
        mHome.setBackgroundResource(R.drawable.shap1);
        mDinner.setBackgroundResource(R.drawable.shap1);
        mChider.setBackgroundResource(R.drawable.shap1);
        mCargo.setBackgroundResource(R.drawable.shap1);
        mDiet.setBackgroundResource(R.drawable.shap1);
    }

    @SuppressLint("ResourceType")
    private void initList(String name1, TextView txt) {
        initColor();
        for (int i = 0; i < filterCategory.size(); i++) {
            HotGoodListBean.DataBeanX.FilterCategoryBean name = filterCategory.get(i);
            if (name1.equals(name.getName())) {
                sort = CATEGORY;
                categoryId = name.getId();
                presenter.getHotGood(getParam());
                popupWindow.dismiss();
                txt.setTextColor(Color.parseColor(this.getString(R.color.red)));
                txt.setBackgroundResource(R.drawable.shap);
                break;
            }
        }
    }

    /**
     * 组装当前的接口参数
     *
     * @return
     */
    private HashMap<String, String> getParam() {
        HashMap<String, String> map = new HashMap<>();
        map.put("isNew", String.valueOf(isNew));
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        map.put("order", order);
        map.put("sort", sort);
        map.put("categoryId", String.valueOf(categoryId));
        return map;
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private void priceStateUp() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private void priceStateDown() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private void resetPriceState() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.black)));
        txtAll.setTextColor(Color.parseColor(getString(R.color.black)));
        txtSort.setTextColor(Color.parseColor(getString(R.color.black)));
        layoutPrice.setTag(0);
    }

    @Override
    public void getHotGood(HotGoodListBean result) {
        list.clear();
        if (result != null) {
            filterCategory = result.getData().getFilterCategory();
            List<HotGoodListBean.DataBeanX.DataBean> data = result.getData().getData();
            list.addAll(data);
            hotGoodAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getHot(HotBase hotBase) {
        if (hotBase != null) {
            HotBase.DataBean.BannerInfoBean bannerInfo = hotBase.getData().getBannerInfo();
            Glide.with(this).load(bannerInfo.getImg_url()).into(imgHotGood);
            txtInfo.setText(bannerInfo.getName());
        }
    }


}
