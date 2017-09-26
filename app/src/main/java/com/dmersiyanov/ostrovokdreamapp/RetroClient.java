package com.dmersiyanov.ostrovokdreamapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dmersianov on 26/09/2017.
 */

public class RetroClient {

    LoginData loginData = new LoginData("dmersiyanov@mail.ru", "123");

    private Gson gson = new GsonBuilder().create();

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://mota.p.ostrovok.ru/api/")
            .build();







}
