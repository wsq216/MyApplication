package com.example.shopping_android_app.model.home.login;

public class LogoutBase {

    /**
     * errno : 0
     * errmsg :
     * data : 退出登录成功
     */

    private int errno;
    private String errmsg;
    private String data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
