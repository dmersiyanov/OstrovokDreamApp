package com.dmersiyanov.ostrovokdreamapp.mvp;

import com.dmersiyanov.ostrovokdreamapp.api.ResponseDreams;
import com.dmersiyanov.ostrovokdreamapp.pojo.BonusLog;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.dmersiyanov.ostrovokdreamapp.mvp.DreamsActivity.dreamsAdapter;

/**
 * Created by dmersianov on 17/10/2017.
 */

public class DreamsPresenter {
    private DreamsActivity view;
    private final DreamsModel model;
    private List<BonusLog> bonusList = new ArrayList<>();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();


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
        compositeSubscription.unsubscribe();
    }

    public void getDreams() {
        model.loadDreams(view.getToken());


        Subscription dreamsSubscription = model.responseDreamsObservable.subscribeOn(Schedulers.io())
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
                        view.showToast("Во время загрузки снов произошла ошибка " + e.getMessage());
                        dreamsAdapter.addFakeDreams();
                    }

                    @Override
                    public void onNext(ResponseDreams responseDreams) {
                        bonusList = responseDreams.getData().getData().getBonusLog();
                        dreamsAdapter.addAll(bonusList);
//                        if (bonusList != null) {
//                            dreamsAdapter.notifyDataSetChanged();
//                        }

                    }
                });

        compositeSubscription.add(dreamsSubscription);

    }


}
