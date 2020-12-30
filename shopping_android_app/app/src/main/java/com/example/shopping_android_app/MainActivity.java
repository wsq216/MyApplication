package com.example.shopping_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.navigation_shop,R.id.navigation_my)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        int id = getIntent().getIntExtra("id", 0);
        navView.setSelectedItemId(navView.getMenu().getItem(id).getItemId());


        navView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        item.setIcon(R.mipmap.ic_menu_choice_pressed);
                        break;
                    case R.id.navigation_dashboard:
                        item.setIcon(R.mipmap.ic_menu_topic_pressed);
                        break;
                    case R.id.navigation_notifications:
                        item.setIcon(R.mipmap.ic_menu_sort_pressed);
                        break;
                    case R.id.navigation_shop:
                        item.setIcon(R.mipmap.ic_menu_shoping_pressed);
                        break;
                    case R.id.navigation_my:
                        item.setIcon(R.mipmap.ic_menu_me_pressed);
                        break;
                }
            }
        });




    }

    public static RealmConfiguration configuration1;

    public static Realm getRealm(){
        //创建数据库
        RealmConfiguration configuration = com.example.shopping_android_app.dao.Realm.getRealm();
        if (configuration1==null){
            configuration1=configuration;
        }
        io.realm.Realm realm = io.realm.Realm.getInstance(configuration1);
        return realm;
    }

}