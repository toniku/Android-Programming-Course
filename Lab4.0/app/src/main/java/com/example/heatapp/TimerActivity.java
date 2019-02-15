package com.example.heatapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    ArrayList<WorkOutType> events = new ArrayList<>();

    int currentWorkoutIndex = 0;
    private TextToSpeech speak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Intent intent = getIntent();
        events = (ArrayList<WorkOutType>) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        speak = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    speak.setLanguage(Locale.US);
                    speak();
                }
            }
        });
        startTimer();
    }

    void startTimer() {
        final WorkOutType current = events.get(currentWorkoutIndex);
        final int time = current.getSeconds();
        final String event = current.getEvent();
        final TextView timeCount = findViewById(R.id.timeCounter);
        final TextView eventType = findViewById(R.id.activity_type);
        eventType.setText(event);
        speak();

        new CountDownTimer(time * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeCount.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                currentWorkoutIndex++;
                startTimer();
                speak();
            }
        }.start();
    }

    private void speak() {
        final TextView eventType = findViewById(R.id.activity_type);
        String event = eventType.getText().toString();
        speak.speak(event, TextToSpeech.QUEUE_FLUSH, null);
    }
}