package com.example.needcalendar;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.needcalendar.R;
import com.example.needcalendar.add_schedule;

import java.util.ArrayList;
import java.util.List;

public class TodayCalendar extends AppCompatActivity {

    Context context;
    private RecyclerView mPostRecyclerView;

    private List<list> mDatas;

    ArrayList<String> toDoList;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily);
        context = this;
        mPostRecyclerView = findViewById(R.id.list_rv);

        mDatas  = new ArrayList<>();


//        mDatas.add(new list("title1", "place1", "Memo1"));
//        mDatas.add(new list("title2", "place2", "Memo2"));
//        mDatas.add(new list("title3", "place3", "Memo3"));

        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //초기화
        toDoList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.recyclerview_item, toDoList);


        View add_schedule = getLayoutInflater().inflate(R.layout.add_schedule, null);
        editText = add_schedule.findViewById(R.id.editText1);
        //어뎁터 적용


        //할일추가 버튼 이벤트
        Button btn_ok = add_schedule.findViewById(R.id.ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                addItemToList();
            }
        });

    }//onCreate

    //할일 추가
    public void addItemToList(){

        //아이템 등록
        toDoList.add(editText.getText().toString());

        //적용
        adapter.notifyDataSetChanged();;

        //입력창 초기화
        editText.setText("");
    }

}