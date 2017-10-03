package com.dmersiyanov.ostrovokdreamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.api.ResponseDreams;
import com.dmersiyanov.ostrovokdreamapp.pojo.BonusLog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    private static final String MODE = "common";
    private static int PAGE = 1;
    private static int PER_PAGE = 10;
    private static String auth_token = "Bearer 5LoSw33fkjDiJ5ft7sM3wiiS6FlA5W";
    //  private static String auth_token;
    Button dreamsBtn;

    private List<BonusLog> bonuslist;
    private DreamsAdapter dreamsAdapter = new DreamsAdapter();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView dreamsRecycler = (RecyclerView) findViewById(R.id.dreamslist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        dreamsRecycler.setLayoutManager(layoutManager);
        dreamsRecycler.setAdapter(dreamsAdapter);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        auth_token = intent.getStringExtra("auth-token");


        dreamsBtn = (Button) findViewById(R.id.get_dreams);


        dreamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppOstrovok.getApi().getDreams(auth_token, PAGE, MODE, PER_PAGE).enqueue(new Callback<ResponseDreams>() {
                    @Override
                    public void onResponse(Call<ResponseDreams> call, Response<ResponseDreams> response) {

                        try {
                            bonuslist = response.body().getData().getData().getBonusLog();
                            dreamsAdapter.addAll(bonuslist);
                            Toast.makeText(MainActivity.this, "Вы получили " + bonuslist.get(0).getDelta() + " за проживание в" + bonuslist.get(0).getOrderItemData().getHotelName(), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Во время загрузки снов произошла ошибка " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseDreams> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Во время загрузки снов произошла ошибка " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
