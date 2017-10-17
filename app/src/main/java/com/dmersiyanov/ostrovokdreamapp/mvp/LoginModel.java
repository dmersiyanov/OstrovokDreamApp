package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

    public LoginModel(Context context) {
        this.context = context;
    }


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
                        UserData userData = responseLogin.getData();

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("auth-token", userData.getOauthCredentials().getAccessToken());
                        intent.putExtra("dreams-amount", userData.getUserBonusInfo().getPoints().toString());
                        context.startActivity(intent);

                    }
                });
    }

}
