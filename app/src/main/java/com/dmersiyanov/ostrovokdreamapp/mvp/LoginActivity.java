package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.dmersiyanov.ostrovokdreamapp.R;
import com.dmersiyanov.ostrovokdreamapp.SharedPrefsHelper;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.pass)
    EditText password;

    @OnClick(R.id.login_btn)
    public void onLoginButtonClick() {
        presenter.login();

    }

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();


    }

    private void init() {

        SharedPrefsHelper prefsHelper = new SharedPrefsHelper(this);

        final LoginModel loginModel = new LoginModel(this, prefsHelper);
        presenter = new LoginPresenter(loginModel);
        presenter.attachView(this);

    }

    public LoginData getLoginData() {
        String user_email = String.valueOf(email.getText());
        String pass = String.valueOf(password.getText());
        return new LoginData(user_email.trim(), pass.trim());
    }


    public void openDreamsActivity(UserData data) {
        Intent intent = new Intent(this, DreamsActivity.class);
        intent.putExtra("auth-token", data.getOauthCredentials().getAccessToken());
        intent.putExtra("dreams-amount", data.getUserBonusInfo().getPoints().toString());
        this.startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.destroy();
    }
}
