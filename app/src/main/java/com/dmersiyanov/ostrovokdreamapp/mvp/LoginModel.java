package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Context;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.SharedPrefsHelper;
import com.dmersiyanov.ostrovokdreamapp.api.AppOstrovok;
import com.dmersiyanov.ostrovokdreamapp.api.ResponseLogin;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class LoginModel {

    private Context context;

    SharedPrefsHelper mSharedPrefsHelper;

    public LoginModel(Context context, SharedPrefsHelper sharedPrefsHelper) {
        this.context = context;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    private UserData userData;
    // private boolean isLogedin = false;


    public void login(LoginData loginData) {
        Observable<ResponseLogin> loginObservable = AppOstrovok.getApi().login(loginData);
        loginObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseLogin>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(context, "Успешная авторизация ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Во время авторизации произошла ошибка " + e.getMessage(), Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNext(ResponseLogin responseLogin) {
                        userData = responseLogin.getData();
                        //  openDreamsActivity(context, userData);
//                        isLogedin = true;
                        setLoggedIn();



                    }
                });
    }

//    public void openDreamsActivity(Context context, UserData data) {
//        Intent intent = new Intent(context, DreamsActivity.class);
//        intent.putExtra("auth-token", data.getOauthCredentials().getAccessToken());
//        intent.putExtra("dreams-amount", data.getUserBonusInfo().getPoints().toString());
//        context.startActivity(intent);
//    }

    public UserData getUserData() {
        return this.userData;
    }

//    public boolean isLogedin() {
//        return isLogedin;
//    }


    public void clear() {
        mSharedPrefsHelper.clear();
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

    public String getAuthToken() {
        return mSharedPrefsHelper.getAuthToken();
    }


    public void setLoggedIn() {
        mSharedPrefsHelper.setLoggedInMode(true);
    }

    public Boolean getLoggedInMode() {
        return mSharedPrefsHelper.getLoggedInMode();
    }

}
