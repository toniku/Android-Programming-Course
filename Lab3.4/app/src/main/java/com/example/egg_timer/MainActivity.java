package com.example.egg_timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String EXTRA_MESSAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startButton).setOnClickListener(this);
        EditText timeInSeconds = findViewById(R.id.timeField);
        timeInSeconds.setSelection(timeInSeconds.getText().length());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.startButton) {
            Intent intent = new Intent(this, TimerActivity.class);
            EditText timeInSeconds = findViewById(R.id.timeField);
            String time = timeInSeconds.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, time);
            startActivity(intent);
        }
    }
}
