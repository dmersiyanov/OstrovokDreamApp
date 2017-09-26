package com.dmersiyanov.ostrovokdreamapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView dreamsRecycler;
    private DreamsAdapter dreamsAdapter = new DreamsAdapter();
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dreamsRecycler = (RecyclerView) findViewById(R.id.dreamslist);
        layoutManager = new LinearLayoutManager(this);
        dreamsRecycler.setLayoutManager(layoutManager);

        dreamsRecycler.setAdapter(dreamsAdapter);


    }
}
