package com.example.heatapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    ArrayList<WorkOutType> events = new ArrayList<>();
    WorkOutType currentWorkOutType;
    TextView timeCount, eventType = null;
    int time, currentWorkoutIndex = 0;
    private TextToSpeech speakActivity = null;

    // At start we receive the intent and get its ArrayList and assign it to another ArrayList events
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Intent intent = getIntent();
        timeCount = findViewById(R.id.timeCounter);
        eventType = findViewById(R.id.activity_type);
        events = (ArrayList<WorkOutType>) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        speakActivity = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    speakActivity.setLanguage(Locale.US);
                    speak();
                }
            }
        });
        startTimer();
    }

    // We call the timer as long as there are objects in the ArrayList
    private void startTimer() {
        currentWorkOutType = events.get(currentWorkoutIndex);
        time = currentWorkOutType.getSeconds();
        eventType.setText(currentWorkOutType.getEvent());
        speak();

        new CountDownTimer(time * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeCount.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                currentWorkoutIndex++;
                if (currentWorkoutIndex == events.size()) {
                    currentWorkoutIndex = 0;
                    finish();
                } else {
                    speak();
                    startTimer();
                }
            }
        }.start();
    }

    // Speak the activity when the timer starts
    private void speak() {
        speakActivity.speak(eventType.getText(), TextToSpeech.QUEUE_FLUSH, null, null);
    }

    // onStop we shutdown speakActivity and return to main
    @Override
    protected void onStop() {
        super.onStop();
        speakActivity.shutdown();
    }
}