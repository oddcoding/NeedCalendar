package com.example.needcalendar;


import android.content.Context;
import android.content.SharedPreferences;

public class LoginManager {

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "MyAppPrefs"; // SharedPreferences 이름
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn"; // 로그인 상태를 저장하고 검색할 키

    public LoginManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }
}
