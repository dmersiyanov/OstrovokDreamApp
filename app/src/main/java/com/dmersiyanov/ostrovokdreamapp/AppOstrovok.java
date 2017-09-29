package com.dmersiyanov.ostrovokdreamapp;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dmersianov on 26/09/2017.
 */

public class AppOstrovok extends Application {


    private static OstrovokService ostrovokService;
    private Retrofit retrofit;



    @Override
    public void onCreate() {
        super.onCreate();

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://mota.ostrovok.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ostrovokService = retrofit.create(OstrovokService.class);
    }

    public static OstrovokService getApi() {
        return ostrovokService;
    }

}
