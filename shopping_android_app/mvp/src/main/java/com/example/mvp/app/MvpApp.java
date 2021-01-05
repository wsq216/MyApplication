package com.example.mvp.app;

import android.app.Application;

public class MvpApp {

    public static Application application;

    public void initApp(Application application){
        this.application=application;
    }
}
