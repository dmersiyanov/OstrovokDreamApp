package com.dmersiyanov.ostrovokdreamapp.api;


import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

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
    Observable<ResponseLogin> login(@Body LoginData request);

    @Headers({"Content-Type: application/json",
            "ota-brand: ostrovok",
            "accept: application/json",
            "Accept-Language: ru",
//            "host: mota.p.ostrovok.ru",
            "User-Agent: Ostrovok/3.6.5-DEV-DEBUG (android; FRD-L09; Android 7.0; Scale/2.00) ASID eafaafe12402f569 AID e274a392-8f96-40f5-aad6-aec644af12dd",
            "x-mota-api-version: 2",
            "x-mota-client-name: Android"})
    @GET("v3/bonus_history")
    Observable<ResponseDreams> getDreams(@Header("Authorization") String accessToken, @Query("page") int page, @Query("mode") String mode, @Query("per_page") int per_page);
}
