package com.dmersiyanov.ostrovokdreamapp.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
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
@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {
//    private LoginActivity view;
    private final LoginRepo model;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();



    public LoginPresenter(LoginRepo model) {
        this.model = model;
    }

//    public void attachView(LoginActivity usersActivity) {
//        view = usersActivity;
//    }
//
//    public void detachView() {
//        view = null;
//    }

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
                        getViewState().showToast("Успешная авторизация");
                        model.setLoggedIn();

                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showToast("Во время авторизации произошла ошибка: " + e.getMessage());

                    }

                    @Override
                    public void onNext(ResponseLogin responseLogin) {
                        UserData userData = responseLogin.getData();
                        getViewState().openDreamsActivity(userData);

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
