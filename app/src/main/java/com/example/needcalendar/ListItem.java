package com.example.needcalendar;

import android.graphics.Color;
import android.widget.ImageView;

public class ListItem {
    private int id;
    private String title;
    private String place;
    private String memo;
    private int backgroundColor;

    public ListItem(int id, String title, String place, String memo, int isChecked) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.memo = memo;

        this.backgroundColor = Color.TRANSPARENT;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public String getMemo() {
        return memo;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


}
