package com.example.needcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class logout extends AppCompatActivity {

    private Button logoutButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);

        // 로그아웃 버튼을 XML 레이아웃과 연결
        logoutButton = findViewById(R.id.logoutButton);

        // 로그아웃 버튼 클릭 이벤트 처리
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기에 로그아웃 작업을 수행하는 코드를 추가합니다.
                // 예: 사용자 정보를 삭제하고 로그인 화면으로 이동하는 등의 작업을 수행합니다.
                // 아래는 예시 코드이므로 실제로 사용하는 방법에 맞게 수정해야 합니다.

                // 로그아웃 작업 수행
                performLogout();

                // 로그인 화면으로 이동
                Intent intent = new Intent(logout.this, login.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        });
    }

    // 로그아웃 작업을 수행하는 메서드
    private void performLogout() {
        // 여기에서 로그아웃 작업을 수행합니다.
        // 사용자 정보를 삭제하고 필요한 초기화 작업을 진행합니다.
        // 예: 세션 종료, 캐시 삭제 등
    }


}
