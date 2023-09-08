package com.example.needcalendar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, nameEditText;
    private Button registerButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        emailEditText = findViewById(R.id.register_email);
        passwordEditText = findViewById(R.id.register_password);
        nameEditText = findViewById(R.id.register_name);
        registerButton = findViewById(R.id.btn_register);

        dbHelper = new DBHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();

                // 데이터베이스에 사용자 정보 추가
                boolean success = dbHelper.addUser(email, password, name);
                if (success) {
                    // 회원가입 성공
                    // 원하는 화면으로 이동하거나 작업 수행
                    // 예를 들어, 로그인 화면으로 이동
                    Intent intent = new Intent(RegisterActivity.this, login.class);
                    startActivity(intent);
                    finish();
                } else {
                    // 회원가입 실패
                    // 에러 메시지 표시 또는 작업 수행
                }
            }
        });
    }
}
