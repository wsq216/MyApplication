package com.example.shopping_android_app.interfaces;

public interface Callback<T> {

    void success(T data);

    void fail(String err);

}
