package com.example.shopping_android_app.ui.login;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping_android_app.R;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.interfaces.login.ILogin;
import com.example.shopping_android_app.model.home.login.LoginBean;
import com.example.shopping_android_app.model.home.login.LogoutBase;
import com.example.shopping_android_app.model.home.login.RegisterBean;
import com.example.shopping_android_app.presenter.home.login.LoginPresenter;
import com.example.shopping_android_app.ui.register.RegisterActivity;
import com.example.shopping_android_app.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View {


    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.layout_pw)
    FrameLayout layoutPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.register)
    TextView register;
    private String username;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected ILogin.Presenter createPrenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());

        imgPw.setTag(1);
        btnLogin.setOnClickListener(this::onClick);
        imgPw.setOnClickListener(this::onClick);
        register.setOnClickListener(this::onClick);
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
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void login() {
        username = inputUsername.getText().toString();
        String pw = inputPw.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)) {
            presenter.login(username, pw);
        } else {
            Toast.makeText(this, getString(R.string.tips_login), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void loginReturn(LoginBean loginBean) {
        if (!TextUtils.isEmpty(loginBean.getData().getToken())) {
            SpUtils.getInstance().setValue("token", loginBean.getData().getToken());
            SpUtils.getInstance().setValue("uid", loginBean.getData().getUserInfo().getUid());
            SpUtils.getInstance().setValue("username", loginBean.getData().getUserInfo().getUsername());
            Intent intent = getIntent();
            intent.putExtra("name",username);
            setResult(100,intent);
            finish();
        }
    }

    @Override
    public void getRegister(RegisterBean registerBean) {

    }




}