package com.dmersiyanov.ostrovokdreamapp.mvp;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class DreamsPresenter {
    private DreamsActivity view;
    private final DreamsModel model;


    public DreamsPresenter(DreamsModel model) {
        this.model = model;
    }

    public void attachView(DreamsActivity usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }

    public void destroy() {
        model.unsubcribe();
    }


    public void getDreams() {
        model.loadDreams(view.getToken());

    }

    public DreamsActivity getView() {
        return view;
    }


}
