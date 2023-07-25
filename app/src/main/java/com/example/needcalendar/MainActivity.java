package com.example.needcalendar;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.recyclerview.widget.LinearLayoutManager;

        import android.app.DatePickerDialog;
        import android.app.TimePickerDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.DatePicker;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;
        import android.annotation.SuppressLint;
        import android.content.Context;

        import android.widget.Button;

        import com.google.android.material.bottomnavigation.BottomNavigationView;

        import java.time.LocalDate;
        import java.time.YearMonth;
        import java.time.format.DateTimeFormatter;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{

    Button btn_start_date, btn_start_time ,btn_end_date, btn_end_time;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    TextView textView;

    private ImageButton imageButton;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();


        btn_end_date = findViewById(R.id.btn_end_date);
        btn_end_time = findViewById(R.id.btn_end_time);
        btn_start_date = findViewById(R.id.btn_start_date);
        btn_start_time = findViewById(R.id.btn_start_time);
        textView = findViewById(R.id.textView);


        imageButton = findViewById(R.id.todaybutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 다른 액티비티로 화면 이동하는 Intent를 생성합니다.
                Intent intent = new Intent(getApplicationContext(), TodayCalendar.class);

                // Intent를 사용하여 다른 액티비티로 화면 이동합니다.
                startActivity(intent);
            }
        });

        imageButton = findViewById(R.id.menuButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent1 = new Intent(getApplicationContext(), WeekViewActivity.class);
                Intent intent2 = new Intent(getApplicationContext(), MenuViewActivity.class);

                //startActivity(intent1);
                startActivity(intent2);
            }
        });


        imageButton = findViewById(R.id.addbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(getApplicationContext(), add_schedule.class);

                startActivity(intent3);
            }
        });

    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}