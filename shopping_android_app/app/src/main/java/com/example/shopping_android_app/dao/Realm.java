package com.example.shopping_android_app.dao;

import android.content.Context;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.app.MyApp;
import com.example.shopping_android_app.utils.SpUtils;

import java.security.SecureRandom;

import io.realm.DynamicRealm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class Realm {
    public static RealmConfiguration getRealm(){
        byte[] bytes = new byte[1024];

        new SecureRandom().nextBytes(SpUtils.getInstance().getString("token").getBytes());
        io.realm.Realm.init(MyApp.app);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("test.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .migration(new CustomMigration())//升级数据库
                .build();



        return configuration;
    }

    private static class CustomMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        }
    }
}
