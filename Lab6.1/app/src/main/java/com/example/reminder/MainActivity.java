package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static final String SHARED_PREF_FILE = "MyApp";
    static final String SHARED_PREF_EDITOR_TEXT_KEY = "textInTheEditor";
    private int notificationId = 1;

    EditText reminderText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimePicker picker = (TimePicker) findViewById(R.id.timePicker);
        picker.setIs24HourView(true);
        reminderText = findViewById(R.id.reminder_text);

        findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.reminder_text);
                TimePicker timePicker = findViewById(R.id.timePicker);

                // Set notificationId & text.
                Intent intent = new Intent(MainActivity.this, ReminderBroadcastReceiver.class);
                intent.putExtra("notificationId", notificationId);
                intent.putExtra("todo", editText.getText().toString());

                // getBroadcast(context, requestCode, intent, flags)
                PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set alarm.
                // set(type, milliseconds, intent)
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Log.d("LAB6.1", "MainActivity, onClick-METHOD");

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        String text = reminderText.getText().toString();
        SharedPreferences sharedPreferences = getPref();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREF_EDITOR_TEXT_KEY, text);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getPref();
        String previousText = sharedPreferences.getString(SHARED_PREF_EDITOR_TEXT_KEY, null);
        reminderText.setText(previousText);
    }

    protected SharedPreferences getPref() {
        return getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);
    }

    private void PickTime() {
    }
}
