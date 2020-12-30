package com.example.shopping_android_app.dao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.shopping_android_app.MainActivity;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.DbAdapter;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.IBasePresenter;
import com.example.shopping_android_app.ui.login.LoginActivity;
import com.example.shopping_android_app.utils.SpUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class DbActivity extends BaseActivity {

    //    @BindView(R.id.rv_db)
//    RecyclerView rvDb;

    @BindView(R.id.rv_db)
    SwipeMenuRecyclerView rvDb;
    private List<DbUtils> list;
    private DbAdapter dbAdapter;
    private Realm realm;

    @Override
    protected int getLayout() {
        return R.layout.activity_db;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        if (SpUtils.getInstance().getString("token") != null) {
            realm = MainActivity.getRealm();
            RealmResults<DbUtils> user = realm.where(DbUtils.class).findAll();
            list = new ArrayList<>();
            list.addAll(user);
//            rvDb.setLayoutManager(new LinearLayoutManager(this));
//            DbAdapter dbAdapter = new DbAdapter(this, list);
//            rvDb.setAdapter(dbAdapter);
            rvDb.setLayoutManager(new LinearLayoutManager(this));
            rvDb.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));


            dbAdapter = new DbAdapter(this, list);
            // 设置菜单创建器
            rvDb.setSwipeMenuCreator(swipeMenuCreator);
            // 设置菜单Item点击监听
            rvDb.setSwipeMenuItemClickListener(itemClickListener);
            rvDb.setAdapter(dbAdapter);
        } else {
            startActivity(new Intent(DbActivity.this, LoginActivity.class));
        }
    }
    //创建侧滑菜单
    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            @SuppressLint("ResourceAsColor") SwipeMenuItem deleteItem = new SwipeMenuItem(DbActivity.this)
                    .setImage(R.drawable.s1)
                    .setWidth(144)
                    .setText("删除")
                    .setTextColor(R.color.red)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //高（MATCH_PARENT意为Item多高侧滑菜单多高 （推荐使用））
            swipeRightMenu.addMenuItem(deleteItem);//添加一个条目在右侧菜单
        }
    };

    private int adapterPosition;
    //侧滑菜单的点击事件
    private SwipeMenuItemClickListener itemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            //在menuBridge中我们可以得到侧滑的这一项item的position (menuBridge.getAdapterPosition())
            adapterPosition = menuBridge.getAdapterPosition();
            list.remove(adapterPosition);
            //删除数据库
            RealmResults<DbUtils> user = realm.where(DbUtils.class).findAll();
            realm.executeTransaction(new io.realm.Realm.Transaction() {
                @Override
                public void execute(io.realm.Realm realm) {
                    user.get(adapterPosition).deleteFromRealm();
                }
            });
            dbAdapter.notifyDataSetChanged();
        }
    };
    @Override
    protected void initData() {

    }


}