package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dmersiyanov.ostrovokdreamapp.R;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    EditText email;
    EditText password;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init() {

        loginBtn = (Button) findViewById(R.id.login_activ);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);


        final LoginModel loginModel = new LoginModel(this);
        presenter = new LoginPresenter(loginModel);
        presenter.attachView(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login();

            }
        });

    }

    public LoginData getLoginData() {
        String user_email = String.valueOf(email.getText());
        String pass = String.valueOf(password.getText());
        LoginData loginData = new LoginData(user_email.trim(), pass.trim());
        return loginData;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
