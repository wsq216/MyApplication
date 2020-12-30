package com.example.shopping_android_app.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.login.ILogin;
import com.example.shopping_android_app.model.home.login.LoginBean;
import com.example.shopping_android_app.model.home.login.LogoutBase;
import com.example.shopping_android_app.model.home.login.RegisterBean;
import com.example.shopping_android_app.presenter.home.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View {


    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.layout_pw)
    FrameLayout layoutPw;
    @BindView(R.id.input_pws)
    EditText inputPws;
    @BindView(R.id.img_pws)
    ImageView imgPws;
    @BindView(R.id.layout_pws)
    FrameLayout layoutPws;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected ILogin.Presenter createPrenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        inputPws.setTransformationMethod(PasswordTransformationMethod.getInstance());

        imgPw.setTag(1);
        imgPws.setTag(1);
        btnLogin.setOnClickListener(this::onClick);
        imgPw.setOnClickListener(this::onClick);
        imgPws.setOnClickListener(this::onClick);
    }

    @OnClick
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.img_pw:
                int tag = (int) imgPw.getTag();
                if (tag == 1) {
                    imgPw.setImageResource(R.mipmap.ic_pw_open);
                    imgPw.setTag(2);
                    inputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgPw.setImageResource(R.mipmap.ic_pw_close);
                    imgPw.setTag(1);
                    inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img_pws:
                int tags = (int) imgPws.getTag();
                if (tags == 1) {
                    imgPws.setImageResource(R.mipmap.ic_pw_open);
                    imgPws.setTag(2);
                    inputPws.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgPws.setImageResource(R.mipmap.ic_pw_close);
                    imgPws.setTag(1);
                    inputPws.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }

    private void login() {
        String username = inputUsername.getText().toString();
        String pw = inputPw.getText().toString();
        String pws = inputPws.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw) && !TextUtils.isEmpty(pws)) {
            if (pw.equals(pws)) {
                presenter.getRegister(username, pw);
            }else {
                Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.tips_login), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void loginReturn(LoginBean loginBean) {

    }

    @Override
    public void getRegister(RegisterBean registerBean) {

        if (registerBean.getErrno()==0){
            Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
            finish();
        }else if (registerBean.getErrno()==1000){
            Toast.makeText(this, "用户名已注册！", Toast.LENGTH_SHORT).show();
//            finish();
        }
    }



}