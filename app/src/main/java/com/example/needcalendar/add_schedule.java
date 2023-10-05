package com.example.needcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class add_schedule extends AppCompatActivity {

    Button btn_start_date, btn_start_time ,btn_end_date, btn_end_time, btn_repeat;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    MultiDatePickerDialog multiDatePickerDialog;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        btn_end_date = findViewById(R.id.btn_end_date);
        btn_end_time = findViewById(R.id.btn_end_time);
        btn_start_date = findViewById(R.id.btn_start_date);
        btn_start_time = findViewById(R.id.btn_start_time);
        btn_repeat = findViewById(R.id.btn_repeat);
        textView = findViewById(R.id.textView);

        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveScheduleToDatabase();
            }
        });

    }

    private void saveScheduleToDatabase() {
        // 사용자로부터 입력된 일정 정보 가져오기
        String title = ((EditText) findViewById(R.id.editText1)).getText().toString();
        String startDate = btn_start_date.getText().toString();
        String startTime = btn_start_time.getText().toString();
        String endDate = btn_end_date.getText().toString();
        String endTime = btn_end_time.getText().toString();

        // 데이터베이스에 일정 추가
        DBHelper dbHelper = new DBHelper(this);
        boolean isInserted = dbHelper.addSchedule(title, startDate, startTime, endDate, endTime);

        // 일정 제목이 공백인지 확인
        if (title.trim().isEmpty()) {
            // 일정 제목이 공백인 경우, 토스트 메시지 표시
            Toast.makeText(this, "일정 제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return; // 일정 추가를 중단하고 메소드를 종료
        }

        if (isInserted) {
            // 일정 추가 성공
            Toast.makeText(this, "일정이 추가되었습니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // 일정 추가 실패
            Toast.makeText(this, "일정 추가에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {

        if (view == btn_start_date) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            btn_start_date.setText(year + " / " + (month + 1) + " / " + dayOfMonth);
                        }
                    }, mYear,mMonth,mDay);
            datePickerDialog.show();
        }
        if (view == btn_start_time) {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR);
            int mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            btn_start_time.setText(String.format("%02d:%02d",hourOfDay, minute));

                        }
                    },mHour,mMinute, false);
            timePickerDialog.show();
        }



        if (view == btn_end_date) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            btn_end_date.setText(year + " / " + (month + 1) + " / " + dayOfMonth);
                        }
                    }, mYear,mMonth,mDay);
            datePickerDialog.show();
        }
        if (view == btn_end_time) {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR);
            int mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            btn_end_time.setText(String.format("%02d:%02d",hourOfDay, minute));
                        }
                    },mHour,mMinute, false);
            timePickerDialog.show();
        }

        if (view == btn_repeat) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            multiDatePickerDialog = new  MultiDatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            btn_repeat.setText(year + " / " + (month + 1) + " / " + dayOfMonth);

                        }
                    }, mYear,mMonth,mDay);

            multiDatePickerDialog.addOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                }
            });

            multiDatePickerDialog.show();
        }

    }
}




