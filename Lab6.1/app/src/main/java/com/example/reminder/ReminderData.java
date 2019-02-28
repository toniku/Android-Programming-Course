package com.example.reminder;

import android.content.Context;
import android.content.SharedPreferences;

public class ReminderData {

    static final String SHARED_PREF_FILE = "MyApp";
    static final String SHARED_PREF_EDITOR_TEXT_KEY = "textInTheEditor";
    static final String SHARED_PREF_EDITOR_HOURS_KEY = "timePickerHours";
    static final String SHARED_PREF_EDITOR_MINUTES_KEY = "timePickerMinutes";
    Context context;
    private int minutes, hours;
    private String notificationText;

    public ReminderData(Context context) {
        this.context = context;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setDataHours(int hours) {
        this.hours = hours;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }


    // We use sharedPreferences for data storing
    public void saveDataToSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        // Create a new editor what you can use to make modifications to the data
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREF_EDITOR_TEXT_KEY, notificationText);
        editor.putInt(SHARED_PREF_EDITOR_MINUTES_KEY, minutes);
        editor.putInt(SHARED_PREF_EDITOR_HOURS_KEY, hours);
        editor.commit(); // In the end we call commit to make the changes
    }

    public void loadDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        notificationText = sharedPreferences.getString(SHARED_PREF_EDITOR_TEXT_KEY, null);
        minutes = sharedPreferences.getInt(SHARED_PREF_EDITOR_MINUTES_KEY, 0);
        hours = sharedPreferences.getInt(SHARED_PREF_EDITOR_HOURS_KEY, 0);
    }


}
