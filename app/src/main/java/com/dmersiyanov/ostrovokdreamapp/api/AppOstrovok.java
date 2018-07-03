package com.dmersiyanov.ostrovokdreamapp.api;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://mota.ostrovok.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        ostrovokService = retrofit.create(OstrovokService.class);

    }

    public static OstrovokService getApi() {
        return ostrovokService;
    }

}
