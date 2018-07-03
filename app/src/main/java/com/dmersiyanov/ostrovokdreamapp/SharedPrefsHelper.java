package com.dmersiyanov.ostrovokdreamapp;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Mitya on 22.10.2017.
 */

public class SharedPrefsHelper {

    private static final String MY_PREFS = "MY_PREFS";
    private static final String EMAIL = "EMAIL";
    private static final String DREAMS = "DREAMS";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";

    SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    public void putEmail(String email) {
        mSharedPreferences.edit().putString(EMAIL, email).apply();
    }

    public void putDreams(String dreams) {
        mSharedPreferences.edit().putString(DREAMS, dreams).apply();
    }

    public void putAuthToken(String token) {
        mSharedPreferences.edit().putString(AUTH_TOKEN, token).apply();
    }

    public String getEmail() {
        return mSharedPreferences.getString(EMAIL, null);
    }

    public String getDreams() {
        return mSharedPreferences.getString(DREAMS, null);
    }

    public String getAuthToken() {
        return mSharedPreferences.getString(AUTH_TOKEN, null);
    }

    public boolean getLoggedInMode() {
        return mSharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

    public void setLoggedInMode(boolean loggedIn) {
        mSharedPreferences.edit().putBoolean("IS_LOGGED_IN", loggedIn).apply();
    }

}
