package com.dmersiyanov.ostrovokdreamapp.mvp;

import com.dmersiyanov.ostrovokdreamapp.SharedPrefsHelper;
import com.dmersiyanov.ostrovokdreamapp.api.AppOstrovok;
import com.dmersiyanov.ostrovokdreamapp.api.Constants;
import com.dmersiyanov.ostrovokdreamapp.api.ResponseDreams;

import rx.Observable;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class DreamsModel {
    private SharedPrefsHelper mSharedPrefsHelper;
    public Observable<ResponseDreams> responseDreamsObservable;


    public DreamsModel(SharedPrefsHelper sharedPrefsHelper) {
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    public void loadDreams(String auth_token) {
        responseDreamsObservable = AppOstrovok.getApi().getDreams(auth_token, Constants.getPAGE(), Constants.getMODE(), Constants.getPerPage());
    }


    public String getAuthToken() {
        return mSharedPrefsHelper.getAuthToken();
    }

    public String getDreams() {
        return mSharedPrefsHelper.getDreams();
    }



    public void clear() {
        mSharedPrefsHelper.clear();
    }

    public void logOut() {
        mSharedPrefsHelper.setLoggedInMode(false);
    }



}
