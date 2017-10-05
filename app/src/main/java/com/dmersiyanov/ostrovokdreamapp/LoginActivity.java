package com.dmersiyanov.ostrovokdreamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.api.ResponseLogin;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    private LoginData loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.login_activ);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginData = new LoginData("dmersiyanov@mail.ru", "123");

                AppOstrovok.getApi().login(loginData).enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                        try {
                            UserData userData = response.body().getData();
                            if (response.isSuccessful()) {
                                String email = userData.getEmail();
                                Toast.makeText(LoginActivity.this, "Успешная авторизация " + email, Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("auth-token", response.body().getData().getOauthCredentials().getAccessToken());
                                startActivity(intent);
                            }

                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Во время авторизации произошла ошибка " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }


}
