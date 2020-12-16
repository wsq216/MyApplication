package com.example.shopping_android_app.interfaces;

public interface Callback<T> {

    void fail(String msg);

    void success(T t);

}
