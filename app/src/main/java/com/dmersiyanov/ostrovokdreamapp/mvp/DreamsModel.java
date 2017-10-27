package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Context;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.SharedPrefsHelper;
import com.dmersiyanov.ostrovokdreamapp.api.AppOstrovok;
import com.dmersiyanov.ostrovokdreamapp.api.ResponseDreams;
import com.dmersiyanov.ostrovokdreamapp.pojo.BonusLog;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.dmersiyanov.ostrovokdreamapp.mvp.DreamsActivity.dreamsAdapter;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class DreamsModel {
    private Context context;
    private SharedPrefsHelper mSharedPrefsHelper;


    public DreamsModel(Context context, SharedPrefsHelper sharedPrefsHelper) {
        this.context = context;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private static final String MODE = "common";
    private static final int PAGE = 1;
    private static final int PER_PAGE = 20;

    private List<BonusLog> bonusList;

    public void loadDreams(String auth_token) {
        Observable<ResponseDreams> responseDreamsObservable = AppOstrovok.getApi().getDreams(auth_token, PAGE, MODE, PER_PAGE);


        Subscription dreamsSubscription = responseDreamsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseDreams>() {
                    @Override
                    public void onCompleted() {
                        if (bonusList != null) {
                            dreamsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Во время загрузки снов произошла ошибка " + e.getMessage(), Toast.LENGTH_LONG).show();
                        dreamsAdapter.addFakeDreams();
                    }

                    @Override
                    public void onNext(ResponseDreams responseDreams) {
                        bonusList = responseDreams.getData().getData().getBonusLog();
                        dreamsAdapter.addAll(bonusList);
                        if (bonusList != null) {
                            dreamsAdapter.notifyDataSetChanged();
                        }

                    }
                });

        compositeSubscription.add(dreamsSubscription);
    }


    public List<BonusLog> getBonusList() {
        return bonusList;
    }

    public String getAuthToken() {
        return mSharedPrefsHelper.getAuthToken();
    }

    public void clear() {
        mSharedPrefsHelper.clear();
    }

    public void logOut() {
        mSharedPrefsHelper.setLoggedInMode(false);
    }

    public void unsubcribe() {
        compositeSubscription.unsubscribe();
    }




}
