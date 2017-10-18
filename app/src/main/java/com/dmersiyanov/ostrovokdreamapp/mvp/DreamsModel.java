package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Context;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.api.AppOstrovok;
import com.dmersiyanov.ostrovokdreamapp.api.ResponseDreams;
import com.dmersiyanov.ostrovokdreamapp.pojo.BonusLog;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class DreamsModel {
    private Context context;

    public DreamsModel(Context context) {
        this.context = context;
    }

    private static final String MODE = "common";
    private static int PAGE = 1;
    private static int PER_PAGE = 20;

    private List<BonusLog> bonusList;

    public void loadDreams(String auth_token) {
        Observable<ResponseDreams> responseDreamsObservable = AppOstrovok.getApi().getDreams(auth_token, PAGE, MODE, PER_PAGE);
        responseDreamsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseDreams>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Во время загрузки снов произошла ошибка " + e.getMessage(), Toast.LENGTH_LONG).show();
                        //  dreamsAdapter.addFakeDreams();
                    }

                    @Override
                    public void onNext(ResponseDreams responseDreams) {
                        bonusList = responseDreams.getData().getData().getBonusLog();

                    }
                });
    }

    public List<BonusLog> getBonusList() {
        return bonusList;
    }



}
