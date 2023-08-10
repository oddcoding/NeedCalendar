package com.example.needcalendar;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodayCalendar extends AppCompatActivity {

    Context context;
    Button button;
    private RecyclerView mPostRecyclerView;
    private checklist mAdapter;
    private List<list> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.daily);
        context = this;
        mPostRecyclerView = findViewById(R.id.list_rv);

        mDatas = new ArrayList<>();

        mDatas.add(new list("title1", "place1", "Memo1"));
        mDatas.add(new list("title2", "place2", "Memo2"));
        mDatas.add(new list("title3", "place3", "Memo3"));;


        mAdapter = new checklist(mDatas);
        mPostRecyclerView.setAdapter(mAdapter);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}




