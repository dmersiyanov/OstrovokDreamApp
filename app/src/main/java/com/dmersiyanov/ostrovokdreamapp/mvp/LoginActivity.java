package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.dmersiyanov.ostrovokdreamapp.CommonUtils;
import com.dmersiyanov.ostrovokdreamapp.R;
import com.dmersiyanov.ostrovokdreamapp.SharedPrefsHelper;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MvpAppCompatActivity implements LoginView{

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.pass)
    EditText password;

    @OnClick(R.id.login_btn)
    public void onLoginButtonClick() {
        String user_email = String.valueOf(email.getText());
        String pass = String.valueOf(password.getText());

        if (!CommonUtils.isEmailValid(user_email)) {
            showToast("Введите правильный e-mail");
            return;
        }

        if (pass == null || pass.isEmpty()) {
            showToast("Введите пароль");
            return;
        }

        presenter.login(new LoginData(user_email.trim(), pass.trim()));

    }

    @InjectPresenter
    LoginPresenter presenter;

    @ProvidePresenter
    LoginPresenter provideLoginPresenter() {
        SharedPrefsHelper prefsHelper = new SharedPrefsHelper(this);
        final LoginRepo loginRepo = new LoginRepo(prefsHelper);
        return new LoginPresenter(loginRepo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();


    }


    private void init() {

//        SharedPrefsHelper prefsHelper = new SharedPrefsHelper(this);
//
//        final LoginRepo loginRepo = new LoginRepo(prefsHelper);
//        presenter = new LoginPresenter(loginRepo);
//        presenter.attachView(this);

    }

    public LoginData getLoginData() {
        String user_email = String.valueOf(email.getText());
        String pass = String.valueOf(password.getText());
        return new LoginData(user_email.trim(), pass.trim());
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }


    @Override
    public void openDreamsActivity(UserData data) {
        Intent intent = DreamsActivity.getStartIntent(this);
        presenter.saveToken(data.getOauthCredentials().getAccessToken());
        presenter.saveDreams(data.getUserBonusInfo().getPoints().toString());
        startActivity(intent);

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.detachView();
        presenter.destroy();
    }
}
