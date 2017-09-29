package com.dmersiyanov.ostrovokdreamapp;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by dmersianov on 26/09/2017.
 */

public interface OstrovokService {

    @Headers({"Content-Type: application/json",
            "device-screen-resolution: 1080x1794",
            "ota-brand: ostrovok",
            "User-Agent: Ostrovok/3.6.5-DEV-DEBUG (android; FRD-L09; Android 7.0; Scale/2.00) ASID eafaafe12402f569 AID e274a392-8f96-40f5-aad6-aec644af12dd",
            "x-mota-api-version: 2",
            "x-mota-client-name: Android"})
    @POST("v2/login")
    Call<List<Data>> login(@Body Object request);
}
