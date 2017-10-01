package com.dmersiyanov.ostrovokdreamapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.api.ResponseDreams;
import com.dmersiyanov.ostrovokdreamapp.api.ResponseLogin;
import com.dmersiyanov.ostrovokdreamapp.pojo.BonusLog;
import com.dmersiyanov.ostrovokdreamapp.pojo.LoginData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    private static final String MODE = "common";
    private static int PAGE = 1;
    private static int PER_PAGE = 10;
    private static String Authorization = "Bearer 5LoSw33fkjDiJ5ft7sM3wiiS6FlA5W";

    private Button authBtn;
    private Button dreamsBtn;
    private LoginData loginData;

    private RecyclerView dreamsRecycler;
    private List<BonusLog> bonuslist;
    private DreamsAdapter dreamsAdapter = new DreamsAdapter();
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dreamsRecycler = (RecyclerView) findViewById(R.id.dreamslist);
        layoutManager = new LinearLayoutManager(this);
        dreamsRecycler.setLayoutManager(layoutManager);
        dreamsRecycler.setAdapter(dreamsAdapter);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        authBtn = (Button) findViewById(R.id.login);
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginData = new LoginData("dmersiyanov@mail.ru", "123");

                AppOstrovok.getApi().login(loginData).enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {


                        try {
                            UserData userData = response.body().getData();
                            if (response.body() != null) {
                                String email = userData.getEmail();
                                Toast.makeText(MainActivity.this, "Успешная авторизация " + email, Toast.LENGTH_LONG).show();
                        }

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Во время авторизации произошла ошибка " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        dreamsBtn = (Button) findViewById(R.id.get_dreams);
        dreamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppOstrovok.getApi().getDreams(Authorization, PAGE, MODE, PER_PAGE).enqueue(new Callback<ResponseDreams>() {
                    @Override
                    public void onResponse(Call<ResponseDreams> call, Response<ResponseDreams> response) {

                        bonuslist = response.body().getData().getData().getBonusLog();
                        dreamsAdapter.addAll(bonuslist);

                        Toast.makeText(MainActivity.this, "Вы получили " + bonuslist.get(0).getDelta() + " за проживание в" + bonuslist.get(0).getOrderItemData().getHotelName(), Toast.LENGTH_LONG).show();

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
