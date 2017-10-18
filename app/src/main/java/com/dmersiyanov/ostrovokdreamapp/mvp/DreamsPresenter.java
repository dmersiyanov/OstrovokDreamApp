package com.dmersiyanov.ostrovokdreamapp.mvp;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class DreamsPresenter {
    private MainActivity view;
    private final DreamsModel model;


    public DreamsPresenter(DreamsModel model) {
        this.model = model;
    }

    public void attachView(MainActivity usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }


    public void getDreams() {
        model.loadDreams(view.getToken());

    }


}
