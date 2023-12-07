package com.example.needcalendar;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class seed extends AppCompatActivity {

    private Button button;
    private ImageView imageView;

    private int[] imageArray = {R.drawable.seed, R.drawable.sprout, R.drawable.plant, R.drawable.flower1, R.drawable.seed, R.drawable.sprout, R.drawable.plant, R.drawable.flower2, R.drawable.seed, R.drawable.sprout, R.drawable.plant, R.drawable.flower3, R.drawable.seed, R.drawable.sprout, R.drawable.plant, R.drawable.flower4};
    private int currentImageIndex = 0;
    private int imageRepetitionCount = 0;
    private boolean isButtonEnabled = true;
    private ColorStateList originalButtonColor;
    private static final String IMAGE_INDEX_KEY = "image_index";

    private static final String IMAGE_REPETITION_COUNT_KEY = "image_repetition_count";
    private static final long BUTTON_DISABLE_DURATION = 5000; // 5초, 밀리초 단위

    private long buttonDisabledTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seed);

        button = findViewById(R.id.seedButton);
        imageView = findViewById(R.id.seedIcon);
        originalButtonColor = button.getBackgroundTintList();

        // SharedPreferences에서 저장된 이미지 불러오기
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentImageIndex = preferences.getInt(IMAGE_INDEX_KEY, 0);
        imageView.setImageResource(imageArray[currentImageIndex]);
        imageRepetitionCount = preferences.getInt(IMAGE_REPETITION_COUNT_KEY, 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isButtonEnabled) {
                    return;
                }

                setImageAndIncrementCount();

                if (imageRepetitionCount >= 4) {
                    imageRepetitionCount = 0;
                    currentImageIndex = (currentImageIndex + 1) % imageArray.length;
                    imageView.setImageResource(imageArray[currentImageIndex]);

                    // 변경된 이미지 인덱스를 SharedPreferences에 저장
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt(IMAGE_INDEX_KEY, currentImageIndex);
                    editor.apply();
                }
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(IMAGE_REPETITION_COUNT_KEY, imageRepetitionCount);
                editor.apply();

                disableButtonFor5Seconds();
            }
        });

        buttonDisabledTime = preferences.getLong("button_disabled_time", 0);
        long elapsedTime = System.currentTimeMillis() - buttonDisabledTime;
        if (elapsedTime < BUTTON_DISABLE_DURATION) {
            disableButtonForRemainingTime(BUTTON_DISABLE_DURATION - elapsedTime);
        } else {
            enableButton(); // 시간이 지나서 자동으로 활성화
        }
    }
    private void saveButtonDisabledTime() {
        buttonDisabledTime = System.currentTimeMillis();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("button_disabled_time", buttonDisabledTime);
        editor.apply();
    }

    private void setImageAndIncrementCount() {

        imageRepetitionCount++;
    }

    private void disableButtonFor5Seconds() {
        button.setEnabled(false);
        isButtonEnabled = false;
        button.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        saveButtonDisabledTime();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enableButton();
            }
        }, BUTTON_DISABLE_DURATION);
    }

    private void disableButtonForRemainingTime(long remainingTime) {
        button.setEnabled(false);
        isButtonEnabled = false;
        button.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                enableButton();
            }
        },remainingTime);
    }
    private void enableButton() {
        button.setEnabled(true);
        isButtonEnabled = true;
        button.setBackgroundTintList(originalButtonColor);
    }
}
