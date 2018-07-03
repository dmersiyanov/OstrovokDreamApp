package com.dmersiyanov.ostrovokdreamapp.mvp;

import com.arellomobile.mvp.MvpView;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

interface LoginView extends MvpView {

    void showToast(String msg);
    void openDreamsActivity(UserData dat);

}
