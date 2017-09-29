package com.dmersiyanov.ostrovokdreamapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;

public class MainActivity extends AppCompatActivity  {

    private Button authBtn;
    LoginData loginData = new LoginData("dmersiyanov@yandex.ru", "lewumoqyZa");

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        authBtn = (Button) findViewById(R.id.login);
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Response response = AppOstrovok.getApi().login(loginData).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                AppOstrovok.getApi().login(loginData).enqueue(new Callback<List<Data>>() {
                    @Override
                    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                        if(response.body() != null) {
                            Toast.makeText(MainActivity.this, "Успешная авторизация" + response.body().toString(), Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Не успешная авторизация", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });

    }
}
