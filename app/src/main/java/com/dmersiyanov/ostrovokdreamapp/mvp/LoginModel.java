package com.dmersiyanov.ostrovokdreamapp.mvp;

import com.dmersiyanov.ostrovokdreamapp.SharedPrefsHelper;
import com.dmersiyanov.ostrovokdreamapp.api.AppOstrovok;
import com.dmersiyanov.ostrovokdreamapp.api.ResponseLogin;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;

import rx.Observable;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class LoginModel {

    public Observable<ResponseLogin> loginObservable;
    private SharedPrefsHelper mSharedPrefsHelper;

    public LoginModel(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void login(LoginData loginData) {
        loginObservable = AppOstrovok.getApi().login(loginData);
    }

    public SharedPrefsHelper getPrefHelper() {
        return this.mSharedPrefsHelper;
    }


    public void saveEmailId(String email) {
        mSharedPrefsHelper.putEmail(email);
    }

    public void saveAuthToken(String token) {
        mSharedPrefsHelper.putAuthToken(token);
    }

    public String getEmailId() {
        return mSharedPrefsHelper.getEmail();
    }

    public void setLoggedIn() {
        mSharedPrefsHelper.setLoggedInMode(true);
    }

    public Boolean getLoggedInMode() {
        return mSharedPrefsHelper.getLoggedInMode();
    }


}
