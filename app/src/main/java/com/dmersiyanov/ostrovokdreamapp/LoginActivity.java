package com.dmersiyanov.ostrovokdreamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.api.AppOstrovok;
import com.dmersiyanov.ostrovokdreamapp.api.ResponseLogin;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    private LoginData loginData;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.login_activ);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // loginData = new LoginData("dmersiyanov@mail.ru" , "123");
                String user_email = String.valueOf(email.getText());
                String pass = String.valueOf(password.getText());
                loginData = new LoginData(user_email.trim(), pass.trim());


                Observable<ResponseLogin> loginObservable = AppOstrovok.getApi().login(loginData);
                loginObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseLogin>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(LoginActivity.this, "Успешная авторизация ", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(LoginActivity.this, "Во время авторизации произошла ошибка " + e.getMessage(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onNext(ResponseLogin responseLogin) {
                                UserData userData = responseLogin.getData();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("auth-token", userData.getOauthCredentials().getAccessToken());
                                intent.putExtra("dreams-amount", userData.getUserBonusInfo().getPoints().toString());
                                startActivity(intent);
                            }
                        });


            }
        });

    }


}
