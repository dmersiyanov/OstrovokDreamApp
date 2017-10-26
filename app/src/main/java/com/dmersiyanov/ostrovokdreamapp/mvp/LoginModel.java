package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.SharedPrefsHelper;
import com.dmersiyanov.ostrovokdreamapp.api.AppOstrovok;
import com.dmersiyanov.ostrovokdreamapp.api.ResponseLogin;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class LoginModel {

    private Context context;
    private SharedPrefsHelper mSharedPrefsHelper;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public LoginModel(Context context, SharedPrefsHelper sharedPrefsHelper) {
        this.context = context;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    private UserData userData;


    public void login(LoginData loginData) {
        Observable<ResponseLogin> loginObservable = AppOstrovok.getApi().login(loginData);

        Subscription loginSubscription = loginObservable.subscribeOn(Schedulers.io())
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
                        openDreamsActivity(context, userData);
                        setLoggedIn();



                    }
                });

        compositeSubscription.add(loginSubscription);
    }

    public void openDreamsActivity(Context context, UserData data) {
        Intent intent = new Intent(context, DreamsActivity.class);
//        intent.putExtra("auth-token", data.getOauthCredentials().getAccessToken());
        saveAuthToken(data.getOauthCredentials().getAccessToken());

        intent.putExtra("dreams-amount", data.getUserBonusInfo().getPoints().toString());
        context.startActivity(intent);
    }

    public UserData getUserData() {

        return this.userData;
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

    public void unsubcribe() {
        compositeSubscription.unsubscribe();
    }

}
