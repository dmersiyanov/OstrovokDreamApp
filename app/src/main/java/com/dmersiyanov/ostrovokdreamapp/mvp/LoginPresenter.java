package com.dmersiyanov.ostrovokdreamapp.mvp;

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

public class LoginPresenter {
    private LoginActivity view;
    private final LoginModel model;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();



    public LoginPresenter(LoginModel model) {
        this.model = model;
    }

    public void attachView(LoginActivity usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }

    public void destroy() {
        compositeSubscription.unsubscribe();
    }


    public void login(LoginData loginData) {
        Observable<ResponseLogin> loginObservable = model.login(loginData);

        Subscription loginSubscription = loginObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseLogin>() {
                    @Override
                    public void onCompleted() {
                        view.showToast("Успешная авторизация");
                        model.setLoggedIn();

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showToast("Во время авторизации произошла ошибка: " + e.getMessage());

                    }

                    @Override
                    public void onNext(ResponseLogin responseLogin) {
                        UserData userData = responseLogin.getData();
                        view.openDreamsActivity(userData);

                    }
                });

        compositeSubscription.add(loginSubscription);


    }

    public void saveToken(String token) {
        model.saveAuthToken(token);
    }

    public void saveDreams(String dreams) {
        model.saveDreams(dreams);
    }





}
