package com.example.shopping_android_app.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.AcquisitionAdapter;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.model.home.shop.CarBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AcquisitionActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.linear)
    LinearLayout linear;

    int sum = 0;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_coupon_money)
    TextView tvCouponMoney;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.con_address)
    ConstraintLayout conAddress;
    private AcquisitionAdapter acquisitionAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_acquisition;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        List<CarBean.DataBean.CartListBean> list = (List<CarBean.DataBean.CartListBean>) getIntent().getSerializableExtra("list");

        for (int i = 0; i < list.size(); i++) {
            double market_price = list.get(i).getMarket_price();
            int number = list.get(i).getNumber();

            sum += (int) (market_price * number);

        }

        tvMoney.setText("￥" + sum);
        tvFreight.setText("￥0");
        tvCouponMoney.setText("-￥0");

        tvSum.setText("实付：￥" + sum);

        rvAddress.setLayoutManager(new LinearLayoutManager(this));
        acquisitionAdapter = new AcquisitionAdapter(this, list);
        rvAddress.setAdapter(acquisitionAdapter);
    }



    @OnClick({R.id.con_address, R.id.tv_coupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.con_address:
                startActivityForResult(new Intent(AcquisitionActivity.this,AddressActivity.class),1);
                break;
            case R.id.tv_coupon:
                break;
        }
    }
}