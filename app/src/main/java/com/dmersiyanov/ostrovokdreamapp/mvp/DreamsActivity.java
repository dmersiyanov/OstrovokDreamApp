package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dmersiyanov.ostrovokdreamapp.DreamsAdapter;
import com.dmersiyanov.ostrovokdreamapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DreamsActivity extends AppCompatActivity {

    public final static DreamsAdapter dreamsAdapter = new DreamsAdapter();
    private DreamsPresenter presenter;
    private static String auth_token;

    // @BindView(R.id.get_dreams) Button dreamsBtn;
    @BindView(R.id.dreams_amount)
    TextView dreamsAmount;
    @BindView(R.id.dreamslist)
    RecyclerView dreamsRecycler;

    @OnClick(R.id.get_dreams)
    void getDreams() {
        presenter.getDreams();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }

    private void init() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        dreamsRecycler.setLayoutManager(layoutManager);
        dreamsRecycler.setAdapter(dreamsAdapter);

        Intent intent = getIntent();
        auth_token = intent.getStringExtra("auth-token");
        dreamsAmount.setText(intent.getStringExtra("dreams-amount"));

        final DreamsModel model = new DreamsModel(this);

        presenter = new DreamsPresenter(model);
        presenter.attachView(this);

    }

    public String getToken() {
        return auth_token;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
