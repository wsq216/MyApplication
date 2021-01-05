package com.example.shopping_android_app.app;

import android.app.Application;

import com.example.mvp.app.MvpApp;
import com.example.shopping_android_app.utils.SpUtils;
import com.live.MyApplication;

public class MyApp extends Application {
    private static String[] modules = {"com.live.MyApplication","com.example.mvp.app.MvpApp"};
    public static MyApp app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        initModel();
    }

    private void initModel() {
        for (String moduleImpl : modules){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof MyApplication){
                    ((MyApplication) obj).initApp(app);
                }

                if (obj instanceof MvpApp){
                    ((MvpApp) obj).initApp(app);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
