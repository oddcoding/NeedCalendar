package com.example.needcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuViewActivity extends AppCompatActivity {
    private Button loginButton;
    private LoginManager loginManager;
    private boolean isLoggedIn = false;
    private static final int LOGIN_REQUEST_CODE = 1;

        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.menu);

            loginManager = new LoginManager(this);

            loginButton = findViewById(R.id.login);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 로그인 버튼 클릭 시 로그인 화면으로 이동
                    Intent loginIntent = new Intent(MenuViewActivity.this, login.class);
                    startActivity(loginIntent);
                }
            });
        }
}
