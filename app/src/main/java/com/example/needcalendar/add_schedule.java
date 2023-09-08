package com.example.needcalendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class add_schedule extends AppCompatActivity {

    Button btn_start_date, btn_start_time ,btn_end_date, btn_end_time;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    TextView textView;
    private DBHelper mDBHelper;
    private RecyclerView mRv_todo;

    private ArrayList<TodoItem> mTodoItems;
    private CustomAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        btn_end_date = findViewById(R.id.btn_end_date);
        btn_end_time = findViewById(R.id.btn_end_time);
        btn_start_date = findViewById(R.id.btn_start_date);
        btn_start_time = findViewById(R.id.btn_start_time);


        setInit();
    }

    private void setInit() {

        mDBHelper = new DBHelper(this);

        mRv_todo = findViewById(R.id.list_rv);
        mTodoItems = new ArrayList<>();

        //Button mBtn_write = findViewById(R.id.ok);

        // load recent DB
        //loadRecentDB();


        }

    private void loadRecentDB() {
        mTodoItems = mDBHelper.getTodoList();
        if(mAdapter == null){
            mAdapter = new CustomAdapter(mTodoItems, this);
            mRv_todo.setHasFixedSize(true);
            mRv_todo.setAdapter(mAdapter);

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
            Button btn_ok= findViewById(R.id.ok);
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText et_title = findViewById(R.id.editText1);
                    EditText et_content = findViewById(R.id.editText2);
                    EditText et_memo = findViewById(R.id.editText3);

                    mDBHelper.InsertTodo(et_title.getText().toString(), et_content.getText().toString(), et_memo.getText().toString());
                    //insert UI
                    TodoItem item = new TodoItem();
                    item.setTitle(et_title.getText().toString());
                    item.setContent(et_content.getText().toString());
                    item.setWriteDate(et_memo.getText().toString());
                    mAdapter.addItem(item);

                    et_title.setText("");
                    et_content.setText("");
                    et_memo.setText("");

                }
            });





    }
}
