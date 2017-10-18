package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dmersiyanov.ostrovokdreamapp.DreamsAdapter;
import com.dmersiyanov.ostrovokdreamapp.R;

public class MainActivity extends AppCompatActivity  {

    private DreamsPresenter presenter;

    private static String auth_token;
    private Button dreamsBtn;
    private TextView dreamsAmount;

    private DreamsAdapter dreamsAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {

        dreamsAdapter = new DreamsAdapter();
        final RecyclerView dreamsRecycler = (RecyclerView) findViewById(R.id.dreamslist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        dreamsRecycler.setLayoutManager(layoutManager);
        dreamsRecycler.setAdapter(dreamsAdapter);

        dreamsBtn = (Button) findViewById(R.id.get_dreams);
        dreamsAmount = (TextView) findViewById(R.id.dreams_amount);

        Intent intent = getIntent();
        auth_token = intent.getStringExtra("auth-token");
        dreamsAmount.setText(intent.getStringExtra("dreams-amount"));


        final DreamsModel model = new DreamsModel(this);
        presenter = new DreamsPresenter(model);
        presenter.attachView(this);

        dreamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getDreams();
                dreamsAdapter.addAll(model.getBonusList());

            }
        });



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
