package com.dmersiyanov.ostrovokdreamapp;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;

/**
 * Created by dmersianov on 26/09/2017.
 */

public interface OstrovokService {

    @POST("v2/login")
    Call<Object> login(@Body Object request);
}
