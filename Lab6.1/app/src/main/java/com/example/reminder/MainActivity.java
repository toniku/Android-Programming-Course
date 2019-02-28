package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText reminderText;
    TimePicker timePicker;
    ReminderData reminderData;
    private int notificationId = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reminderData = new ReminderData(this);
        timePicker = findViewById(R.id.timePicker);
        reminderText = findViewById(R.id.reminder_text);
        timePicker.setIs24HourView(true);

        // When user sets reminder, we create an pending intent which is sent to the ReminderBroadcastReceiver
        findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set notificationId & text.
                Intent intent = new Intent(getApplicationContext(), ReminderBroadcastReceiver.class);
                intent.putExtra("notificationText", reminderData.getNotificationText());

                // Create pending intent with time given
                PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), notificationId,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // Create AlarmManager which is sent with pending intent
                AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                // Create time when notification will fire
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.DAY_OF_WEEK, startTime.get(Calendar.DAY_OF_WEEK));
                startTime.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                startTime.set(Calendar.MINUTE, timePicker.getMinute());
                startTime.set(Calendar.SECOND, 0);

                long alarmStartTime = startTime.getTimeInMillis();


                /* If reminder time is smaller than current time, start notification on next day, still in progress */

                /*
                if (startTime.getTimeInMillis() < System.currentTimeMillis()) {
                    startTime.set(Calendar.DAY_OF_WEEK, startTime.get(Calendar.DAY_OF_WEEK + 1));
                    alarmStartTime = startTime.getTimeInMillis();
                }
                else
                {
                    alarmStartTime = startTime.getTimeInMillis();
                }
                */


                // Set alarm with (type, milliseconds, intent)
                // With RTC_WAKEUP we wake the device up and deliver the pending intent
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                saveData();

            }
        });
    }

    // Save data when app stops
    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    // Load data when app resumes
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    // For data saving we use ReminderData class
    // ReminderData class has a function that saves all the information to SharedPreferences
    private void saveData() {
        // Save data to ReminderData class
        reminderData.setMinutes(timePicker.getMinute());
        reminderData.setDataHours(timePicker.getHour());
        reminderData.setNotificationText(reminderText.getText().toString());
        reminderData.saveDataToSharedPreferences();
    }

    private void loadData() {
        // Load all the data from ReminderData class
        reminderData.loadDataFromSharedPreferences();
        reminderText.setText(reminderData.getNotificationText());
        timePicker.setMinute(reminderData.getMinutes());
        timePicker.setHour(reminderData.getHours());
    }
}
