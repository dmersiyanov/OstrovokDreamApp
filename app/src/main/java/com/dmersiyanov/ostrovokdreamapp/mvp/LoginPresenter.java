package com.dmersiyanov.ostrovokdreamapp.mvp;

import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class LoginPresenter {
    private LoginActivity view;
    private final LoginModel model;


    public LoginPresenter(LoginModel model) {
        this.model = model;
    }

    public void attachView(LoginActivity usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }


    public void login() {
        LoginData loginData = view.getLoginData();
        model.login(loginData);

        if (model.isLogedin()) {
            view.openDreamsActivity(model.getUserData());
        }

    }


}
