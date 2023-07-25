package com.example.needcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class add_schedule extends AppCompatActivity {

    Button btn_start_date, btn_start_time ,btn_end_date, btn_end_time;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        btn_end_date = findViewById(R.id.btn_end_date);
        btn_end_time = findViewById(R.id.btn_end_time);
        btn_start_date = findViewById(R.id.btn_start_date);
        btn_start_time = findViewById(R.id.btn_start_time);
        textView = findViewById(R.id.textView);

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

    }


}
