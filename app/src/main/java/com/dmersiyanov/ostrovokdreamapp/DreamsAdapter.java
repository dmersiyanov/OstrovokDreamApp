package com.dmersiyanov.ostrovokdreamapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmersiyanov.ostrovokdreamapp.pojo.BonusLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitya on 25.09.2017.
 */

public class DreamsAdapter extends RecyclerView.Adapter<DreamsAdapter.ViewHolder> {


    private List<BonusLog> dreamsList = new ArrayList<>();

    DreamsAdapter() {


    }

    public void addAll(List<BonusLog> bonusLogs) {
        this.dreamsList.addAll(bonusLogs);
        notifyDataSetChanged();
    }


//        DreamsAdapter() {
//        dreamsList.add(new Dreams(400, "Скачивание приложения", "01.08.2017"));
//        dreamsList.add(new Dreams(100, "Регистрация", "01.06.2017"));
//        dreamsList.add(new Dreams(666, "Проживание в гостинице Петр 1 Москва", "01.05.2017"));
//        dreamsList.add(new Dreams(400, "Скачивание приложения", "01.08.2017"));
//        dreamsList.add(new Dreams(100, "Регистрация", "01.06.2017"));
//        dreamsList.add(new Dreams(666, "Проживание в гостинице Петр 1 Москва", "01.05.2017"));
//        dreamsList.add(new Dreams(400, "Скачивание приложения", "01.08.2017"));
//        dreamsList.add(new Dreams(100, "Регистрация", "01.06.2017"));
//        dreamsList.add(new Dreams(666, "Проживание в гостинице Петр 1 Москва", "01.05.2017"));


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dream_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BonusLog bonusLog = dreamsList.get(position);
        holder.amount.setText(String.valueOf(bonusLog.getDelta()));
        holder.reason.setText(String.valueOf(bonusLog.getReason()));
        holder.date.setText(String.valueOf(bonusLog.getCreatedAt()));

    }

    @Override
    public int getItemCount() {
        return dreamsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView amount, reason, date;

        public ViewHolder(View itemView) {
            super(itemView);
            amount = (TextView) itemView.findViewById(R.id.amount);
            reason = (TextView) itemView.findViewById(R.id.reason);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
