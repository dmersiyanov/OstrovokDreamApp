package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.CommonUtils;
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

        final LoginModel loginModel = new LoginModel(prefsHelper);
        presenter = new LoginPresenter(loginModel);
        presenter.attachView(this);

    }

//    public LoginData getLoginData() {
//        String user_email = String.valueOf(email.getText());
//        String pass = String.valueOf(password.getText());
//
//        if (!CommonUtils.isEmailValid(user_email)) {
//            showToast("Введите правильный e-mail");
//            return;
//        }
//
//        if (pass == null || pass.isEmpty()) {
//            showToast("Введите пароль");
//            return;
//        }
//
//
//        return new LoginData(user_email.trim(), pass.trim());
//    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }



    public void openDreamsActivity(UserData data) {
        Intent intent = DreamsActivity.getStartIntent(this);
        presenter.saveToken(data.getOauthCredentials().getAccessToken());
        presenter.saveDreams(data.getUserBonusInfo().getPoints().toString());
        startActivity(intent);

    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.destroy();
    }
}
