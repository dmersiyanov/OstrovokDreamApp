package com.dmersiyanov.ostrovokdreamapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dmersiyanov.ostrovokdreamapp.DreamsAdapter;
import com.dmersiyanov.ostrovokdreamapp.R;
import com.dmersiyanov.ostrovokdreamapp.SharedPrefsHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DreamsActivity extends AppCompatActivity {

    public final static DreamsAdapter dreamsAdapter = new DreamsAdapter();
    private DreamsPresenter presenter;
    private DreamsModel model;
    private static String auth_token;

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

        SharedPrefsHelper prefsHelper = new SharedPrefsHelper(this);
        model = new DreamsModel(prefsHelper);

        presenter = new DreamsPresenter(model);

        auth_token = model.getAuthToken();
        dreamsAmount.setText(model.getDreams());

        presenter.attachView(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Выйти");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("Выйти")) {
            Toast.makeText(this, "Успешно", Toast.LENGTH_LONG).show();
            this.startActivity(LoginActivity.getStartIntent(this));
            model.logOut();
            model.clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getToken() {
        return auth_token;
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DreamsActivity.class);
        return intent;
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.destroy();
    }


}
