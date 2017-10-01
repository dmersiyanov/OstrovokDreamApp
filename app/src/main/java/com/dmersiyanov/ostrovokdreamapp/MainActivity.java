package com.dmersiyanov.ostrovokdreamapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.api.ResponseAPI;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    private Button authBtn;
    private LoginData loginData;

    private RecyclerView dreamsRecycler;
    private DreamsAdapter dreamsAdapter = new DreamsAdapter();
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dreamsRecycler = (RecyclerView) findViewById(R.id.dreamslist);
        layoutManager = new LinearLayoutManager(this);
        dreamsRecycler.setLayoutManager(layoutManager);
        dreamsRecycler.setAdapter(dreamsAdapter);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        authBtn = (Button) findViewById(R.id.login);
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginData = new LoginData("dmersiyanov@mail.ru", "123");

                AppOstrovok.getApi().login(loginData).enqueue(new Callback<ResponseAPI>() {
                    @Override
                    public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {


                        try {
                            UserData userData = response.body().getUserData();
                            if (response.body() != null) {
                                String email = userData.getEmail();
                                Toast.makeText(MainActivity.this, "Успешная авторизация " + email, Toast.LENGTH_LONG).show();
                        }

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAPI> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Во время авторизации произошла ошибка " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
