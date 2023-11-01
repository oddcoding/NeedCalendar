package com.example.needcalendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class add_schedule extends AppCompatActivity {


    public class ColorPicker extends AppCompatActivity {
        private final String TAG=this.getClass().getSimpleName();
        private int tColor; // 직전 선택한 색상
        private Button btnColorPicker;

        private ImageView imageView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_schedule);


            btnColorPicker = (Button)findViewById(R.id.btn_color);
            btnColorPicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { Log.e(TAG,"choice() onClick");

                }
            });
        }

       }


    Button btn_start_date, btn_start_time ,btn_end_date, btn_end_time, btn_repeat;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    MultiDatePickerDialog multiDatePickerDialog;
    TextView textView;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button okButton;
    private DatabaseHelper dbHelper;
    private Button btn_help;
    private RecyclerView recyclerView1; // 체크박스가 체크된 경우의 리사이클러뷰
    private RecyclerView recyclerView2; // 체크박스가 해제된 경우의 리사이클러뷰
    private checklist adapter1; // recyclerView1의 어댑터
    private checklist adapter2;
    private ImageView checkIcon;
    private CheckBox checkBox1;

    private ImageView imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        btn_end_date = findViewById(R.id.btn_end_date);
        btn_end_time = findViewById(R.id.btn_end_time);
        btn_start_date = findViewById(R.id.btn_start_date);
        btn_start_time = findViewById(R.id.btn_start_time);
        textView = findViewById(R.id.textView);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        okButton = findViewById(R.id.ok);

        // 데이터베이스 도우미를 초기화합니다.
        dbHelper = new DatabaseHelper(this);

        btn_help = (Button) findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder d = new AlertDialog.Builder(add_schedule.this);

                d.setTitle("기념일 알림 기능");
                d.setMessage("기념일 알림 기능 활성화시 시정 시간 외에도 2차례 알림이 추가로 울립니다.");

                d.show();
            }
        });

        // OK 버튼에 클릭 리스너를 설정합니다.

        CheckBox checkBox = findViewById(R.id.myCheckBox);

//        View secondLayout = getLayoutInflater().inflate(R.layout.recyclerview_item, null); // 두 번째 XML 레이아웃 파일
//        ImageView imageView = secondLayout.findViewById(R.id.imageView3);
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (!isChecked) {
//                    imageView.setVisibility(View.INVISIBLE); // isChecked가 false인 경우 ImageView를 숨깁니다.
//                } else {
//                    imageView.setVisibility(View.VISIBLE); // isChecked가 true인 경우 ImageView를 보이게 합니다.
//                }
//            }
//        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // EditText에서 텍스트를 가져옵니다.
                String title = editText1.getText().toString();
                String place = editText2.getText().toString();
                String memo = editText3.getText().toString();
                boolean isCheckBox = checkBox.isChecked();

//                if (imageView != null) {
//                    if (!isCheckBox) {
//                        imageView.setVisibility(View.INVISIBLE); // isChecked가 0인 경우 ImageView를 숨깁니다.
//                    } else {
//                        imageView.setVisibility(View.VISIBLE); // isChecked가 1인 경우 ImageView를 보이게 합니다.
//                    }
//                }


                long eventId = dbHelper.insertData(title, place, memo, isCheckBox);

                // 데이터베이스에 데이터를 추가합니다.
                // 추가 결과를 확인하고 필요한 작업을 수행합니다.

                if (eventId != -1) {
                    // 데이터가 성공적으로 추가됨
                    // 추가 후에 필요한 작업을 수행하세요
                    Toast.makeText(add_schedule.this, "일정이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(add_schedule.this, MainActivity.class);
                    startActivity(intent); // 메인 화면으로 이동

                } else {
                    // 데이터 추가 실패
                    // 실패한 경우 필요한 작업을 수행하세요
                    Toast.makeText(add_schedule.this, "일정 추가에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }

//                editText1.setText("");
//                editText2.setText("");
//                editText3.setText("");

                TextView titleTextView = findViewById(R.id.editText1);
                TextView placeTextView = findViewById(R.id.editText2);
                TextView memoTextView = findViewById(R.id.editText3);

                title = editText1.getText().toString();
                place = editText2.getText().toString();
                memo = editText3.getText().toString();

                titleTextView.setText(title);
                placeTextView.setText(place);
                memoTextView.setText(memo);

            }

        });

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




